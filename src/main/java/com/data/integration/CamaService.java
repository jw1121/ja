package com.data.integration;

import com.data.integration.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Service
public class CamaService {
    final static Logger logger = LoggerFactory.getLogger(CamaService.class);

    final static String dateFormat = "yyyy-MM-dd";
    final static String dateFormatmonth = "dd-MMM-yy";

    @Autowired
    CamaRepository camaRepository;

    /**
     * @param payload
     * @return
     * @throws Exception
     *
     * - assume there will be only 1 userData and saleData
     * - Every deed will have Owndat and Sales record
     * - duplicate Owndat and ownmlt record for each parcel
     *
     * OWNDAT :
     * - The ParId-TaxYr is unique (foreing key) for OWNDAT table
     * - overwrite existing record in OWNDAT and increment SEQ value.
     * - There will be existing OWNDAT data that will need to be overwritten.
     * - OWNDAT.OWNNUM is always null
     *
     * OWNMLT :
     * - Owner 2+ will go to Ownmlt table
     * - if there OWNMLT record exist then deactivate before insert on field "deactivat" with value "e" and sysdate to current.
     * - deactivate all (if multiple) records found
     * - The ParId-TaxYr-OwnSeq is unique (foreing key) for OWNMLT table
     * -
     *
     * SALES :
     * - each parcel entries will be new SALES record.
     * - SALETYPE in SALES are different for each parcel but unknown at this time
     * - OLDDOWN is own1 from OWNDAT before overwriting
     * - always new record to SALES table and set SALES.SEQ to 0
     *
     *
     */
    public boolean LeonProcess(Leon payload) throws Exception {
        try {
            logger.debug("sfdsfsd");
            if (payload == null) { throw new CamaException("Empty payload."); }
            SaleData saleData = payload.getSaleData();
            if (saleData == null) { throw new CamaException("Empty sale date."); }

            BuyerAddressComponent buyerAddressComponent = saleData.getBuyer_address_component();
            List<BuyerNamesComponent> buyerNamesComponents = saleData.getBuyer_names_component();
            ParcelMatchCardsComponent parcelMatchCardsComponent = saleData.getParcel_match_cards_component();

            if( buyerAddressComponent == null || buyerNamesComponents == null || parcelMatchCardsComponent == null) { return false; }

            List<MainParcel> mainParcels = null;
            if(parcelMatchCardsComponent != null) {
                mainParcels = parcelMatchCardsComponent.getMainParcels();
                if(mainParcels == null) { throw new CamaException("Empty parcel info."); }
                for(MainParcel mainParcel : mainParcels) {
                    if(StringUtils.isEmpty(mainParcel.getParcelNumber())) {
                        throw new CamaException("parcel number is required.");
                    }
                }
            }

            BuyerNamesComponent firstBuyer = buyerNamesComponents.get(0);
            String book = saleData.getBook();
            String page = saleData.getPage();
            int taxYear = saleData.getTax_year();
            double stampAmount = saleData.getDocstamp_amount();
            int price = saleData.getDerived_sale_price_florida();
            Date saleDate = toSQLDate(saleData.getSale_date(), dateFormat);
            Date recordDate = toSQLDate(saleData.getRecorded_date(), dateFormat);
            String instrtype = saleData.getSale_instrument();
            int parcelCount = saleData.getTotal_parcel_count();

            int seq = 0;
            String source = "D";
            String steb = null;
            String hideName = "N";

            camaRepository.dbConn();
            int salesKey = camaRepository.getNextSeq();
            logger.debug("SalesKey: " + salesKey);
            if(salesKey < 1) { throw new CamaException("Something wrong with saleKey."); }

            for(MainParcel mainParcel : mainParcels) {
                HashMap<String, Object> oldOWNDAT = camaRepository.getOWNDAT(mainParcel.getParcelNumber(), taxYear);
                if(oldOWNDAT.isEmpty()) { throw new CamaException("Existing OWNDAT record was not found. "+ mainParcel.getParcelNumber() + "-"  + taxYear);}
                seq = (int) oldOWNDAT.get("seq");
                seq++;
                logger.debug("new seq number:" + seq);

                String oldown = (String) oldOWNDAT.get("oldown");

                camaRepository.updateOWNDAT(mainParcel, taxYear, book, page, salesKey, hideName, seq, firstBuyer, buyerAddressComponent);
                camaRepository.insertSALE(mainParcel, saleDate, stampAmount, price, salesKey, book, page, oldown, firstBuyer.getFullName1(), source, steb, parcelCount, instrtype, recordDate);

                for(int i = 1; buyerNamesComponents.size() > i; i++) {
                    logger.debug("buyerNamesComponent: " + i);
                    BuyerNamesComponent buyerNamesComponent = buyerNamesComponents.get(i);
                    camaRepository.deactivatOWNMLT(getcurrentDate(dateFormatmonth), mainParcel.getParcelNumber(), taxYear, buyerNamesComponent.getIndex());
                    camaRepository.insertOWNMLT(mainParcel, taxYear, seq, book, page, salesKey, hideName, buyerNamesComponent);
                }
            }
            // TODO Test transactional
            camaRepository.commit();
        } catch (SQLException e) {
            //TODO log to file
            e.printStackTrace();
            throw new CamaException(e.getMessage(), e);
        }

        return true;
    }

    private Date toSQLDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        java.util.Date parsed = null;
        try {
            parsed = sdf.parse(date);
        } catch (ParseException e1) {
            e1.printStackTrace();
            throw new CamaException("Bad date format.");
        }
        return new java.sql.Date(parsed.getTime());
    }

    private Date getcurrentDate(String format) {
        java.util.Date date = new java.util.Date();
        return new java.sql.Date(date.getTime());
    }
}

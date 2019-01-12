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
     * - explictly set OWNDAT.OWNNUM is always null
     *
     * OWNMLT :
     * - Owner 2+ will go to Ownmlt table
     * - always deactivate all (if multiple) records found based on ParId and TaxYr
     * - if there OWNMLT record exist then always deactivate before insert on field "deactivat" with value "e" and sysdate to current.
     * - The ParId-TaxYr-OwnSeq is unique (foreing key) for OWNMLT table
     * -
     *
     * SALES :
     * - each parcel entries will be new SALES record.
     * - SALETYPE in SALES are different for each parcel but unknown at this time
     * - OLDDOWN is own1 from OWNDAT before overwriting
     * - always new record to SALES table and set SALES.SEQ to 0
     * - set SALES.OLDOWN2 with the prior owners OWNDAT.OWN2 data (do this any time OWNDAT.OWN2 has an entry) (new logic)
     * - set SALES.OWN2 with the new owners OWNDAT.OWN2 data (do this any time there is any new data going into OWNDAT.OWN2) (new logic)
     */
    public boolean LeonProcess(Leon payload) throws Exception {
        try {
            if (payload == null) { throw new CamaException("Empty payload."); }
            SaleData saleData = payload.getSaleData();
            if (saleData == null) { throw new CamaException("Empty sale date."); }

            UserData userData = payload.getUserData();

            BuyerAddressComponent buyerAddressComponent = saleData.getBuyer_address_component();
            List<BuyerNamesComponent> buyerNamesComponents = saleData.getBuyer_names_component();
            ParcelMatchCardsComponent parcelMatchCardsComponent = saleData.getParcel_match_cards_component();
            VacantOrImprovedLandTable landTable = saleData.getVacant_or_improved_land_table();

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
            int taxYear = saleData.getCama_tax_year();
            double stampAmount = saleData.getDocstamp_amount();
            int price = saleData.getDerived_sale_price_florida();
            Date saleDate = toSQLDate(saleData.getSale_date(), dateFormat);
            Date recordDate = toSQLDate(saleData.getRecorded_date(), dateFormat);
            String instrtype = saleData.getSale_instrument();
            int parcelCount = saleData.getTotal_parcel_count();
            String processor = null;
            if( userData != null) {
                processor = getProcessor(userData.getEmail());
            }

            int seq = 0;
            String source = "D";
            String steb = null;
            String hideName = "N";

            camaRepository.dbConn();

            for(MainParcel mainParcel : mainParcels) {
                int salesKey = camaRepository.getNextSeq();
                logger.debug("SalesKey: " + salesKey);
                if(salesKey < 1) { throw new CamaException("Something wrong with saleKey."); }

                HashMap<String, Object> oldOWNDAT = camaRepository.getOWNDAT(mainParcel.getParcelNumber(), taxYear);
                if(oldOWNDAT.isEmpty()) { throw new CamaException("Existing OWNDAT record was not found. "+ mainParcel.getParcelNumber() + "-"  + taxYear);}

                seq = (int) oldOWNDAT.get("seq");
                String oldown = (String) oldOWNDAT.get("own1");
                String oldown2 = (String) oldOWNDAT.get("own2");
                String saletype = getSaleType(landTable, mainParcel.getParcelNumber());
                logger.debug("new seq number: {} own1: {} own2 {}", seq, oldown, oldown2);

                camaRepository.updateOWNDAT(mainParcel, taxYear, book, page, salesKey, hideName, ++seq, firstBuyer, processor, buyerAddressComponent);
                camaRepository.insertSALE(mainParcel, saleDate, stampAmount, price, salesKey, book, page, oldown, firstBuyer.getFullName1(), saletype, source, steb, parcelCount, instrtype, recordDate, processor, oldown2, firstBuyer.getFullName2());
                camaRepository.deactivatOWNMLT(getcurrentDate(dateFormatmonth), mainParcel.getParcelNumber(), taxYear);

                int ownSEQ = camaRepository.getOWNMLT(mainParcel.getParcelNumber(), taxYear);
                for(int i = 1; buyerNamesComponents.size() > i; i++) {
                    logger.debug("buyerNamesComponent: " + i);
                    BuyerNamesComponent buyerNamesComponent = buyerNamesComponents.get(i);
                    String newown2 = buyerNamesComponent.getFullName2();

                    camaRepository.insertOWNMLT(mainParcel, taxYear, ++ownSEQ, book, page, salesKey, hideName, processor, buyerNamesComponent);
                }
            }
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

    private String getProcessor(String email) {
        String[] token = null;
        if(email.indexOf("@") > 0) {
            token = email.split("@");
            if (!StringUtils.isEmpty(token[0])) {
                return token[0];
            }
        }
        return null;
    }

    // TODO need to optimize this logic
    private String getSaleType(VacantOrImprovedLandTable landTable, String parcelNumber) {
        if(landTable == null) { return null; }

        for(List<String> value : landTable.getValues()) {
            if(parcelNumber == value.get(0)) {
                return value.get(1);
            }
        }
        return null;
    }
}

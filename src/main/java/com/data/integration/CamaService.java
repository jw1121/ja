package com.data.integration;

import com.data.integration.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CamaService {

    final static String dateFormat = "yyyy-MM-dd";

    @Autowired
    CamaRepository camaRepository;

    public boolean LeonProcess(Leon payload) throws Exception {
        try {
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

            String book = saleData.getBook();
            String page = saleData.getPage();
            int taxYear = saleData.getTax_year();
            if (taxYear < 999 || taxYear > 9999) { throw new CamaException("incorrect tax_year."); }
            double stampAmount = saleData.getDocstamp_amount();
            int price = saleData.getDerived_sale_price_florida();
            Date saleDate = toSQLDate(saleData.getSale_date(), dateFormat);
            Date recordDate = toSQLDate(saleData.getRecorded_date(), dateFormat);
            String instrtype = saleData.getSale_instrument();
            int parcelCount = saleData.getTotal_parcel_count();
            String source = "D";
            String steb = null;
            String hideName = "N";

            camaRepository.dbConn();

            //            camaRepository.checkOWNDAT(parid, taxYear);
            //            camaRepository.checkOWNMLT(parid, taxYear, ownseq);
            //            camaRepository.checkSALE();

            int salesKey = camaRepository.getNextSeq();
//            if(salesKey < 1) { throw new CamaException("Something wrong with saleKey."); }
//
//            for(int i = 0; mainParcels.size() > i; i++) {
//                MainParcel mainParcel = mainParcels.get(i);
//                if(camaRepository.doesRecordExist(mainParcel.getParcelNumber(), taxYear)) {
//                    // TODO delete OWNDAT and Deactivate OWNMLT
////                    camaRepository.deleteOWNDAT();
////                    camaRepository.deactivateOWNMLT();
//                }
//
//                camaRepository.insertOWNDAT(mainParcel, taxYear, book, page, salesKey, hideName, buyerNamesComponents.get(0), buyerAddressComponent);
//                BuyerNamesComponent buyerNamesComponent = buyerNamesComponents.get(i);
//                camaRepository.insertSALE(mainParcel, saleDate, taxYear, stampAmount, price, salesKey, book, page, buyerNamesComponent.getFullName1(), source, steb, parcelCount, instrtype, recordDate);
//
//                if(buyerNamesComponents.size() > 1) {
//                    camaRepository.insertOWNMLT(mainParcel, taxYear, book, page, salesKey, hideName, buyerNamesComponents);
//                }
//
//                //camaRepository.run();
//            }
//
//            camaRepository.commit();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    private Date toSQLDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        java.util.Date parsed = null;
        try {
            parsed = sdf.parse(date);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return new java.sql.Date(parsed.getTime());
    }
}

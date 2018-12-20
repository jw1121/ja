package com.data.integration;

import com.data.integration.model.Cama;
import com.data.integration.model.Owner;
import com.data.integration.model.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class CamaService {

    @Autowired
    CamaRepository camaRepository;

    public boolean LeonProcess(Cama payload) {
        boolean insert = false;

        try {
//            camaRepository.checkOWNDAT(parid, taxyr);
//            camaRepository.checkOWNMLT(parid, taxyr, ownseq);
//            camaRepository.checkSALE();

            List<Parcel> parcels = payload.getParcels();
            if(payload == null || parcels.isEmpty()) { return false; }

            int book = payload.getBook();
            int page = payload.getPage();
            int taxyr = payload.getTaxyr();
            double stampval = payload.getStampval();
            int price = payload.getPrice();
            String saledt = payload.getSaledt();
            String recorddt = payload.getRecorddt();
            String instrtyp = payload.getInstrtyp();
            int nopar = payload.getNopar();
            String source = payload.getSource();
            Object steb = payload.getSteb();

            for(Parcel parcel : parcels) {
                camaRepository.dbConn();

                if(camaRepository.doesRecordExist(parcel.getParid(), payload.getTaxyr())) {
                    // TODO delete OWNDAT and Deactivate OWNMLT
                    camaRepository.deleteOWNDAT();
                    camaRepository.deactivateOWNMLT();
                }

                List<Owner> owners = payload.getOwners();
                camaRepository.insertOWNDAT(parcel, taxyr, book, page, owners.get(0), payload.getMailing_Address());
                camaRepository.insertOWNMLT(parcel, taxyr, book, page, owners);
                camaRepository.insertSALE(payload);

                camaRepository.run();

            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}

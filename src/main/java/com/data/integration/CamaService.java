package com.data.integration;

import com.data.integration.model.Cama;
import com.data.integration.model.Owner;
import com.data.integration.model.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;

@Service
public class CamaService {

    @Autowired
    CamaRepository camaRepository;

    public boolean process(Cama payload) {
        try {
//            StringBuilder Owndat = new StringBuilder();
//            StringBuilder Ownmlt = new StringBuilder();
//            StringBuilder sale = new StringBuilder();

            List<Owner> owners = payload.getOwners();

            String owndat = "insert into OWNDAT () value ";
            String owndatValueSet = "(?,?,?)";
            String ownmlt = "insert into OWNMLT () value (?)";
            String sale = "insert into SALE () value (?)";

            for(int i = 0; i < owners.size(); i++) {
                owndat += owndatValueSet;
                if(i < owners.size() - 1) {
                    owndat += ", ";
                } else {
                    owndat += ";";
                }
            }

            PreparedStatement owner_ps = null; //con.prepareStatement(owndat);
            for (Owner owner : owners) {
                owner_ps.setInt(1, owner.getOwnseq());
            }

            List<Parcel> parcels = payload.getParcels();
            for (Parcel parcel : parcels) {

            }

            camaRepository.insertOWNDAT();
            //if there is multiple own use ownmlt
            camaRepository.insertOWNMLT();

            camaRepository.insertSALE();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

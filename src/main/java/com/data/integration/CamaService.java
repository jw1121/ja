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

    public boolean process(Cama payload) {
        try {
            camaRepository.dbConn();

            camaRepository.insertOWNDAT(payload);
            camaRepository.insertOWNMLT(payload);
            camaRepository.insertSALE(payload);

            camaRepository.run();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}

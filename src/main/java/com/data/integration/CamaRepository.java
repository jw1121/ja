package com.data.integration;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class CamaRepository {

    String dbURL = "jdbc:oracle:thin:@ENTRY_FROM_TNSNAMES";
    String username = "ja_api";
    String password = "Propapi$6200";
    String url = "jdbc:oracle:thin:@TEST";


    @Bean
    DataSource dataSource() throws SQLException {

        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
    }


    Connection conect() throws Exception {
        System.setProperty("oracle.net.tns_admin", "C:/Oracle12c/product/12.1.0/client_2/network/admin");
        //System.getenv();
        Class.forName("oracle.jdbc.OracleDriver");
       return DriverManager.getConnection(url, username, password);
    }

    Connection connection = null;

    public boolean insertOWNDAT() {
        return false;

    }

    public boolean insertOWNMLT() {
        return false;
    }

    public boolean insertSALE() {
        return false;
    }
}

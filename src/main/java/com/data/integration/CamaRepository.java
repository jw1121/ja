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

    static String username = "ja_api";
    static String password = "Propapi$6200";
    static String url = "jdbc:oracle:thin:@TEST";
    static String tns = "C:/Oracle12c/product/12.1.0/client_2/network/admin";

    Connection connection = null;

    public void dbConn(String[] args) {
        try {
            System.setProperty("oracle.net.tns_admin", tns);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection == null) {
            System.out.println("Failed to make connection!");
        }
    }

    Connection conect() throws Exception {
        System.setProperty("oracle.net.tns_admin", "C:/Oracle12c/product/12.1.0/client_2/network/admin");
        //System.getenv();
        Class.forName("oracle.jdbc.OracleDriver");
       return DriverManager.getConnection(url, username, password);
    }

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

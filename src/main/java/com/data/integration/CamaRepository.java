package com.data.integration;

import com.data.integration.model.Cama;
import com.data.integration.model.Mailing_Address;
import com.data.integration.model.Owner;
import com.data.integration.model.Parcel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CamaRepository {

    final static String OWNDAT = "INSERT INTO TEST.OWNDAT () values";
    final static String OWNMLT = "INSERT INTO TEST.OWNMLT () values";
    final static String SALE = "INSERT INTO TEST.SALE () values";

//    static String username = "ja_api";
//    static String password = "Propapi$6200";
//    static String url = "jdbc:oracle:thin:@TEST";
//    static String tns = "C:/Oracle12c/product/12.1.0/client_2/network/admin";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String pass;
    @Value("${spring.datasource.tns}")
    private String tns;
    @Value("${spring.datasource.object}")
    private String object;

    public void dbConn() {
        try {
            System.setProperty("oracle.net.tns_admin", tns);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, user, pass);
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

    public void run() throws SQLException {
//        Statement statement = connection.createStatement();

        preparedStatement.executeBatch();
        preparedStatement.close();
        connection.close();
    }

    public String getNextSeq() throws SQLException {
        ResultSet resultSet = statement.executeQuery("select ${object}.saleseq.nextval from dual;");
        String next = resultSet.getString(0);
        return next;
    }


    public void insertOWNDAT(Cama payload) throws SQLException {
        List<Owner> owners = payload.getOwners();
        List<Parcel> parcels = payload.getParcels();
        Mailing_Address address = payload.getMailing_Address();

        preparedStatement = connection.prepareStatement("");
        parcels.get(0).getParid();
        payload.getTaxyr();
        payload.getBook();
        payload.getPage();

        Owner owner = owners.get(0);
        owner.getOwnseq();
        owner.getOwn1();
        owner.getOwn2();
        owner.getPctown();
        owner.getOwntype1();
        owner.getOwntype2();
        owner.getOwntype3();
        owner.getOwntype4();
        owner.getHidename();
        owner.getMarstat();

        address.getCareof();
        address.getAddrtype();
        address.getAdrno();
        address.getAdradd();
        address.getAdrdir();
        address.getAdrstr();
        address.getAdrsuf();
        address.getAdrsuf2();
        address.getCityname();
        address.getStatecode();
        address.getCountry();
        address.getPostalcode();
        address.getUnitdesc();
        address.getUnitno();
        address.getAddr1();
        address.getAddr2();
        address.getAddr3();
        address.getZip1();
        address.getZip2();
        address.getUser4();

        preparedStatement.addBatch("");
    }

    public void insertOWNMLT(Cama payload) throws SQLException {
        List<Owner> owners = payload.getOwners();
        List<Parcel> parcels = payload.getParcels();
        Mailing_Address address = payload.getMailing_Address();

        parcels.get(0).getParid();
        payload.getTaxyr();
        payload.getBook();
        payload.getPage();

        for(int i = 1; i > owners.size(); i++) {
            Owner owner = owners.get(i);
            owner.getOwnseq();
            owner.getOwn1();
            owner.getOwn2();
            owner.getPctown();
            owner.getOwntype1();
            owner.getOwntype2();
            owner.getOwntype3();
            owner.getOwntype4();
            owner.getHidename();
            owner.getMarstat();

            statement.addBatch("");
        }
    }

    public void insertSALE(Cama payload) throws SQLException {
        List<Owner> owners = payload.getOwners();
        List<Parcel> parcels = payload.getParcels();
        Mailing_Address mailing_address = payload.getMailing_Address();

        for(Parcel parcel : parcels) {
            parcel.getParid();
            parcel.getSaletype();

            payload.getBook();
            payload.getPage();
            payload.getStampval();
            payload.getPrice();
            payload.getSaledt();
            payload.getRecorddt();
            payload.getInstrtyp();
            payload.getNopar();
            payload.getSource();
            payload.getSteb();

            owners.get(0).getOwn1();

            statement.addBatch("");
        }
    }
}

package com.data.integration;

import com.data.integration.model.BuyerAddressComponent;
import com.data.integration.model.BuyerNamesComponent;
import com.data.integration.model.MainParcel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.List;

@Repository
public class CamaRepository {

    final static String SQL_OWNDAT = "INSERT INTO TEST.OWNDAT (PARID, TAXYR, OWNSEQ, SEQ, OWN1, OWN2, CAREOF, ADDRTYPE, ADRNO, ADRADD, ADRDIR, ADRSTR, ADRSUF, ADRSUF2, CITYNAME, STATECODE, COUNTRY, POSTALCODE, UNITDESC, UNITNO, ADDR1, ADDR2, ADDR3, ZIP1, ZIP2, PCTOWN, SALEKEY, OWNTYPE1, OWNTYPE2, OWNTYPE3, OWNTYPE4, HIDENAME, MARSTAT, BOOK, PAGE, USER4, IASW_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SQL_OWNMLT = "INSERT INTO TEST.OWNMLT (PARID, TAXYR, OWNSEQ, SEQ, OWN1, OWN2, PCTOWN, SALEKEY, OWNTYPE1, OWNTYPE2, OWNTYPE3, OWNTYPE4, HIDENAME, MARSTAT, BOOK, PAGE, USER4, IASW_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SQL_SALE = "INSERT INTO TEST.SALE (PARID, SALEDT, STAMPVAL, PRICE, SEQ, SALEKEY, BOOK, PAGE, OLDOWN, OWN1, SOURCE, SALETYPE, STEB, NOPAR, INSTRTYP, RECORDDT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            connection.setAutoCommit(false);
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

    public void commit() throws SQLException {
        connection.commit();
    }

    public void run() throws SQLException {
//        Statement statement = connection.createStatement();

        preparedStatement.executeBatch();
        preparedStatement.close();
        connection.close();
    }

    public int getNextSeq() throws SQLException {
        ResultSet resultSet = statement.executeQuery("select ${object}.saleseq.nextval from dual;");
        int next = resultSet.getInt(1);
        return next;
    }


    public boolean doesRecordExist(String parid, int taxyr) throws SQLException {
        preparedStatement = connection.prepareStatement("");
        preparedStatement.setString(0, parid);
        preparedStatement.setInt(1, taxyr);
        ResultSet resultSet = statement.executeQuery("");

        while(resultSet.next()) {
            String tblName = resultSet.getString("tbl_name");
            if(StringUtils.isEmpty(tblName)) {
                return true;
            }
        }
        return false;
    }

    public void insertOWNDAT(MainParcel parcel, int taxyr, String book, String page, int saleskey, String hidename, BuyerNamesComponent buyer, BuyerAddressComponent address) throws SQLException {

        preparedStatement = connection.prepareStatement(SQL_OWNDAT);
        preparedStatement.setString(1, parcel.getParcelNumber());
        preparedStatement.setInt(2, taxyr);
        preparedStatement.setInt(3, buyer.getIndex());
//        preparedStatement.setInt(4, "SEQ"); // overwrite existing record and increase this number
        preparedStatement.setString(5, buyer.getFullName1());
        preparedStatement.setString(6, buyer.getFullName2());
        preparedStatement.setString(7, address.getBuyerAddressCareOf());
        preparedStatement.setString(8, address.getAddressCategory());
        preparedStatement.setInt(9, address.getBuyerAddressStreetNumber1());
        preparedStatement.setString(10, address.getBuyerAddressStreetNumber2());
        preparedStatement.setString(11, address.getBuyerAddressStreetDirectionalPrefix());
        preparedStatement.setString(12, address.getBuyerAddressStreetName());
        preparedStatement.setString(13, address.getBuyerAddressStreetSuffix());
        preparedStatement.setString(14, address.getBuyerAddressStreetDirectionalSuffix());
        preparedStatement.setString(15, address.getBuyerCity());
        preparedStatement.setString(16, address.getBuyerState());
        preparedStatement.setString(17, address.getBuyerCountry());
        preparedStatement.setString(18, address.getBuyerForeignPostalCode());
        preparedStatement.setString(19, address.getBuyerAddressSecondaryUnitDesignator());
        preparedStatement.setString(20, address.getBuyerAddressSecondaryUnitNumber());
        preparedStatement.setString(21, address.getBuyerAddress1());
        preparedStatement.setString(22, address.getBuyerAddress2());
        preparedStatement.setString(23, address.getBuyerAddress3());
        preparedStatement.setString(24, address.getBuyerZip());
        preparedStatement.setString(25, address.getBuyerZip4());
        preparedStatement.setDouble(26, buyer.getBuyerPercentage());
        preparedStatement.setInt(27, saleskey);
        preparedStatement.setString(28, buyer.getBuyerType());
        preparedStatement.setString(29, buyer.getBuyerType2());
        preparedStatement.setString(30, buyer.getBuyerType3());
        preparedStatement.setString(31, buyer.getBuyerType4());
        preparedStatement.setString(32, hidename);
        preparedStatement.setString(33, buyer.getBuyerMaritalStatus());
//        preparedStatement.setString(34, "");
        preparedStatement.setString(34, book);
        preparedStatement.setString(35, page);
        preparedStatement.setString(36, address.getBuyerMailingNotificationCode());

        preparedStatement.executeUpdate();
    }

    public void insertSALE(MainParcel parcel, Date saleDt, int taxyr, double stampval, int price, int saleKey, String book, String page, String own, String source, String steb, int nopar, String instrtype, Date recordDt) throws SQLException {
        preparedStatement = connection.prepareStatement(SQL_SALE);

        preparedStatement.setString(1, parcel.getParcelNumber());
        preparedStatement.setDate(2, saleDt);
        preparedStatement.setInt(2, taxyr);
        preparedStatement.setDouble(3, stampval);
        preparedStatement.setInt(4, price);
        preparedStatement.setInt(5, 0);
        preparedStatement.setInt(6, saleKey);
        preparedStatement.setString(7, book);
        preparedStatement.setString(8,page);
//            preparedStatement.setString(9, "OLDOWN");
        preparedStatement.setString(10, own); //not right
        preparedStatement.setString(11, source);
        //preparedStatement.setString(12, parcel.getSaletype());
        preparedStatement.setString(13, steb);
        preparedStatement.setInt(14, nopar);
        preparedStatement.setString(15, instrtype);
        preparedStatement.setDate(16, recordDt);

        preparedStatement.executeUpdate();
    }

    public void insertOWNMLT(MainParcel parcel, int taxyr, String book, String page, int saleskey, String hidename, List <BuyerNamesComponent> buyers) throws SQLException {

        preparedStatement = connection.prepareStatement(SQL_OWNMLT);

        for(int i = 1; i > buyers.size(); i++) {
            BuyerNamesComponent buyer = buyers.get(i);

            preparedStatement.setString(1, parcel.getParcelNumber());
            preparedStatement.setInt(2, taxyr);
            preparedStatement.setInt(3, buyer.getIndex());
//            preparedStatement.setInt(4, "SEQ");
            preparedStatement.setString(5, buyer.getFullName1());
            preparedStatement.setString(6, buyer.getFullName2());
            preparedStatement.setDouble(7, buyer.getBuyerPercentage());
            preparedStatement.setInt(8, saleskey);
            preparedStatement.setString(9, buyer.getBuyerType());
            preparedStatement.setString(10, buyer.getBuyerType2());
            preparedStatement.setString(11, buyer.getBuyerType3());
            preparedStatement.setString(12, buyer.getBuyerType4());
            preparedStatement.setString(13, hidename);
            preparedStatement.setString(14, buyer.getBuyerMaritalStatus());
            preparedStatement.setString(15, book);
            preparedStatement.setString(16, page);
            preparedStatement.setString(17, page);

            preparedStatement.addBatch();
        }
        preparedStatement.execute();
    }


}

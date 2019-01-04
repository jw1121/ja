package com.data.integration;

import com.data.integration.model.BuyerAddressComponent;
import com.data.integration.model.BuyerNamesComponent;
import com.data.integration.model.MainParcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;

@Repository
public class CamaRepository {
    final static Logger logger = LoggerFactory.getLogger(CamaRepository.class);

    final static String SQL_OWNDAT = "INSERT INTO TEST.OWNDAT (PARID, TAXYR, OWNSEQ, SEQ, OWN1, OWN2, CAREOF, ADDRTYPE, ADRNO, ADRADD, ADRDIR, ADRSTR, ADRSUF, ADRSUF2, CITYNAME, STATECODE, COUNTRY, POSTALCODE, UNITDESC, UNITNO, ADDR1, ADDR2, ADDR3, ZIP1, ZIP2, PCTOWN, SALEKEY, OWNTYPE1, OWNTYPE2, OWNTYPE3, OWNTYPE4, HIDENAME, MARSTAT, BOOK, PAGE, USER4) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SQL_OWNDAT_UPDATE = "UPDATE TEST.OWNDAT SET PARID = ?, TAXYR = ?, OWNSEQ = ?, SEQ = ?, OWN1 = ?, OWN2 = ?, CAREOF = ?, ADDRTYPE = ?, ADRNO = ?, ADRADD = ?, ADRDIR = ?, ADRSTR = ?, ADRSUF = ?, ADRSUF2 = ?, CITYNAME = ?, STATECODE = ?, COUNTRY = ?, POSTALCODE = ?, UNITDESC = ?, UNITNO = ?, ADDR1 = ?, ADDR2 = ?, ADDR3 = ?, ZIP1 = ?, ZIP2 = ?, PCTOWN = ?, SALEKEY = ?, OWNTYPE1 = ?, OWNTYPE2 = ?, OWNTYPE3 = ?, OWNTYPE4 = ?, HIDENAME = ?, MARSTAT = ?, BOOK = ?, PAGE = ?, USER4 = ? WHERE PARID = ? AND TAXYR = ?";
    final static String SQL_OWNMLT = "INSERT INTO TEST.OWNMLT (PARID, TAXYR, OWNSEQ, SEQ, OWN1, OWN2, PCTOWN, SALEKEY, OWNTYPE1, OWNTYPE2, OWNTYPE3, OWNTYPE4, HIDENAME, MARSTAT, BOOK, PAGE, USER4) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SQL_OWNMLT_UPDATE = "UPDATE TEST.OWNMLT SET deactivat = ? WHERE PARID = ? AND TAXYR = ? AND OWNSEQ = ? ";
    final static String SQL_SALES = "INSERT INTO TEST.SALES (PARID, SALEDT, STAMPVAL, PRICE, SEQ, SALEKEY, BOOK, PAGE, OLDOWN, OWN1, SOURCE, SALETYPE, STEB, NOPAR, INSTRTYP, RECORDDT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    Connection connection = null;
    //Statement statement = null;
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
            logger.debug("DB connection.");
            System.setProperty("oracle.net.tns_admin", tns);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            logger.debug("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        } catch (SQLException e) {
            logger.debug("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        if (connection == null) {
            logger.debug("Failed to make connection!");
        }
    }

    public void commit() throws SQLException {
        logger.debug("start commit");
        if(connection != null) {
            logger.debug("connection was used.");
            connection.commit();
            logger.debug("finish commit");
            connection.close();
            logger.debug("close commit");
        }
    }

    public int getNextSeq() throws SQLException {
        logger.debug("getNextSeq method");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select TEST.saleseq.nextval as val from dual");
        resultSet.next();
        int next = resultSet.getInt("val");
        return next;
    }

    public HashMap<String, Object> getOWNDAT(String parid, int taxyr) throws SQLException {
        logger.debug("getOWNDAT method");
        preparedStatement = connection.prepareStatement("SELECT * FROM TEST.OWNDAT WHERE PARID = ? AND TAXYR = ? ");
        preparedStatement.setString(1, parid);
        preparedStatement.setInt(2, taxyr);
        ResultSet resultSet = preparedStatement.executeQuery();

        HashMap<String, Object> result = new HashMap<>();
        int rowCount = 0;
        logger.debug("getOWNDAT result. " + parid + ",  " + taxyr);

        while(resultSet.next()) {
            logger.debug("found existing owndat. " + resultSet.getInt("SEQ") + ", " + resultSet.getString("OWN1"));
            result.put("seq", resultSet.getInt("SEQ"));
            result.put("oldown", resultSet.getString("OWN1"));
            rowCount++;
        }
        if(rowCount > 1) {
            new CamaException("Multiple OWNDAT record was found:" + parid + "-"  + taxyr);
        }

        return result;
    }

    public void deactivatOWNMLT(Date date, String parid, int taxyr, int ownseq) throws SQLException {
        logger.debug("deactivatOWNMLT method");
        logger.debug("inputs: " + parid + ", " + taxyr + ", " + ownseq);

        preparedStatement = connection.prepareStatement(SQL_OWNMLT_UPDATE);
        preparedStatement.setDate(1, date);
        preparedStatement.setString(2, parid);
        preparedStatement.setInt(3, taxyr);
        preparedStatement.setInt(4, ownseq);
        int resultSet = preparedStatement.executeUpdate();
    }

    public void updateOWNDAT(MainParcel parcel, int taxyr, String book, String page, int saleskey, String hidename, int seq, BuyerNamesComponent buyer, BuyerAddressComponent address) throws SQLException {
        logger.debug("updateOWNDAT method");

        preparedStatement = connection.prepareStatement(SQL_OWNDAT_UPDATE);
        preparedStatement.setString(1, parcel.getParcelNumber());
        preparedStatement.setInt(2, taxyr);
        preparedStatement.setInt(3, buyer.getIndex());
        preparedStatement.setInt(4, seq); // overwrite existing record and increase this number
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
        preparedStatement.setString(34, book);
        preparedStatement.setString(35, page);
        preparedStatement.setString(36, address.getBuyerMailingNotificationCode());
        preparedStatement.setString(37, parcel.getParcelNumber());
        preparedStatement.setInt(38, taxyr);

        preparedStatement.executeUpdate();
    }

    public void insertSALE(MainParcel parcel, Date saleDt, double stampval, int price, int saleKey, String book, String page, String oldown, String own, String source, String steb, int nopar, String instrtype, Date recordDt) throws SQLException {
        logger.debug("insertSALE method");
        logger.debug("inputs: " + saleDt + ", "+  stampval + ", "+  price + ", "+  saleKey + ", "+  book + ", "+   page + ", "+
                oldown + ", "+  own + ", "+ source + ", "+  steb + ", "+  nopar + ", "+  instrtype + ", "+  recordDt);

        preparedStatement = connection.prepareStatement(SQL_SALES);
        preparedStatement.setString(1, parcel.getParcelNumber());
        preparedStatement.setDate(2, saleDt);
        preparedStatement.setDouble(3, stampval);
        preparedStatement.setInt(4, price);
        preparedStatement.setInt(5, 0);
        preparedStatement.setInt(6, saleKey);
        preparedStatement.setString(7, book);
        preparedStatement.setString(8,page);
        preparedStatement.setString(9, oldown);
        preparedStatement.setString(10, own); //not right
        preparedStatement.setString(11, source);
        preparedStatement.setString(12, "t"); //parcel.getSaletype());
        preparedStatement.setString(13, steb);
        preparedStatement.setInt(14, nopar);
        preparedStatement.setString(15, instrtype);
        preparedStatement.setDate(16, recordDt);

        preparedStatement.execute();
    }

    public void insertOWNMLT(MainParcel parcel, int taxyr, int seq, String book, String page, int saleskey, String hidename, BuyerNamesComponent buyer) throws SQLException {
        logger.debug("insertOWNMLT method");

        preparedStatement = connection.prepareStatement(SQL_OWNMLT);
        preparedStatement.setString(1, parcel.getParcelNumber());
        preparedStatement.setInt(2, taxyr);
        preparedStatement.setInt(3, buyer.getIndex());
        preparedStatement.setInt(4, seq);
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
        preparedStatement.setString(17, ""); // <- user4

        preparedStatement.execute();
    }
}

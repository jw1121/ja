package com.data.integration;

import com.data.integration.model.BuyerAddressComponent;
import com.data.integration.model.BuyerNamesComponent;
import com.data.integration.model.MainParcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

import static com.data.integration.validation.Constant.*;

@Repository
@Scope("prototype")
final public class CamaRepository {
    final static Logger logger = LoggerFactory.getLogger(CamaRepository.class);

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

    final static String SQL_OWNDAT = "INSERT INTO {}.OWNDAT (PARID, TAXYR, OWNSEQ, SEQ, OWN1, OWN2, CAREOF, ADDRTYPE, ADRNO, ADRADD, ADRDIR, ADRSTR, ADRSUF, ADRSUF2, CITYNAME, STATECODE, COUNTRY, POSTALCODE, UNITDESC, UNITNO, ADDR1, ADDR2, ADDR3, ZIP1, ZIP2, PCTOWN, SALEKEY, OWNTYPE1, OWNTYPE2, OWNTYPE3, OWNTYPE4, HIDENAME, MARSTAT, BOOK, PAGE, USER4) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SQL_OWNDAT_UPDATE = "UPDATE {}.OWNDAT SET PARID = ?, TAXYR = ?, OWNSEQ = ?, SEQ = ?, OWN1 = ?, OWN2 = ?, CAREOF = ?, ADDRTYPE = ?, ADRNO = ?, ADRADD = ?, ADRDIR = ?, ADRSTR = ?, ADRSUF = ?, ADRSUF2 = ?, CITYNAME = ?, STATECODE = ?, COUNTRY = ?, POSTALCODE = ?, UNITDESC = ?, UNITNO = ?, ADDR1 = ?, ADDR2 = ?, ADDR3 = ?, ZIP1 = ?, ZIP2 = ?, PCTOWN = ?, SALEKEY = ?, OWNTYPE1 = ?, OWNTYPE2 = ?, OWNTYPE3 = ?, OWNTYPE4 = ?, HIDENAME = ?, MARSTAT = ?, OWNNUM = ?,BOOK = ?, PAGE = ?, USER4 = ?, USER8 = ? WHERE PARID = ? AND TAXYR = ?";
    final static String SQL_OWNMLT = "INSERT INTO {}.OWNMLT (PARID, TAXYR, OWNSEQ, OWN1, OWN2, PCTOWN, SALEKEY, OWNTYPE1, OWNTYPE2, OWNTYPE3, OWNTYPE4, HIDENAME, MARSTAT, BOOK, PAGE, USER8) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final static String SQL_OWNMLT_SELECT = "SELECT MAX(OWNSEQ) FROM {}.OWNMLT WHERE PARID = ? AND TAXYR = ?";
    final static String SQL_OWNMLT_UPDATE = "UPDATE {}.OWNMLT SET deactivat = ? WHERE PARID = ? AND TAXYR = ? ";
    final static String SQL_SALES = "INSERT INTO {}.SALES (PARID, SALEDT, STAMPVAL, PRICE, SEQ, SALEKEY, BOOK, PAGE, OLDOWN, OWN1, SOURCE, SALETYPE, STEB, NOPAR, INSTRTYP, RECORDDT, USER11, OLDOWN2, OWN2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    Connection connection = null;

    final public void dbConn() {
        //TODO manager
        try {
            logger.debug("DB connection.");
            System.setProperty("oracle.net.tns_admin", tns);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new CamaException("49999", "Could not connect to the database", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CamaException("49999", "Could not connect to the database", e);
        }
        if (connection == null) {
            logger.debug("Failed to make connection!");
        }
    }

    final public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    final private void close(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    final public void commit() throws SQLException {
        logger.debug("start commit");
        if(connection != null) {
            logger.debug("connection was used.");
            connection.commit();
            logger.debug("finish commit");
        }
    }

    final public int getNextSeq() throws SQLException {
        logger.debug("getNextSeq method");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select {}.saleseq.nextval as val from dual".replace("{}", object));
        resultSet.next();
        int next = resultSet.getInt("val");

        statement.close();

        return next;
    }

    final public HashMap<String, Object> getOWNDAT(final String parid, final int taxyr) throws SQLException {
        logger.debug("getOWNDAT method");
        logger.info("getOWNDAT with " + parid + ",  " + taxyr);

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM {}.OWNDAT WHERE PARID = ? AND TAXYR = ? ".replace("{}", object));
            preparedStatement.setString(1, parid);
            preparedStatement.setInt(2, taxyr);
            ResultSet resultSet = preparedStatement.executeQuery();

            HashMap<String, Object> result = new HashMap<>();
            int rowCount = 0;

            while (resultSet.next()) {
                logger.debug("found existing owndat. " + resultSet.getInt("SEQ") + ", " + resultSet.getString("OWN1"));
                result.put("seq", resultSet.getInt("SEQ"));
                result.put("own1", resultSet.getString("OWN1"));
                result.put("own2", resultSet.getString("OWN2"));
                rowCount++;
            }
            if (rowCount > 1) {
                new CamaException(UnknownErrorCode, "Multiple OWNDAT record was found:" + parid + "-" + taxyr);
            }

            return result;
        } finally {
            close(preparedStatement);
        }
    }

    final public void deactivatOWNMLT(final String date, final String parid, final int taxyr) throws SQLException {
        logger.debug("deactivatOWNMLT method");
        logger.info("inputs: " + parid + ", " + taxyr);

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_OWNMLT_UPDATE.replace("{}", object));
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, parid);
            preparedStatement.setInt(3, taxyr);
            int resultSet = preparedStatement.executeUpdate();
        } finally {
            close(preparedStatement);
        }
    }

    final public void updateOWNDAT(final MainParcel parcel, final int taxyr, final String book, final String page, final int saleskey, final String hidename, final int seq, final BuyerNamesComponent buyer, final String processor, final BuyerAddressComponent address) throws SQLException {
        logger.debug("updateOWNDAT method");
        logger.info("updateOWNDAT with " + parcel.getParcelNumber() + ", " + taxyr + ", " + buyer.getId() + ", " + seq);
        logger.info(buyer.toString() + address.toString());
        logger.info(saleskey + ", " + hidename + ", " + book + ", " + page);

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_OWNDAT_UPDATE.replace("{}", object));
            preparedStatement.setString(1, parcel.getParcelNumber());
            preparedStatement.setInt(2, taxyr);
            preparedStatement.setInt(3, buyer.getId());
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
            preparedStatement.setDouble(26, buyer.getBuyerPercentage() * 100);
            preparedStatement.setInt(27, saleskey);
            preparedStatement.setString(28, buyer.getBuyerType());
            preparedStatement.setString(29, buyer.getBuyerType2());
            preparedStatement.setString(30, buyer.getBuyerType3());
            preparedStatement.setString(31, buyer.getBuyerType4());
            preparedStatement.setString(32, hidename);
            preparedStatement.setString(33, buyer.getBuyerMaritalStatus());
            preparedStatement.setString(34, null);
            preparedStatement.setString(35, book);
            preparedStatement.setString(36, page);
            preparedStatement.setString(37, address.getBuyerMailingNotificationCode());
            preparedStatement.setString(38, processor);
            preparedStatement.setString(39, parcel.getParcelNumber());
            preparedStatement.setInt(40, taxyr);

            preparedStatement.executeUpdate();
        } finally {
            close(preparedStatement);
        }
    }

    final public void updateOWNDAT2(final MainParcel parcel, final int taxyr, final String book, final String page, final int saleskey, final String hidename, final int seq, final BuyerNamesComponent buyer, final String processor, final BuyerAddressComponent address) throws SQLException {
        logger.debug("updateOWNDAT2 method");
        logger.info("updateOWNDAT2 with " + parcel.getParcelNumber() + ", " + taxyr + ", " + buyer.getId() + ", " + seq);
        logger.info(buyer.toString() + address.toString());
        logger.info(saleskey + ", " + hidename + ", " + book + ", " + page);

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_OWNDAT_UPDATE.replace("{}", object));
            preparedStatement.setString(1, parcel.getParcelNumber());
            preparedStatement.setInt(2, taxyr);
            preparedStatement.setInt(3, buyer.getId());
            preparedStatement.setInt(4, seq); // overwrite existing record and increase this number
            preparedStatement.setString(5, buyer.getFullName1());
            preparedStatement.setString(6, buyer.getFullName2());
            preparedStatement.setString(7, address.getBuyerAddressCareOf());
            preparedStatement.setString(8, address.getAddressCategory());
            preparedStatement.setNull(9, Types.INTEGER);
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
            preparedStatement.setDouble(26, buyer.getBuyerPercentage() * 100);
            preparedStatement.setInt(27, saleskey);
            preparedStatement.setString(28, buyer.getBuyerType());
            preparedStatement.setString(29, buyer.getBuyerType2());
            preparedStatement.setString(30, buyer.getBuyerType3());
            preparedStatement.setString(31, buyer.getBuyerType4());
            preparedStatement.setString(32, hidename);
            preparedStatement.setString(33, buyer.getBuyerMaritalStatus());
            preparedStatement.setString(34, null);
            preparedStatement.setString(35, book);
            preparedStatement.setString(36, page);
            preparedStatement.setString(37, address.getBuyerMailingNotificationCode());
            preparedStatement.setString(38, processor);
            preparedStatement.setString(39, parcel.getParcelNumber());
            preparedStatement.setInt(40, taxyr);

            preparedStatement.executeUpdate();
        } finally {
            close(preparedStatement);
        }
    }

    final public void insertSALE(final MainParcel parcel, final LocalDate saleDt, final double stampval, final int price, final int saleKey, final String book, final String page, final String oldown, final String own, final String saletype, final String source, final String steb, final int nopar, final String instrtype, final LocalDate recordDt, final String processor, final String oldown2, final String own2) throws SQLException {
        logger.debug("insertSALE method");
        logger.info("insertSALE inputs: " + saleDt + ", "+ stampval + ", "+ price + ", "+ saleKey + ", "+ book + ", "+ page + ", "+ oldown + ", "+ own + ", "+ saletype + ", "+ source + ", "+ steb + ", "+ nopar + ", "+ instrtype + ", "+ recordDt + ", "+ processor + ", "+ oldown2 + ", "+ own2);

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SALES.replace("{}", object));
            preparedStatement.setString(1, parcel.getParcelNumber());
            preparedStatement.setObject(2, saleDt);
            preparedStatement.setDouble(3, stampval);
            preparedStatement.setInt(4, price);
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, saleKey);
            preparedStatement.setString(7, book);
            preparedStatement.setString(8, page);
            preparedStatement.setString(9, oldown);
            preparedStatement.setString(10, own); //not right
            preparedStatement.setString(11, source);
            preparedStatement.setString(12, saletype); //parcel.getSaletype());
            preparedStatement.setString(13, steb);
            preparedStatement.setInt(14, nopar);
            preparedStatement.setString(15, instrtype);
            preparedStatement.setObject(16, recordDt);
            preparedStatement.setString(17, processor);
            preparedStatement.setString(18, oldown2);
            preparedStatement.setString(19, own2);

            preparedStatement.execute();
        } finally {
            close(preparedStatement);
        }
    }

    final public int getOWNMLT(final String parid, final int taxyr) throws SQLException {
        logger.debug("getOWNMLT method");
        logger.info("getOWNMLT using. " + parid + ",  " + taxyr);

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_OWNMLT_SELECT.replace("{}", object));
            preparedStatement.setString(1, parid);
            preparedStatement.setInt(2, taxyr);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int result = resultSet.getInt(1);
            logger.debug("found existing owndat. " + result);

            return result;
        } finally {
            close(preparedStatement);
        }
    }

    final public void insertOWNMLT(final MainParcel parcel, final int taxyr, final int ownseq, final String book, final String page, final int saleskey, final String hidename, final String processor, final BuyerNamesComponent buyer) throws SQLException {
        logger.debug("insertOWNMLT method");
        logger.info("insertOWNMLT " + parcel.getParcelNumber() + ", " + taxyr + ", " + ownseq + ", " + book + ", " + page + ", " + saleskey + ", " + hidename);
        logger.info(buyer.toString());

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_OWNMLT.replace("{}", object));
            preparedStatement.setString(1, parcel.getParcelNumber());
            preparedStatement.setInt(2, taxyr);
            preparedStatement.setInt(3, ownseq);
            preparedStatement.setString(4, buyer.getFullName1());
            preparedStatement.setString(5, buyer.getFullName2());
            preparedStatement.setDouble(6, buyer.getBuyerPercentage() * 100);
            preparedStatement.setInt(7, saleskey);
            preparedStatement.setString(8, buyer.getBuyerType());
            preparedStatement.setString(9, buyer.getBuyerType2());
            preparedStatement.setString(10, buyer.getBuyerType3());
            preparedStatement.setString(11, buyer.getBuyerType4());
            preparedStatement.setString(12, hidename);
            preparedStatement.setString(13, buyer.getBuyerMaritalStatus());
            preparedStatement.setString(14, book);
            preparedStatement.setString(15, page);
            preparedStatement.setString(16, processor);

            preparedStatement.execute();
        } finally {
            close(preparedStatement);
        }
    }
}

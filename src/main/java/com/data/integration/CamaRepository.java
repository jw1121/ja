package com.data.integration;

import com.data.integration.model.Cama;
import com.data.integration.model.Mailing_Address;
import com.data.integration.model.Owner;
import com.data.integration.model.Parcel;
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

    public void insertOWNDAT(Parcel parcel, int taxyr, String book, String page, int saleskey, Owner owner, Mailing_Address address) throws SQLException {

        preparedStatement = connection.prepareStatement(SQL_OWNDAT);
        preparedStatement.setString(1, parcel.getParid());
        preparedStatement.setInt(2, taxyr);
        preparedStatement.setInt(3, owner.getOwnseq());
//        preparedStatement.setInt(4, "SEQ");
        preparedStatement.setString(5, owner.getOwn1());
        preparedStatement.setString(6, owner.getOwn2());
        preparedStatement.setString(7, address.getCareof());
        preparedStatement.setString(8, address.getAddrtype());
        preparedStatement.setInt(9, address.getAdrno());
        preparedStatement.setString(10, address.getAdradd());
        preparedStatement.setString(11, address.getAdrdir());
        preparedStatement.setString(12, address.getAdrstr());
        preparedStatement.setString(13, address.getAdrsuf());
        preparedStatement.setString(14, address.getAdrsuf2());
        preparedStatement.setString(15, address.getCityname());
        preparedStatement.setString(16, address.getStatecode());
        preparedStatement.setString(17, address.getCountry());
        preparedStatement.setString(18, address.getPostalcode());
        preparedStatement.setString(19, address.getUnitdesc());
        preparedStatement.setString(20, address.getUnitno());
        preparedStatement.setString(21, address.getAddr1());
        preparedStatement.setString(22, address.getAddr2());
        preparedStatement.setString(23, address.getAddr3());
        preparedStatement.setString(24, address.getZip1());
        preparedStatement.setString(25, address.getZip2());
        preparedStatement.setDouble(26, owner.getPctown());
        preparedStatement.setInt(27, saleskey);
        preparedStatement.setString(28, owner.getOwntype1());
        preparedStatement.setString(29, owner.getOwntype2());
        preparedStatement.setString(30, owner.getOwntype3());
        preparedStatement.setString(31, owner.getOwntype4());
        preparedStatement.setString(32, owner.getHidename());
        preparedStatement.setString(33, owner.getMarstat());
//        preparedStatement.setString(34, "");
        preparedStatement.setString(34, book);
        preparedStatement.setString(35, page);
        preparedStatement.setString(36, address.getUser4());

        preparedStatement.addBatch();
    }

    public void insertOWNMLT(Parcel parcel, int taxyr, String book, String page, int saleskey, List<Owner> owners) throws SQLException {

        preparedStatement = connection.prepareStatement(SQL_OWNDAT);

        for(int i = 1; i > owners.size(); i++) {
            Owner owner = owners.get(i);

            preparedStatement.setString(1, parcel.getParid());
            preparedStatement.setInt(2, taxyr);
            preparedStatement.setInt(3, owner.getOwnseq());
//            preparedStatement.setInt(4, "SEQ");
            preparedStatement.setString(5, owner.getOwn1());
            preparedStatement.setString(6, owner.getOwn2());
            preparedStatement.setDouble(7, owner.getPctown());
            preparedStatement.setInt(8, saleskey);
            preparedStatement.setString(9, owner.getOwntype1());
            preparedStatement.setString(10, owner.getOwntype2());
            preparedStatement.setString(11, owner.getOwntype3());
            preparedStatement.setString(12, owner.getOwntype4());
            preparedStatement.setString(13, owner.getHidename());
            preparedStatement.setString(14, owner.getMarstat());
            preparedStatement.setString(15, book);
            preparedStatement.setString(16, page);
            preparedStatement.setString(17, page);

            preparedStatement.addBatch();
        }
    }

    public void insertSALE(Cama payload, int saleKey) throws SQLException {
        List<Owner> owners = payload.getOwners();

        for(Parcel parcel : payload.getParcels()) {

            preparedStatement.setString(1, parcel.getParid());
//            preparedStatement.setDate(2, payload.getSaledt());
            preparedStatement.setInt(2, payload.getTaxyr());
            preparedStatement.setDouble(3, payload.getStampval());
            preparedStatement.setInt(4, payload.getPrice());
//            preparedStatement.setInt(5, "SEg");
            preparedStatement.setInt(6, saleKey);
            preparedStatement.setDouble(7, payload.getBook());
            preparedStatement.setInt(8, payload.getPage());
//            preparedStatement.setString(9, "OLDOWN");
            preparedStatement.setString(10, payload.getOwners().get(0).getOwn1()); //not right
            preparedStatement.setString(11, payload.getSource());
            preparedStatement.setString(12, parcel.getSaletype());
            preparedStatement.setString(13, payload.getSteb());
            preparedStatement.setInt(14, payload.getNopar());
            preparedStatement.setString(15, payload.getInstrtyp());
//            preparedStatement.setDate(16, payload.getRecorddt());

            preparedStatement.addBatch();
        }
    }
}

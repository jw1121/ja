import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBconn {

    static String dbURL = "jdbc:oracle:thin:@ENTRY_FROM_TNSNAMES";
    static String username = "ja_api";
    static String password = "Propapi$6200";
    static String url = "jdbc:oracle:thin:@TEST";

    public static void main(String[] args) {
        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {
            System.setProperty("oracle.net.tns_admin", "C:/Oracle12c/product/12.1.0/client_2/network/admin");
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}

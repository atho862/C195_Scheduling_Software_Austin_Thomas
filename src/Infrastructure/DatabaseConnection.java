package Infrastructure;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection dbConnection = null;

    private static final String protocol = "jdbc";
    private static final String vendorName = "mysql";
    private static final String ipAddress = "3.227.166.251";
    private static final String databaseName = "U06cRO";

    private static final String username = "U06cRO";
    private static final String password = "53688728358";

    public static Connection connectToDatabase() {
        try {
            dbConnection = (Connection) DriverManager.getConnection(buildJdbcUrl(), username, password);
            System.out.println("Connection Successful");
            return dbConnection;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static String buildJdbcUrl() {
        return protocol + ":" + vendorName + "://" + ipAddress + "/" + databaseName;
    }
}

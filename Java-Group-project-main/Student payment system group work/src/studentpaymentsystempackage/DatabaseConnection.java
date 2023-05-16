
package studentpaymentsystempackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author abel
 */
public class DatabaseConnection {
    public static Connection connectionDB() {
        try {

            // Class.forName("org.sqlite.JDBC");
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection connection =
            // DriverManager.getConnection("jdbc:sqlite:C://sqlite//StudentPaymentManagementSystem.db");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/studentpaymentmanagementsystem",
                    "root", ""); // root is the default username while empty or null to the password

            return connection;

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/bank_application";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "deeprock25";
    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}

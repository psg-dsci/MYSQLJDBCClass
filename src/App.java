import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/d5";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTable(Connection conn) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(255) NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table 'users' created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(Connection conn, String name) {
        String insertSQL = "INSERT INTO users (name) VALUES ('" + name + "')";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(insertSQL);
            System.out.println("Data inserted into 'users' table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Connected to database!");
            createTable(conn);
            insertData(conn, "John Doe");
            insertData(conn, "Jane Smith");
        } else {
            System.out.println("Failed to connect to database.");
        }
    }
}

import java.sql.*;

public class DBConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            // Set up the connection details (URL, user, password)
            String url = "jdbc:mysql://localhost:3306/library_management";
            String user = "root"; // Replace with your actual database username
            String password = "umabharathi@12345"; // Replace with your actual database password

            // Try to establish the connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            // Print the error and provide details for troubleshooting
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}

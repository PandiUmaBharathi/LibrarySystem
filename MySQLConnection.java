import java.sql.*;

public class MySQLConnection {
    public static void main(String[] args) {
        // JDBC URL for MySQL database
        String url = "jdbc:mysql://localhost:3306/library_management"; // Replace with your DB details
        String username = "root"; // MySQL username
        String password = "umabharathi@12345"; // MySQL password

        // Connection object
        Connection connection = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            connection = DriverManager.getConnection(url, username, password);

            // Check connection status
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        } finally {
            try {
                // Close the connection if open
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

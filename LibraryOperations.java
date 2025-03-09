import java.sql.*;

public class LibraryOperations {

    public static void main(String[] args) {
        LibraryOperations operations = new LibraryOperations();

        // Example: adding a new book
        Book newBook = new Book(1, "Java Programming", "John Doe", "TechPublisher", 10);
        operations.addBook(newBook);
        
        // Display books
        operations.displayBooks();
        
        // Issue a book
        operations.issueBook(1, 1);
        
        // Return a book
        operations.returnBook(1);
        
        // Login user
        boolean isLoggedIn = operations.login("john.doe@example.com", "password123");
        if (isLoggedIn) {
            System.out.println("User logged in successfully!");
        } else {
            System.out.println("Login failed!");
        }
    }

    // Method to add a book to the library
    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, publisher, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getQuantity());
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to display all books in the library
    public void displayBooks() {
        String query = "SELECT * FROM books";
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            System.out.println("Books in the Library:");
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int year = rs.getInt("year");
                int quantity = rs.getInt("quantity");
                System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Publisher: " + publisher + ", Year: " + year + ", Quantity: " + quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to issue a book to a user
    public void issueBook(int userId, int bookId) {
        String query = "INSERT INTO transactions (user_id, book_id, issue_date) VALUES (?, ?, NOW())";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
            System.out.println("Book issued successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to return a book by updating the transaction record
    public void returnBook(int transactionId) {
        String query = "UPDATE transactions SET return_date = NOW() WHERE transaction_id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, transactionId);
            stmt.executeUpdate();
            System.out.println("Book returned successfully!"); // Fixed the unclosed string
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to login user with email and password
    public boolean login(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Invalid email or password.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

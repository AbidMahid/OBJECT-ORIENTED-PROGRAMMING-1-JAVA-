import java.sql.*;

public class JDBCDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    // Create table
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                     "id INT PRIMARY KEY AUTO_INCREMENT," +
                     "name VARCHAR(100) NOT NULL," +
                     "price DECIMAL(10,2)," +
                     "quantity INT)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Create (INSERT)
    public static void insertProduct(String name, double price, int quantity) {
        String sql = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
            System.out.println("Product inserted: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Read (SELECT)
    public static void readProducts() {
        String sql = "SELECT * FROM products";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\nAll Products:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Price: $%.2f, Quantity: %d%n",
                    rs.getInt("id"), rs.getString("name"), 
                    rs.getDouble("price"), rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Update
    public static void updateProduct(int id, double newPrice) {
        String sql = "UPDATE products SET price = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, id);
            int affected = pstmt.executeUpdate();
            System.out.println("Updated " + affected + " product(s)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Delete
    public static void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affected = pstmt.executeUpdate();
            System.out.println("Deleted " + affected + " product(s)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        createTable();
        insertProduct("Laptop", 999.99, 10);
        insertProduct("Mouse", 29.99, 50);
        readProducts();
        updateProduct(1, 899.99);
        readProducts();
        deleteProduct(2);
        readProducts();
    }
}
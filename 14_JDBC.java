import java.sql.*;
import java.util.Vector;

class ConnectionPool {
    private Vector<Connection> connections = new Vector<>();
    private String url, user, password;
    private int maxConnections;
    
    public ConnectionPool(String url, String user, String password, int maxConnections) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxConnections = maxConnections;
        
        for (int i = 0; i < maxConnections; i++) {
            connections.add(createConnection());
        }
    }
    
    private Connection createConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public synchronized Connection getConnection() {
        while (connections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return connections.remove(0);
    }
    
    public synchronized void releaseConnection(Connection conn) {
        connections.add(conn);
        notify();
    }
    
    public void closeAll() {
        for (Connection conn : connections) {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ConnectionPoolDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql:
        String user = "root";
        String password = "password";
        
        ConnectionPool pool = new ConnectionPool(url, user, password, 5);
        
        Connection conn = pool.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(conn);
        }
        
        pool.closeAll();
    }
}
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class CompleteCRUDApp extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, priceField;
    private JButton addBtn, updateBtn, deleteBtn, refreshBtn;
    private JLabel statusLabel;
    private Connection connection;
    
    public CompleteCRUDApp() {
        setTitle("Product Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        connectToDatabase();
        createTableIfNotExists();
        
        String[] columns = {"ID", "Name", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
        
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        
        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);
        
        add(inputPanel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        addBtn = new JButton("Add");
        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");
        refreshBtn = new JButton("Refresh");
        
        addBtn.addActionListener(e -> addProduct());
        updateBtn.addActionListener(e -> updateProduct());
        deleteBtn.addActionListener(e -> deleteProduct());
        refreshBtn.addActionListener(e -> loadProducts());
        
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(refreshBtn);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Products List"));
        add(scrollPane, BorderLayout.SOUTH);
        
        statusLabel = new JLabel("Ready");
        add(statusLabel, BorderLayout.EAST);
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                idField.setText(tableModel.getValueAt(row, 0).toString());
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                priceField.setText(tableModel.getValueAt(row, 2).toString());
            }
        });
        
        loadProducts();
    }
    
    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql:
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            statusLabel.setText("Connected to database");
        } catch (SQLException e) {
            statusLabel.setText("Database connection failed");
            e.printStackTrace();
        }
    }
    
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                     "id INT PRIMARY KEY," +
                     "name VARCHAR(100) NOT NULL," +
                     "price DECIMAL(10,2))";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadProducts() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price")
                };
                tableModel.addRow(row);
            }
            statusLabel.setText("Loaded " + tableModel.getRowCount() + " products");
        } catch (SQLException e) {
            statusLabel.setText("Error loading products");
            e.printStackTrace();
        }
    }
    
    private void addProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            
            String sql = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setDouble(3, price);
                pstmt.executeUpdate();
                statusLabel.setText("Product added successfully");
                loadProducts();
                clearFields();
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid input format");
        } catch (SQLException e) {
            statusLabel.setText("Error adding product: " + e.getMessage());
        }
    }
    
    private void updateProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            
            String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setDouble(2, price);
                pstmt.setInt(3, id);
                int affected = pstmt.executeUpdate();
                if (affected > 0) {
                    statusLabel.setText("Product updated successfully");
                    loadProducts();
                    clearFields();
                } else {
                    statusLabel.setText("Product not found");
                }
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid input format");
        } catch (SQLException e) {
            statusLabel.setText("Error updating product");
        }
    }
    
    private void deleteProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String sql = "DELETE FROM products WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int affected = pstmt.executeUpdate();
                if (affected > 0) {
                    statusLabel.setText("Product deleted successfully");
                    loadProducts
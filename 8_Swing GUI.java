import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingDemo extends JFrame implements ActionListener {
    private JTextField nameField, emailField;
    private JTextArea displayArea;
    private JButton submitButton, clearButton;
    private JLabel statusLabel;
    
    public SwingDemo() {
        setTitle("Student Registration Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);
        
        add(inputPanel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.CENTER);
        
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registered Students"));
        add(scrollPane, BorderLayout.SOUTH);
        
        statusLabel = new JLabel("Ready", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.EAST);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            
            if (name.isEmpty() || email.isEmpty()) {
                statusLabel.setText("Please fill all fields!");
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
            } else {
                displayArea.append("Name: " + name + ", Email: " + email + "\n");
                nameField.setText("");
                emailField.setText("");
                statusLabel.setText("Student added successfully!");
            }
        } else if (e.getSource() == clearButton) {
            nameField.setText("");
            emailField.setText("");
            displayArea.setText("");
            statusLabel.setText("Form cleared");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SwingDemo().setVisible(true);
        });
    }
}
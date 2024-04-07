import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JFrame {
    private JButton submitButton;
    private JPanel loginPanel;
    private JLabel userLabel, passwordLabel;
    private JTextField userField, passwordField;

    LoginForm() {
        super("Entry Screen");
        setupForm();
        setupActionListeners();
        setVisible(true);
    }

    private void setupForm() {
        userLabel = new JLabel("Username");
        userField = new JTextField(15);
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(15);
        submitButton = new JButton("SUBMIT");

        loginPanel = new JPanel(new GridLayout(3, 1));
        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(submitButton);

        add(loginPanel, BorderLayout.CENTER);
        setTitle("LOGIN");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void setupActionListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String username = userField.getText();
                String password = passwordField.getText();
                if (isValidLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Welcome " + username);
                    dispose();
                    DriverListing entryListing = new DriverListing();
                    entryListing.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect. Please enter a valid username and password");
                }
            }
        });
    }

    private boolean isValidLogin(String username, String password) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
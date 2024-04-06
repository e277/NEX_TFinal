import javax.swing.*;
import java.awt.*;

public class SecurityGuardEntry extends JFrame {
    private JTextField securityGuardIdField, securityGuardNameField, securityGuardPasswordField;
    private StudentListing entryListing;

    public SecurityGuardEntry() {
        super("Create Security Guard");

        setupFrame();
        setupTextFields();
        setupSaveButton();
    }

    private void setupFrame() {
        setSize(300, 150);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        setVisible(true);
    }

    private void setupTextFields() {
        addLabelAndTextField("ID:", securityGuardIdField = new JTextField(20));
        addLabelAndTextField("Name:", securityGuardNameField = new JTextField(20));
        addLabelAndTextField("Password:", securityGuardPasswordField = new JTextField(20));
    }

    private void addLabelAndTextField(String labelText, JTextField textField) {
        add(new JLabel(labelText));
        add(textField);
    }

    private void setupSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveSecurityGuard());
        add(saveButton);
    }

    private void saveSecurityGuard() {
        try {
            String id = securityGuardIdField.getText();
            String name = securityGuardNameField.getText();
            String password = securityGuardPasswordField.getText();
            SecurityGuard securityGuard = new SecurityGuard(id, name, password);
            entryListing.addSecurityGuard(securityGuard);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please fill in the data entry");
        }
    }
}
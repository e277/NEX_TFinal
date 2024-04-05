import javax.swing.*;
import java.awt.*;

public class CreateSecurityGuard extends JFrame {
    private JTextField securityGuardNameField, securityGuardIdField, securityGuardPasswordField
    EntryListing entryListing = new EntryListing();

    public CreateSecurityGuard() {}

    public CreateSecurityGuard(EntryListing entryListing) {
        this.entryListing = entryListing;

        // text fields
        JLabel securityGuardNameLabel = new JLabel("Name:");
        securityGuardNameField = new JTextField(20);
        JLabel securityGuardIdLabel = new JLabel("ID:");
        securityGuardIdField = new JTextField(20);
        JLabel securityGuardPasswordLabel = new JLabel("Password:");
        securityGuardPasswordField = new JTextField(20);

        // panel dem for text fields
        JPanel textFieldPanel = new JPanel(new GridLayout(3, 2));
        textFieldPanel.add(securityGuardNameLabel);
        textFieldPanel.add(securityGuardNameField);
        textFieldPanel.add(securityGuardIdLabel);
        textFieldPanel.add(securityGuardIdField);
        textFieldPanel.add(securityGuardPasswordLabel);
        textFieldPanel.add(securityGuardPasswordField);

        // create save button and addlistener
        JButton SaveButton = new JButton("Save");

        SaveButton.addActionListener(e -> {
            try {
                String name = securityGuardNameField.getText();
                int id = Integer.parseInt(securityGuardIdField.getText());
                String password = securityGuardPasswordField.getText();
                SecurityGuard securityGuard = new SecurityGuard(name, id, password);
                entryListing.addSecurityGuard(securityGuard);

                entryListing.dispose();
            } catch (Exception ex) {
                // Handle the exception
                JOptionPane.showMessageDialog(null, "Please fill in the data entry");
            }
        });
    }
}

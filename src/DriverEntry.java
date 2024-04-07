import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverEntry extends JFrame {
    private JTextField nameField, licensePlateField, idField, timeInField, timeOutField;
    private DriverListing entryListing;

    public DriverEntry(DriverListing entryListing) {
        super("Enter Driver Information");
        this.entryListing = entryListing;

        setupFrame();
        setupTextFields();
        setupSaveButton();
    }

    private void setupFrame() {
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void setupTextFields() {
        JPanel textFieldPanel = new JPanel(new GridLayout(5, 2));
        textFieldPanel.add(createLabelAndTextField("ID:", idField = new JTextField(20)));
        textFieldPanel.add(createLabelAndTextField("Name:", nameField = new JTextField(20)));
        textFieldPanel.add(createLabelAndTextField("License Plate:", licensePlateField = new JTextField(20)));
        textFieldPanel.add(createLabelAndTextField("Time In:", timeInField = new JTextField(20)));
        textFieldPanel.add(createLabelAndTextField("Time Out:", timeOutField = new JTextField(20)));
        getContentPane().add(textFieldPanel, BorderLayout.CENTER);
    }

    private JPanel createLabelAndTextField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel(labelText));
        panel.add(textField);
        return panel;
    }

    private void setupSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveEntry());
        getContentPane().add(saveButton, BorderLayout.SOUTH);
    }

    private void saveEntry() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String licensePlate = licensePlateField.getText();
            String timeIn = timeInField.getText();
            String timeOut = timeOutField.getText();
            Driver entry = new Driver(id, name, licensePlate, timeIn, timeOut);

            String insertSql = "INSERT INTO Drivers (id, name, licensePlate, timeIn, timeOut) VALUES (?, ?, ?, ?, ?)";

            try (Connection connection = DatabaseConfig .getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

                preparedStatement.setString(1, entry.getId());
                preparedStatement.setString(2, entry.getName());
                preparedStatement.setString(3, entry.getLicensePlate());
                preparedStatement.setString(4, entry.getTimeIn());
                preparedStatement.setString(5, entry.getTimeOut());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            entryListing.loadEntries();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please fill in the data entry");
        }
    }
}
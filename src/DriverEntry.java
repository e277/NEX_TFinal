import javax.swing.*;
import java.awt.*;
import java.sql.*;

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

            if (id.isEmpty() || name.isEmpty() || licensePlate.isEmpty() || timeIn.isEmpty() || timeOut.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            Driver entry = new Driver(id, name, licensePlate, timeIn, timeOut);

            String findSlotQuery = "SELECT COUNT(*) as totalSlots, SUM(isOccupied) as occupiedSlots FROM parking_lot";
            try (Connection connection = DatabaseConfig.getConnection();
                 PreparedStatement findSlotStatement = connection.prepareStatement(findSlotQuery);
                 ResultSet resultSet = findSlotStatement.executeQuery()) {

                if (resultSet.next()) {
                    int totalSlots = resultSet.getInt("totalSlots");
                    int occupiedSlots = resultSet.getInt("occupiedSlots");

                    if (occupiedSlots < totalSlots) {
                        String insertSql = "INSERT INTO Drivers (id, name, licensePlate, timeIn, timeOut) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                            preparedStatement.setString(1, entry.getId());
                            preparedStatement.setString(2, entry.getName());
                            preparedStatement.setString(3, entry.getLicensePlate());
                            preparedStatement.setString(4, entry.getTimeIn());
                            preparedStatement.setString(5, entry.getTimeOut());

                            preparedStatement.executeUpdate();
                        }

                        int nextSlot = -1;
                        String findNextSlotQuery = "SELECT MIN(slotNumber) as nextSlot FROM parking_lot WHERE isOccupied = false";
                        try (PreparedStatement findNextSlotStatement = connection.prepareStatement(findNextSlotQuery);
                             ResultSet resultSet2 = findNextSlotStatement.executeQuery()) {
                            if (resultSet2.next()) {
                                nextSlot = resultSet2.getInt("nextSlot");
                            }
                        }

                        if (nextSlot != -1) {
                            String assignDriverQuery = "UPDATE parking_lot SET isOccupied = true, driverId = ? WHERE slotNumber = ?";
                            try (PreparedStatement assignDriverStatement = connection.prepareStatement(assignDriverQuery)) {
                                assignDriverStatement.setString(1, entry.getId());
                                assignDriverStatement.setInt(2, nextSlot);
                                assignDriverStatement.executeUpdate();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "The parking lot is full. The driver entry was not saved.");
                        return;
                    }
                }
            }

            entryListing.loadEntries();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
    }
}
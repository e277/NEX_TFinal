import javax.swing.*;
import java.awt.*;

public class DriverEntry extends JFrame {
    private JTextField nameField, licensePlateField, idField, timeInField, timeOutField;
    private DriverListing entryListing;

    public DriverEntry(DriverListing entryListing) {
        super("Student Data Entry");
        this.entryListing = entryListing;

        setupTextFields();
        setupSaveButton();
        setupFrame();
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
            String name = nameField.getText();
            String licensePlate = licensePlateField.getText();
            String id = idField.getText();
            String timeIn = timeInField.getText();
            String timeOut = timeOutField.getText();
            Driver entry = new Driver(name, licensePlate, id, timeIn, timeOut);
            entryListing.addEntry(entry);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please fill in the data entry");
        }
    }

    private void setupFrame() {
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
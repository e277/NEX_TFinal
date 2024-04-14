import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverEditor extends JFrame {
    private JTextField idField, nameField, licensePlateField, timeInField, timeOutField;
    private JComboBox<String> entryComboBox;
    private DriverListing entryListing;

    public DriverEditor(DriverListing entryListing) {
        super("Edit Driver Information");
        this.entryListing = entryListing;

        setupFrame();
        setupComponents();
        setupListeners();
    }

    private void setupFrame() {
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        setVisible(true);
    }

    private void setupComponents() {
        entryComboBox = new JComboBox<>();
        loadEntriesToComboBox();

        nameField = new JTextField(20);
        licensePlateField = new JTextField(20);
        idField = new JTextField(20);
        timeInField = new JTextField(20);
        timeOutField = new JTextField(20);

        addComponentsToFrame();
    }

    private void loadEntriesToComboBox() {
        String selectSql = "SELECT * FROM Drivers";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                entryComboBox.addItem(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addComponentsToFrame() {
        add(new JLabel("Select Entry:"));
        add(entryComboBox);
        add(new JLabel("Name: "));
        add(nameField);
        add(new JLabel("License Plate: "));
        add(licensePlateField);
        add(new JLabel("ID: "));
        add(idField);
        add(new JLabel("Time In: "));
        add(timeInField);
        add(new JLabel("Time Out: "));
        add(timeOutField);
        add(new JLabel(""));
        add(new JButton("Update"));
    }

    private void setupListeners() {
        entryComboBox.addActionListener(e -> updateFieldsBasedOnSelectedEntry());
        JButton saveButton = (JButton) getContentPane().getComponent(13);
        saveButton.addActionListener(e -> updateEntry());
    }

    private void updateFieldsBasedOnSelectedEntry() {
        String selectedName = (String) entryComboBox.getSelectedItem();
        String selectSql = "SELECT * FROM Drivers WHERE name = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            preparedStatement.setString(1, selectedName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String licensePlate = resultSet.getString("licensePlate");
                String timeIn = resultSet.getString("timeIn");
                String timeOut = resultSet.getString("timeOut");

                idField.setText(id);
                nameField.setText(name);
                licensePlateField.setText(licensePlate);
                timeInField.setText(timeIn);
                timeOutField.setText(timeOut);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateEntry() {
        String id = idField.getText();
        String name = nameField.getText();
        String licensePlate = licensePlateField.getText();
        String timeIn = timeInField.getText();
        String timeOut = timeOutField.getText();

        String updateSql = "UPDATE Drivers SET name = ?, licensePlate = ?, timeIn = ?, timeOut = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, licensePlate);
            preparedStatement.setString(3, timeIn);
            preparedStatement.setString(4, timeOut);
            preparedStatement.setString(5, id);
            preparedStatement.executeUpdate();

            entryListing.loadEntries();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dispose();
    }
}
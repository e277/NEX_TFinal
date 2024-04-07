import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverSearch extends JFrame {
    private JLabel nameField, licensePlateField, idField, timeInField, timeOutField;
    private JTextField idSearch;
    private DriverListing entryListing;

    public DriverSearch(DriverListing entryListing) {
        super("Search For Driver");
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
        nameField = new JLabel();
        licensePlateField = new JLabel();
        idField = new JLabel();
        timeInField = new JLabel();
        timeOutField = new JLabel();
        idSearch = new JTextField();

        addLabelAndTextField(idSearch);
        addLabelAndField("Name: ", nameField);
        addLabelAndField("License Plate: ", licensePlateField);
        addLabelAndField("ID: ", idField);
        addLabelAndField("Time In: ", timeInField);
        addLabelAndField("Time Out: ", timeOutField);

        addButtons();
    }

    private void addLabelAndTextField(JTextField textField) {
        add(new JLabel("Search for ID:"));
        add(textField);
    }

    private void addLabelAndField(String labelText, JLabel label) {
        add(new JLabel(labelText));
        add(label);
    }

    private void addButtons() {
        JButton searchButton = new JButton("Search");
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        add(searchButton);
        add(closeButton);
    }

    private void setupListeners() {
        JButton searchButton = (JButton) getContentPane().getComponent(12); // Adjusted index
        searchButton.addActionListener(e -> searchStudent());
    }

    private void searchStudent() {
        String id = idSearch.getText();
        String selectSql = "SELECT * FROM Drivers WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idField.setText(resultSet.getString("id"));
                nameField.setText(resultSet.getString("name"));
                licensePlateField.setText(resultSet.getString("licensePlate"));
                timeInField.setText(resultSet.getString("timeIn"));
                timeOutField.setText(resultSet.getString("timeOut"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverDeleter extends JFrame {
    private JComboBox<String> entryComboBox;
    private DriverListing entryListing;

    public DriverDeleter(DriverListing entryListing) {
        super("Entry Deleter");
        this.entryListing = entryListing;

        setupFrame();
        setupComponents();
    }

    private void setupFrame() {
        setSize(300, 150);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2));
    }

    private void setupComponents() {
        JLabel selectLabel = new JLabel("Select an entry to delete: ");
        entryComboBox = new JComboBox<>();
        loadEntriesToComboBox();
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteEntry());

        add(selectLabel);
        add(entryComboBox);
        add(new JLabel(""));
        add(deleteButton);
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

    private void deleteEntry() {
        String selectedName = (String) entryComboBox.getSelectedItem();
        deleteDriverFromDatabase(selectedName);
        dispose();
    }

    private void deleteDriverFromDatabase(String name) {
        String deleteSql = "DELETE FROM Drivers WHERE name = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

            entryListing.loadEntries();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverListing extends JFrame {
    private JTable jTable;
    private DefaultTableModel model;

    public DriverListing() {
        super("Driver Listing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupFrame();
        setupTable();
        loadEntries();
        setupButtons();
    }

    private void setupFrame() {
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void setupTable() {
        String[] columnNames = {"ID", "Name", "License Plate", "Time In", "Time Out"};
        model = new DefaultTableModel(columnNames, 0);
        jTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(jTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void setupButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel.add(createButton("Enter Driver", e -> new DriverEntry(this)));
        buttonPanel.add(createButton("Edit Driver", e -> new DriverEditor(this)));
        buttonPanel.add(createButton("Delete Driver", e -> new DriverDeleter(this)));
        buttonPanel.add(createButton("Search Driver", e -> new DriverSearch(this)));
        buttonPanel.add(createButton("Close", e -> dispose()));
        getContentPane().add(buttonPanel, BorderLayout.EAST);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    public void loadEntries() {
        model.setRowCount(0); // Clear existing entries
        String selectSql = "SELECT * FROM Drivers";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String licensePlate = resultSet.getString("licensePlate");
                String timeIn = resultSet.getString("timeIn");
                String timeOut = resultSet.getString("timeOut");
                model.addRow(new Object[]{id, name, licensePlate, timeIn, timeOut});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingLot extends JFrame {
    private int totalSpaces;
    private int availableSpaces;
    private JTable parkingLotStatusTable;

    public ParkingLot() {
        super("Parking Lot Status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 250);
        setLocationRelativeTo(null);

        this.totalSpaces = 0;
        this.availableSpaces = 0;

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        printParkingLotStatus();
    }

    public void calculateTotalSpaces() {
        String query = "SELECT COUNT(*) FROM parking_lot";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                totalSpaces = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void calculateAvailableSpaces() {
        String query = "SELECT COUNT(*) FROM parking_lot WHERE isOccupied = 0";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                availableSpaces = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void signOutCar(String driverId) {
        String query = "DELETE FROM parking_lot WHERE driverId = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, driverId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String updateQuery = "UPDATE parking_lot SET isOccupied = 0 WHERE driverId = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, driverId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printParkingLotStatus() {
        calculateTotalSpaces();
        calculateAvailableSpaces();
        String[][] data = {
                {"Total parking spaces", String.valueOf(totalSpaces)},
                {"Available parking spaces", String.valueOf(availableSpaces)}
        };
        String[] columnNames = {"Status", "Count"};
        parkingLotStatusTable = new JTable(data, columnNames);
        getContentPane().add(new JScrollPane(parkingLotStatusTable), BorderLayout.CENTER);
        if (availableSpaces == 0) {
            JOptionPane.showMessageDialog(null, "All parking spaces are occupied.");
        }
    }
}
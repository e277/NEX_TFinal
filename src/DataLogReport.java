import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.io.File;

public class DataLogReport extends JFrame {
    private int totalPatrons;
    private int newPatrons;
    private int modifiedRecords;
    private JTable reportTable;

    public DataLogReport() {
        super("Data Log Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 250);
        setLocationRelativeTo(null);

        this.totalPatrons = 0;
        this.newPatrons = 0;
        this.modifiedRecords = 0;

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton saveButton = new JButton("Save Report");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReportToFile();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        buttonPanel.add(saveButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        generateReport();
    }

    public void calculateTotalPatrons() {
        String sql = "SELECT COUNT(DISTINCT p.driverId) FROM parking_lot p JOIN drivers d ON p.driverId = d.id WHERE d.timeIn = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, LocalDate.now().toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalPatrons = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void calculateNewPatrons() {
        String sql = "SELECT COUNT(*) FROM drivers WHERE timeIn = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, LocalDate.now().toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    newPatrons = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void calculateModifiedRecords() {
        String sql = "SELECT COUNT(*) FROM parking_lot p JOIN drivers d ON p.driverId = d.id WHERE d.timeOut = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, LocalDate.now().toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    modifiedRecords = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateReport() {
        calculateTotalPatrons();
        calculateNewPatrons();
        calculateModifiedRecords();
        String[][] data = {
                {"Total Patrons", String.valueOf(totalPatrons)},
                {"New Patrons", String.valueOf(newPatrons)},
                {"Modified Records", String.valueOf(modifiedRecords)}
        };
        String[] columnNames = {"Status", "Count"};
        reportTable = new JTable(data, columnNames);
        getContentPane().add(new JScrollPane(reportTable), BorderLayout.CENTER);
    }

    public void saveReportToFile() {
        File reportFile = new File("src/report.txt");
        try (FileWriter writer = new FileWriter(reportFile)) {
            for (int i = 0; i < reportTable.getRowCount(); i++) {
                for (int j = 0; j < reportTable.getColumnCount(); j++) {
                    writer.write(reportTable.getValueAt(i, j).toString() + " ");
                }
                writer.write("\n");
            }
            JOptionPane.showMessageDialog(null, "Report saved successfully to src/report.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: Unable to write to report file.");
            e.printStackTrace();
        }
    }
}
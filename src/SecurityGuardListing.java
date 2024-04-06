import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SecurityGuardListing extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<SecurityGuard> entries = new ArrayList<>();

    public SecurityGuardListing() {
        super("Security Guard Listing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupTable();
        setupButtons();
        setupFrame();

        entries = loadEntries("securityGuards.txt");
    }

    private void setupTable() {
        String[] columnNames = {"ID", "Name", "Password"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void setupButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.add(createButton("Add", e -> new SecurityGuardEntry()));
        buttonPanel.add(createButton("Close", e -> saveAndClose("securityGuards.txt")));
        getContentPane().add(buttonPanel, BorderLayout.EAST);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    private void setupFrame() {
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private ArrayList<SecurityGuard> loadEntries(String filePath) {
        ArrayList<SecurityGuard> entries = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String[] nextLine = scanner.nextLine().split(";");
                entries.add(new SecurityGuard(nextLine[0], nextLine[1], nextLine[2]));
            }
        } catch (IOException ignored) {
        }
        return entries;
    }

    public void addEntry(SecurityGuard entry) {
        entries.add(entry);
        model.addRow(new Object[]{entry.getId(), entry.getName(), entry.getPassword()});
    }

    public void deleteEntry(int index) {
        entries.remove(index);
        model.removeRow(index);
    }

    private void saveAndClose(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (SecurityGuard entry : entries) {
                writer.println(entry.getId() + ";" + entry.getName() + ";" + entry.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }
}
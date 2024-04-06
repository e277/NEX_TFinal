import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentListing extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Student> entries = new ArrayList<>();

    public StudentListing() {
        super("Entry Listing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupTable();
        setupButtons();
        setupFrame();

        entries = loadEntries("NEX_TFinal\\src\\nextlogopic.jpg");
    }

    private void setupTable() {
        String[] columnNames = {"ID", "Name", "License Plate", "Time In", "Time Out"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void setupButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel.add(createButton("Enter Data", e -> new StudentEntry(this)));
        buttonPanel.add(createButton("Edit", e -> new StudentEditor(this, this.entries)));
        buttonPanel.add(createButton("Delete", e -> new StudentDeleter(this, this.entries)));
        buttonPanel.add(createButton("Search", e -> new StudentSearch(this, this.entries)));
        buttonPanel.add(createButton("Close", e -> saveAndClose("\\nextlogopic.jpg")));
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

    private ArrayList<Student> loadEntries(String filePath) {
        ArrayList<Student> entries = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String[] nextLine = scanner.nextLine().split(";");
                entries.add(new Student(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4]));
            }
        } catch (IOException ignored) {
        }
        return entries;
    }

    public void addEntry(Student entry) {
        entries.add(entry);
        model.addRow(new Object[]{entry.getName(), entry.getLicensePlate(), entry.getId(), entry.getTimeIn(), entry.getTimeOut()});
    }

    public void updateEntry(int index, Student entry) {
        model.setValueAt(entry.getId(), index, 0);
        entries.set(index, entry);
        model.setValueAt(entry.getName(), index, 1);
        model.setValueAt(entry.getLicensePlate(), index, 2);
        model.setValueAt(entry.getTimeIn(), index, 3);
        model.setValueAt(entry.getTimeOut(), index, 4);
    }

    public void deleteEntry(int index) {
        entries.remove(index);
        model.removeRow(index);
    }

    private void saveAndClose(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Student entry : entries) {
                writer.println(entry.getName() + ";" + entry.getLicensePlate() + ";" + entry.getId() + ";" + entry.getTimeIn() + ";" + entry.getTimeOut());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }

    public void addSecurityGuard(SecurityGuard securityGuard) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("securityGuards.txt", true))) {
            writer.println(securityGuard.getId() + ";" + securityGuard.getName() + ";" + securityGuard.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
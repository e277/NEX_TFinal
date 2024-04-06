import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentEditor extends JFrame {
    private JTextField idField, nameField, licensePlateField, timeInField, timeOutField;
    private JComboBox<String> entryComboBox;
    private StudentListing entryListing;

    public StudentEditor(StudentListing entryListing, ArrayList<Student> entries) {
        super("Entry Editor");
        this.entryListing = entryListing;

        setupFrame();
        setupComponents(entries);
        setupListeners(entries);
    }

    private void setupFrame() {
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        setVisible(true);
    }

    private void setupComponents(ArrayList<Student> entries) {
        entryComboBox = new JComboBox<>();
        for (Student entry : entries) {
            entryComboBox.addItem(entry.getName());
        }

        nameField = new JTextField(20);
        licensePlateField = new JTextField(20);
        idField = new JTextField(20);
        timeInField = new JTextField(20);
        timeOutField = new JTextField(20);

        addComponentsToFrame();
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
        add(new JButton("Save"));
    }

    private void setupListeners(ArrayList<Student> entries) {
        entryComboBox.addActionListener(e -> updateFieldsBasedOnSelectedEntry(entries));
        JButton saveButton = (JButton) getContentPane().getComponent(13);
        saveButton.addActionListener(e -> saveEntry());
    }

    private void updateFieldsBasedOnSelectedEntry(ArrayList<Student> entries) {
        int selectedIndex = entryComboBox.getSelectedIndex();
        Student newSelectedEntry = entries.get(selectedIndex);
        idField.setText(newSelectedEntry.getId());
        nameField.setText(newSelectedEntry.getName());
        licensePlateField.setText(newSelectedEntry.getLicensePlate());
        timeInField.setText(newSelectedEntry.getTimeIn());
        timeOutField.setText(newSelectedEntry.getTimeOut());
    }

    private void saveEntry() {
        int selectedIndex = entryComboBox.getSelectedIndex();
        String id = idField.getText();
        String name = nameField.getText();
        String licensePlate = licensePlateField.getText();
        String timeIn = timeInField.getText();
        String timeOut = timeOutField.getText();

        Student updatedEntry = new Student(id, name, licensePlate, timeIn, timeOut);
        entryListing.updateEntry(selectedIndex, updatedEntry);

        dispose();
    }
}
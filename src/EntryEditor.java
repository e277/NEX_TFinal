import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EntryEditor extends JFrame {
    private JTextField nameField, licensePlateField, idField, timeInField, timeOutField;
    private ArrayList<Entry> entries;
    private JComboBox<String> entryComboBox;
    private EntryListing entryListing;


    public EntryEditor(EntryListing entryListing, ArrayList<Entry> entries) {
        super("Entry Editor");
        this.entryListing = entryListing;
        this.entries = entries;

        // Set up the GUI
        setTitle("Entry Editor");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize GUI components

        JLabel selectLabel = new JLabel("Select Entry:");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel licensePlateLabel = new JLabel("License Plate: ");
        JLabel idLabel = new JLabel("ID: ");
        JLabel timeInLabel = new JLabel("Time In: ");
        JLabel timeOutLabel = new JLabel("Time Out: ");


        nameField = new JTextField(20);
        licensePlateField = new JTextField(20);
        idField = new JTextField(20);
        timeInField = new JTextField(20);
        timeOutField = new JTextField(20);

        JButton saveButton = new JButton("Save");
        entryComboBox = new JComboBox<>();
        for (Entry entry : entries) {
            entryComboBox.addItem(entry.getName());
        }

        // Add components to JFrame
        setLayout(new GridLayout(7, 2));
        add(selectLabel);
        add(entryComboBox);
        add(nameField);
        add(nameLabel);
        add(licensePlateField);
        add(licensePlateLabel);
        add(idField);
        add(idLabel);
        add(timeInField);
        add(timeInLabel);
        add(timeOutField);
        add(timeOutLabel);
        add(new JLabel(""));
        add(saveButton);

        //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //add(inputPanel, BorderLayout.CENTER);
        //add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        // Set initial text fields
        Entry selectedEntry = entries.get(0);
        nameField.setText(selectedEntry.getName());
        licensePlateField.setText(selectedEntry.getLicensePlate());
        idField.setText(Integer.toString(selectedEntry.getId()));
        timeInField.setText(selectedEntry.getTimeIn());
        timeOutField.setText(selectedEntry.getTimeOut());

        // Add listener to combo box
        entryComboBox.addActionListener(e -> {
            int selectedIndex = entryComboBox.getSelectedIndex();
            Entry newSelectedEntry = entries.get(selectedIndex);
            nameField.setText(newSelectedEntry.getName());
            licensePlateField.setText(newSelectedEntry.getLicensePlate());
            idField.setText(Integer.toString(newSelectedEntry.getId()));
            timeInField.setText(newSelectedEntry.getTimeIn());
            timeOutField.setText(newSelectedEntry.getTimeOut());
        });

        // Add listener to save button
        saveButton.addActionListener(e -> {
            int selectedIndex = entryComboBox.getSelectedIndex();
            String name = nameField.getText();
            String licensePlate = licensePlateField.getText();
            int id = Integer.parseInt(idField.getText());
            String timeIn = timeInField.getText();
            String timeOut = timeOutField.getText();

            Entry updatedEntry = new Entry(name, licensePlate, id, timeIn, timeOut);
            entryListing.updateEntry(selectedIndex, updatedEntry);

            dispose();
        });
    }
}


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DriverDeleter extends JFrame {
    private JComboBox<String> entryComboBox;
    private DriverListing entryListing;

    public DriverDeleter(DriverListing entryListing, ArrayList<Driver> entries) {
        super("Entry Deleter");
        this.entryListing = entryListing;

        setupFrame();
        setupComponents(entries);
    }

    private void setupFrame() {
        setSize(300, 150);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2));
    }

    private void setupComponents(ArrayList<Driver> entries) {
        JLabel selectLabel = new JLabel("Select an entry to delete: ");
        entryComboBox = new JComboBox<>();
        for (Driver entry : entries) {
            entryComboBox.addItem(entry.getName());
        }
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteEntry());

        add(selectLabel);
        add(entryComboBox);
        add(new JLabel(""));
        add(deleteButton);
    }

    private void deleteEntry() {
        int selectedIndex = entryComboBox.getSelectedIndex();
        entryListing.deleteEntry(selectedIndex);
        dispose();
    }
}
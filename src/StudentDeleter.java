import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentDeleter extends JFrame {
    private JComboBox<String> entryComboBox;
    private StudentListing entryListing;

    public StudentDeleter(StudentListing entryListing, ArrayList<Student> entries) {
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

    private void setupComponents(ArrayList<Student> entries) {
        JLabel selectLabel = new JLabel("Select an entry to delete: ");
        entryComboBox = new JComboBox<>();
        for (Student entry : entries) {
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
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EntryDeleter extends JFrame {
    // Declare variables
    private JComboBox<String> entryComboBox;
    private ArrayList<Entry> entries;
    private EntryListing entryListing;

    public EntryDeleter(EntryListing entryListing, ArrayList<Entry> entries) {
        super("Entry Deleter");
        // Set the entryListing variable to the provided entryListing parameter
        this.entryListing = entryListing;
        this.entries = entries;


        setSize(300, 150);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a combo box with the entry names

        JLabel selectLabel = new JLabel("Select an entry to delete: ");
        entryComboBox = new JComboBox<>();
        for (Entry entry : entries) {
            entryComboBox.addItem(entry.getName());
        }
        JButton deleteButton = new JButton("Delete");

        setLayout(new GridLayout(2, 2));
        add(selectLabel);
        add(entryComboBox);
        add(new JLabel(""));
        //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(deleteButton);


        //add(inputPanel, BorderLayout.CENTER);
        //add(buttonPanel, BorderLayout.SOUTH);
        // Add listener to delete button
        deleteButton.addActionListener(e -> {
            int selectedIndex = entryComboBox.getSelectedIndex();

            entryListing.deleteEntry(selectedIndex);
            dispose();
        });
    }

}

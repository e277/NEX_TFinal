import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Search extends JFrame {
    private JLabel nameField, licensePlateField, idField, timeInField, timeOutField;
    private JTextField idSearch;
    private ArrayList<Entry> entries;
    private JComboBox<String> entryComboBox;
    private EntryListing entryListing;


    public Search(EntryListing entryListing, ArrayList<Entry> entries) {
        super("Search");
        this.entryListing = entryListing;
        this.entries = entries;

        // Set up the GUI
        setTitle("Search");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize GUI components

        JLabel selectLabel = new JLabel("search for ID:");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel licensePlateLabel = new JLabel("License Plate: ");
        JLabel idLabel = new JLabel("ID: ");
        JLabel timeInLabel = new JLabel("Time In: ");
        JLabel timeOutLabel = new JLabel("Time Out: ");


        nameField = new JLabel();
        licensePlateField = new JLabel();
        idField = new JLabel();
        timeInField = new JLabel();
        timeOutField = new JLabel();
        idSearch = new JTextField();

        JButton okButton = new JButton("OK");
        JButton closeButton = new JButton("Close");
        entryComboBox = new JComboBox<>();
        for (Entry entry : entries) {
            entryComboBox.addItem(entry.getName());
        }

        // Add components to JFrame
        setLayout(new GridLayout(7, 2));
        add(selectLabel);
        add(idSearch);
        add(nameLabel);
        add(nameField);
        add(licensePlateLabel);
        add(licensePlateField);
        add(idLabel);
        add(idField);
        add(timeInLabel);
        add(timeInField);
        add(timeOutLabel);
        add(timeOutField);
        add(okButton);
        add(closeButton);

        //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //add(inputPanel, BorderLayout.CENTER);
        //add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        // Add listener to save button
        closeButton.addActionListener(e -> {
            dispose();
        });

        okButton.addActionListener(e -> {
            int person = Integer.parseInt(idSearch.getText());
            for(Entry entry:entries){
                if (entry.getId()==person){
                    nameField.setText(entry.getName());
                    licensePlateField.setText(entry.getLicensePlate());
                    idField.setText(Integer.toString(entry.getId()));
                    timeInField.setText(entry.getTimeIn());
                    timeOutField.setText(entry.getTimeOut());
                }
            }
        });
    }
}

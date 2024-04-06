import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class DriverSearch extends JFrame {
    private JLabel nameField, licensePlateField, idField, timeInField, timeOutField;
    private JTextField idSearch;
    private ArrayList<Driver> entries;
    private DriverListing entryListing;

    public DriverSearch(DriverListing entryListing, ArrayList<Driver> entries) {
        super("Search");
        this.entryListing = entryListing;
        this.entries = entries;

        setupFrame();
        setupComponents();
        setupListeners();
    }

    private void setupFrame() {
        setTitle("Search");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        setVisible(true);
    }

    private void setupComponents() {
        nameField = new JLabel();
        licensePlateField = new JLabel();
        idField = new JLabel();
        timeInField = new JLabel();
        timeOutField = new JLabel();
        idSearch = new JTextField();

        addLabelAndTextField("search for ID:", idSearch);
        addLabelAndField("Name: ", nameField);
        addLabelAndField("License Plate: ", licensePlateField);
        addLabelAndField("ID: ", idField);
        addLabelAndField("Time In: ", timeInField);
        addLabelAndField("Time Out: ", timeOutField);

        addButtons();
    }

    private void addLabelAndTextField(String labelText, JTextField textField) {
        add(new JLabel(labelText));
        add(textField);
    }

    private void addLabelAndField(String labelText, JLabel label) {
        add(new JLabel(labelText));
        add(label);
    }

    private void addButtons() {
        JButton okButton = new JButton("OK");
        JButton closeButton = new JButton("Close");

        add(okButton);
        add(closeButton);
    }

    private void setupListeners() {
        JButton closeButton = (JButton) getContentPane().getComponent(12);
        closeButton.addActionListener(e -> dispose());

        JButton okButton = (JButton) getContentPane().getComponent(11);
        okButton.addActionListener(e -> searchStudent());
    }

    private void searchStudent() {
        String person = idSearch.getText();
        for(Driver entry:entries){
            if (Objects.equals(entry.getId(), person)){
                idField.setText(entry.getId());
                nameField.setText(entry.getName());
                licensePlateField.setText(entry.getLicensePlate());
                timeInField.setText(entry.getTimeIn());
                timeOutField.setText(entry.getTimeOut());
            }
        }
    }
}
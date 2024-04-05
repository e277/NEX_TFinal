import javax.swing.*;
import java.awt.*;

public class DataEntry extends JFrame {
    private JTextField nameField,licensePlateField,idField,timeInField,timeOutField;
    EntryListing entryListing = new EntryListing();
    

    public DataEntry(EntryListing entryListing) {
        super("Data Entry");
        DataEntry de=(this);
        this.entryListing = entryListing;

        // text fields
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel licensePlateLabel = new JLabel("License Plate:");
        licensePlateField = new JTextField(20);
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(20);
        JLabel timeInLabel = new JLabel("Time In:");
        timeInField = new JTextField(20);
        JLabel timeOutLabel = new JLabel("Time Out:");
        timeOutField = new JTextField(20);
        
        
        // panel dem for text fields
        JPanel textFieldPanel = new JPanel(new GridLayout(5, 2));
        textFieldPanel.add(nameLabel);
        textFieldPanel.add(nameField);
        textFieldPanel.add(licensePlateLabel);
        textFieldPanel.add(licensePlateField);
        textFieldPanel.add(idLabel);
        textFieldPanel.add(idField);
        textFieldPanel.add(timeInLabel);
        textFieldPanel.add(timeInField);
        textFieldPanel.add(timeOutLabel);
        textFieldPanel.add(timeOutField);

        // create save button and addlistener
        JButton SaveButton = new JButton("Save");

        SaveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String licensePlate = licensePlateField.getText();
                int id = Integer.parseInt(idField.getText());
                String timeIn = timeInField.getText();
                String timeOut = timeOutField.getText();
                Entry entry = new Entry(name, licensePlate, id, timeIn, timeOut);
                entryListing.addEntry(entry);

                dispose();
            } catch (Exception ex) {
                // Handle the exception
                JOptionPane.showMessageDialog(null, "Please fill in the data entry");
            }
        });



        // add components dem
        Container contentPane = getContentPane();
        contentPane.add(textFieldPanel, BorderLayout.CENTER);
        contentPane.add(SaveButton, BorderLayout.SOUTH);

        //set JFrame properties
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}


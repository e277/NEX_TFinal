import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
public class EntryListing extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton cmdAdd;
    private JButton cmdEdit;
    private JButton cmdDelete;
    private JButton cmdSearch;
    private  JButton cmdClose;
    private EntryListing thisForm;
    private ArrayList<Entry> entries = new ArrayList<>();
    //private int space;


    public EntryListing(/*ArrayList<Entry> entries*/) {
        super("Entry Listing");
        thisForm= this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // create table
        String [] columnNames={"Name", "License Plate", "ID", "Time In", "Time Out"};
        model = new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        showTable(entries);
        JScrollPane scrollPane = new JScrollPane(table);

        // create buttons
        cmdAdd = new JButton("Enter Data");
        cmdEdit = new JButton("Edit");
//        cmdSort = new JButton("Sort Data");
        cmdDelete = new JButton("Delete");
        cmdSearch = new JButton("Search");
        //        totalsBtn = new JButton("Totals");
        cmdClose = new JButton("Close");

        // create panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel.add(cmdAdd);
        buttonPanel.add(cmdEdit);
        buttonPanel.add(cmdDelete);
        buttonPanel.add(cmdSearch);
        buttonPanel.add(cmdClose);

        // add listeners to buttons
        cmdAdd.addActionListener(e -> new DataEntry(this));
        cmdEdit.addActionListener(e -> new EntryEditor(this, this.entries));
        cmdDelete.addActionListener(e -> new EntryDeleter(this, this.entries));
        cmdSearch.addActionListener(e -> new Search(this, this.entries));
        cmdClose.addActionListener(e -> SaveandClose(this.entries, "C:\\Users\\ezra\\Documents\\NEX_TFinal\\src\\nextlogopic.jpg"));

        // add components to frame
        Container contentPane = getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.EAST);

        // set size and location
        setSize(800, 600);
        setLocationRelativeTo(null);

        // initialize list of entries
        //this.entries = new ArrayList<>();
        entries = loadEntries("C:\\Users\\ezra\\Documents\\NEX_TFinal\\src\\nextlogopic.jpg");

    }


    private void showTable(ArrayList<Entry> entries)
    {
        for(Entry entry : entries)
        {
            addEntry(entry);
        }

    }


    private ArrayList<Entry> loadEntries(String cfile) {
        System.out.println("Opening file: " + cfile);
        Scanner pscan = null;
        ArrayList<Entry> entries = new ArrayList<Entry>();
        try {
            pscan = new Scanner(new File(cfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(";");
                String name = nextLine[0];
                String licensePlate = nextLine[1];
                int id = Integer.parseInt(nextLine[2]);
                String timeIn = nextLine[3];
                String timeOut = nextLine[4];
                Entry c = new Entry(name, licensePlate, id, timeIn, timeOut);
                entries.add(c); // add entry to list
            }
            System.out.println("Loaded " + entries.size() + " entries");
            pscan.close();
        } catch (IOException e) {
        }
        showTable(entries); // show table
        return entries; // return list of entries
    }
    public int space=10;
    public void addEntry(Entry entry) {
//        int space =10;
//        File cfile= new File("C:\\Users\\Jon-Paul\\IdeaProjects\\NEX_T\\src\\entryinfo.dat");
        entries.add(entry);
        model.addRow(new Object[]{entry.getName(), entry.getLicensePlate(), entry.getId(), entry.getTimeIn(), entry.getTimeOut()});
        //System.out.println("Saving to file: " + cfile);
//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter(new FileWriter(cfile));
//            for (Entry c : entries) {
//                String line = c.getName() + ";" + c.getLicensePlate() + ";" + c.getId() + ";" + c.getTimeIn() + ";" + c.getTimeOut();
//                pw.println(line);
//                if (Objects.equals(c.getTimeOut(), "-"))
//                {
//                    space-=1;
//
//                }else{;}
//            }
//            System.out.println(space + " spaces remaining");
//            //System.out.println("Saved " + entries.size() + " concerts");
//            pw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (Entry c : entries)
//        {
//            if (c.getTimeOut()=="")
//            {
//                space-=1;
//                System.out.println(space + " spaces remaining");
//            }else{System.out.println(space + " spaces remaining");}
//        }

//        if (entries.size()<10)
//        {
//            entries.add(entry);
//            model.addRow(new Object[]{entry.getName(), entry.getLicensePlate(), entry.getId(), entry.getTimeIn(), entry.getTimeOut()});
//        }
//        else if (entries.size()==10)
//        {
//            entries.add(entry);
//            model.addRow(new Object[]{entry.getName(), entry.getLicensePlate(), entry.getId(), entry.getTimeIn(), entry.getTimeOut()});
//            JOptionPane.showMessageDialog(null, "This is the last space, the parking lot is now full");
//        }
//        else if (entries.size()>10)
//        {
//            JOptionPane.showMessageDialog(null, "The parking lot is already full");
//        }
    }
    // Update entry method

    public void updateEntry(int index, Entry entry) {
        //File cfile= new File("C:\\Users\\Jon-Paul\\IdeaProjects\\NEX_T\\src\\entryinfo.dat");
        entries.set(index, entry);
        model.setValueAt(entry.getName(), index, 0);
        model.setValueAt(entry.getLicensePlate(), index, 1);
        model.setValueAt(entry.getId(), index, 2);
        model.setValueAt(entry.getTimeIn(), index, 3);
        model.setValueAt(entry.getTimeOut(), index, 4);

//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter(new FileWriter(cfile));
//            for (Entry c : entries) {
//                String line = c.getName() + ";" + c.getLicensePlate() + ";" + c.getId() + ";" + c.getTimeIn() + ";" + c.getTimeOut();
//                pw.println(line);
//                if (Objects.equals(c.getTimeOut(), "-"))
//                {
//                    space-=1;
//
//                }else{;}
//            }
//            System.out.println(space + " spaces remaining");
//            //System.out.println("Saved " + entries.size() + " concerts");
//            pw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    public void deleteEntry(int index) {
//        int space =10;
//        File cfile= new File("C:\\Users\\Jon-Paul\\IdeaProjects\\NEX_T\\src\\entryinfo.dat");
        entries.remove(index);
        model.removeRow(index);

    }



    private void SaveandClose (ArrayList<Entry> entries, String cfile) {
        System.out.println("Saving to file: " + cfile);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(cfile));
            for (Entry c : entries) {
                String line = c.getName() + ";" + c.getLicensePlate() + ";" + c.getId() + ";" + c.getTimeIn() + ";" + c.getTimeOut();
                pw.println(line);
            }
            System.out.println("Saved " + entries.size() + " concerts");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }

    public void addSecurityGuard(SecurityGuard securityGuard) {
        //int space =10;
        File cfile= new File("C:\\Users\\ezra\\Documents\\NEX_TFinal\\src\\securityinfo.dat");
        //securityGuards.add(securityGuard);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(cfile));
            String line = securityGuard.getName() + ";" + securityGuard.getId() + ";" + securityGuard.getPassword();
            pw.println(line);
            //System.out.println("Saved " + securityGuards.size() + " concerts");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


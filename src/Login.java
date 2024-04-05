import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Login extends JFrame {
    //initialize button, panel, label, and text field  
    JButton submitButton;
    JPanel loginPanel;
    JLabel userLabel, passwordLabel;
    final JTextField userField, passwordField;
    private ArrayList<String> users = new ArrayList<>();

    //calling constructor  
    Login() {
        //create label for username   
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1  

        //create text field to get username from the user  
        userField = new JTextField(15);    //set length of the text  

        //create label for password  
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");      //set label value for textField2  

        //create text field to get password from the user  
        passwordField = new JPasswordField(15);    //set length for the password

        //create submit button  
        submitButton = new JButton("SUBMIT"); //set label to button

        //create panel to put form elements  
        loginPanel = new JPanel(new GridLayout(3, 1));
        loginPanel.add(userLabel);    //set username label to panel  
        loginPanel.add(userField);   //set text field to panel  
        loginPanel.add(passwordLabel);    //set password label to panel  
        loginPanel.add(passwordField);   //set text field to panel  
        loginPanel.add(submitButton);           //set button to panel  

        //set border to panel   
        add(loginPanel, BorderLayout.CENTER);
        setTitle("LOGIN");         //set title to the login form
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(true);

        try {
            //create instance of the CreateLoginForm    
            loginPanel.setSize(300, 100);  //set size of the frame
            loginPanel.setVisible(true); //make form visible to the user 
        } catch (Exception e) {
            //handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //users = loadUsers("C:\\Users\\janth\\Downloads\\NEX_T-main\\src\\logininfo.txt");

        setLoginButtonActionListener(new ActionListener()     //pass action listener as a parameter  
        {
            @Override
            public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
            {
                String username = userField.getText();        //get user entered username from the textField1  
                String password = passwordField.getText();        //get user entered pasword from the textField2  
                //check whether the credentials are authentic or not  
                //boolean err=true;
                /*for(String user:users){
                    String[] arruser = user.split(";");
                    String loguname = arruser[0];
                    String logpword = arruser[1];
                    */
                if (username.equals("admin") && password.equals("admin")) {
                    System.out.println("Welcome " + username);
                    JOptionPane.showMessageDialog(null, "Welcome " + username);
                    dispose();
                    EntryListing entryListing = new EntryListing();
                    entryListing.setVisible(true);
                } else {
                    System.out.println("Incorrect. Please enter a valid username and password");
                    JOptionPane.showMessageDialog(null, "Incorrect. Please enter a valid username and password");
                }
            }
        });
        setVisible(true);
    }

    private void setLoginButtonActionListener(ActionListener listener) {
        // Set the action listener for the enter button
        submitButton.addActionListener(listener);
    }

}
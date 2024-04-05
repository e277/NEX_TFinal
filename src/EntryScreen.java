import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EntryScreen extends JFrame {
    private static ArrayList<Entry> entries = new ArrayList<>();
    private JButton enterButton;

    private JButton addSecurityGuardButton = new JButton("Add Security Guard");

    //This holds the GUI and the functionality for the Entry Screen of the program.
    public EntryScreen() {
        super("Entry Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Create a JLabel with the background image
        JLabel background = new JLabel();
        background.setLayout(new BorderLayout());
        setContentPane(background);
        background.setOpaque(true);

        // Add the message label to the north of the label
//        JLabel messageLabel = new JLabel();
//        messageLabel.setForeground(Color.WHITE);
//        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
//        messageLabel.setHorizontalAlignment(JLabel.CENTER);
//        background.add(messageLabel, BorderLayout.NORTH);

        // Add the button panel to the south of the label
        JPanel buttonPanel = new JPanel();//new GridLayout(2, 1));
        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        enterButton.setPreferredSize(new Dimension(100, 40));

        buttonPanel.add(enterButton);
        buttonPanel.add(addSecurityGuardButton);
        background.add(buttonPanel, BorderLayout.SOUTH);


//        JPanel loginPanel = new JPanel(new GridLayout(3, 1));
//
//        JTextField userField = new JTextField();
//        userField.setFont(new Font("SansSerif", Font.BOLD, 16));
//        userField.setPreferredSize(new Dimension(100, 40));
//        loginPanel.add(userField);
//
//        JPasswordField passwordField = new JPasswordField();
//        passwordField.setFont(new Font("SansSerif", Font.BOLD, 16));
//        passwordField.setPreferredSize(new Dimension(100, 40));
//        loginPanel.add(passwordField);
//
//        JButton loginButton = new JButton("Login");
//        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
//        loginButton.setPreferredSize(new Dimension(100, 40));
//        loginPanel.add(loginButton);
//        background.add(loginPanel, BorderLayout.SOUTH);


    }

    public static void main(String[] args) throws IOException {
        // Create and show entry screen
        EntryScreen entryScreen = new EntryScreen();
        try {
            Image backgroundImage = ImageIO.read(new File("C:\\Users\\ezra\\Documents\\NEX_TFinal\\src\\nextlogopic.jpg"));
//            InputStream inputStream = EntryScreen.class.getResourceAsStream("src/nextlogopic.jpg");
//            Image backgroundImage = ImageIO.read(inputStream);
            entryScreen.setBackgroundImage(backgroundImage);
        } catch (IOException e) {
            System.err.println("Error loading background image: " + e.getMessage());
        }
        entryScreen.setEnterButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryScreen.dispose();
                Login login = new Login();
                login.setVisible(true);
            }
        });
        entryScreen.setVisible(true);

        entryScreen.setAddSecurityGuardButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryScreen.dispose();
                CreateSecurityGuard createSecurityGuard = new CreateSecurityGuard();
                createSecurityGuard.setVisible(true);
            }
        });
    }

    private void setBackgroundImage(Image backgroundImage) {
        if (backgroundImage != null) {
            // Calculate the scale factor to preserve aspect ratio
            double widthScaleFactor = (double) getWidth() / backgroundImage.getWidth(null);
            double heightScaleFactor = (double) getHeight() / backgroundImage.getHeight(null);
            double scaleFactor = Math.min(widthScaleFactor, heightScaleFactor);

            // Create a scaled instance of the image
            Image scaledImage = backgroundImage.getScaledInstance((int) (backgroundImage.getWidth(null) * scaleFactor),
                    (int) (backgroundImage.getHeight(null) * scaleFactor), Image.SCALE_SMOOTH);

            // Set the scaled image as the icon of the content pane JLabel
            ((JLabel) getContentPane()).setIcon(new ImageIcon(scaledImage));
            revalidate();
        }
    }

//    private void setMessage(String msg) {
//        ((JLabel) getContentPane().getComponent(0)).setText(msg);
//    }

    private void setEnterButtonActionListener(ActionListener listener) {
        // Set the action listener for the enter button
        enterButton.addActionListener(listener);
    }

    public void setAddSecurityGuardButtonActionListener(ActionListener listener) {
        // Set the action listener for the enter button
        addSecurityGuardButton.addActionListener(listener);
    }

}


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame {
    private static ArrayList<Student> entries = new ArrayList<>();
    private JButton enterButton;

    private JButton addSecurityGuardButton = new JButton("Add Security Guard");

    //This holds the GUI and the functionality for the Entry Screen of the program.
    public Main() {
        super("Entry Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Create a JLabel with the background image
        JLabel background = new JLabel();
        background.setLayout(new BorderLayout());
        setContentPane(background);
        background.setOpaque(true);

        // Add the button panel to the south of the label
        JPanel buttonPanel = new JPanel();//new GridLayout(2, 1));
        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        enterButton.setPreferredSize(new Dimension(100, 40));

        buttonPanel.add(enterButton);
        buttonPanel.add(addSecurityGuardButton);
        background.add(buttonPanel, BorderLayout.SOUTH);

    }

    public static void main(String[] args) throws IOException {
        // Create and show entry screen
        Main entryScreen = new Main();
        try {
            Image backgroundImage = ImageIO.read(new File("NEX_TFinal\\src\\nextlogopic.jpg"));
            entryScreen.setBackgroundImage(backgroundImage);
        } catch (IOException e) {
            System.err.println("Error loading background image: " + e.getMessage());
        }

        entryScreen.setEnterButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryScreen.dispose();
                LoginForm login = new LoginForm();
                login.setVisible(true);
            }
        });
        entryScreen.setVisible(true);

        entryScreen.setAddSecurityGuardButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryScreen.dispose();
                SecurityGuardEntry createSecurityGuard = new SecurityGuardEntry();
                createSecurityGuard.setVisible(true);
            }
        });
        entryScreen.setVisible(true);
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


    private void setEnterButtonActionListener(ActionListener listener) {
        // Set the action listener for the enter button
        enterButton.addActionListener(listener);
    }

    public void setAddSecurityGuardButtonActionListener(ActionListener listener) {
        // Set the action listener for the enter button
        addSecurityGuardButton.addActionListener(listener);
    }

}


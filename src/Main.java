import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private JButton enterButton;

    public Main() {
        super("Nex_T Parking Management System");
        setupFrame();
        setupBackground();
        setupButtons();
        loadBackgroundImage();
        setupEnterButtonActionListener();
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    private void setupBackground() {
        JLabel background = new JLabel();
        background.setLayout(new BorderLayout());
        setContentPane(background);
        background.setOpaque(true);
    }

    private void setupButtons() {
        JPanel buttonPanel = new JPanel();
        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        enterButton.setPreferredSize(new Dimension(100, 40));

        buttonPanel.add(enterButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadBackgroundImage() {
        try {
            Image backgroundImage = ImageIO.read(new File("src/nextlogopic.jpg"));
            setBackgroundImage(backgroundImage);
        } catch (IOException e) {
            System.err.println("Error loading background image: " + e.getMessage());
        }
    }

    private void setBackgroundImage(Image backgroundImage) {
        if (backgroundImage != null) {
            double widthScaleFactor = (double) getWidth() / backgroundImage.getWidth(null);
            double heightScaleFactor = (double) getHeight() / backgroundImage.getHeight(null);
            double scaleFactor = Math.min(widthScaleFactor, heightScaleFactor);

            Image scaledImage = backgroundImage.getScaledInstance((int) (backgroundImage.getWidth(null) * scaleFactor),
                    (int) (backgroundImage.getHeight(null) * scaleFactor), Image.SCALE_SMOOTH);

            ((JLabel) getContentPane()).setIcon(new ImageIcon(scaledImage));
            revalidate();
            repaint();
        }
    }

    private void setupEnterButtonActionListener() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm login = new LoginForm();
                login.setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Main entryScreen = new Main();
        entryScreen.setVisible(true);
    }
}
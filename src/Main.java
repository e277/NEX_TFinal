import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private JButton enterButton;
    private JButton checkAvailabilityButton;
    private JButton generateReportButton;

    public Main() {
        super("Nex_T Parking Management System");
        setupFrame();
        setupBackground();
        setupButtons();
        loadBackgroundImage();
        setupEnterButtonActionListener();
        setupCheckAvailabilityButtonActionListener();
        setupGenerateReportButtonActionListener();
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

        enterButton = new JButton("Login");
        enterButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        enterButton.setPreferredSize(new Dimension(150, 40));

        checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        checkAvailabilityButton.setPreferredSize(new Dimension(200, 40));

        generateReportButton = new JButton("Generate Report");
        generateReportButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        generateReportButton.setPreferredSize(new Dimension(200, 40));

        buttonPanel.add(enterButton);
        buttonPanel.add(checkAvailabilityButton);
        buttonPanel.add(generateReportButton);
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
//                dispose();
                LoginForm login = new LoginForm();
                login.setVisible(true);
            }
        });
    }

    private void setupCheckAvailabilityButtonActionListener() {
        checkAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParkingLot checkAvailability = new ParkingLot();
                checkAvailability.setVisible(true);
            }
        });
    }

    private void setupGenerateReportButtonActionListener() {
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataLogReport dataLogReport = new DataLogReport();
                dataLogReport.setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Main entryScreen = new Main();
        entryScreen.setVisible(true);
    }
}
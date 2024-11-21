import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;

public class ImageViewerApp extends JFrame {
    private JLabel imageLabel;
    private ImageIcon imageIcon;
    private double scaleFactor = 1.0; // Domyślny współczynnik skalowania

    public ImageViewerApp() {
        setTitle("Image Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);

        // Pasek
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Panel obrazu
        imageLabel = new JLabel("", JLabel.CENTER);
        imageLabel.setBackground(Color.GRAY);
        imageLabel.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(imageLabel);
        add(scrollPane, BorderLayout.CENTER);

        // Panel przycisków
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");
        JButton fitToWindowButton = new JButton("Fit to Window");
        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);
        buttonPanel.add(fitToWindowButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Obsługa wczytywania obrazu
        openMenuItem.addActionListener(e -> openImage());

        // Obsługa zbliżania
        zoomInButton.addActionListener(e -> zoomImage(1.2));

        // Obsługa oddalania
        zoomOutButton.addActionListener(e -> zoomImage(0.8));

        // Obsługa dopasowania do okna
        fitToWindowButton.addActionListener(e -> fitImageToWindow());

        // Obsługa kółka myszy do zoomowania
        imageLabel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) { // Scroll up
                    zoomImage(1.1);
                } else { // Scroll down
                    zoomImage(0.9);
                }
            }
        });

        setVisible(true);
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg", "bmp", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            imageLabel.setIcon(imageIcon);
            scaleFactor = 1.0; // Resetujemy skalę przy wczytaniu nowego obrazu
        }
    }

    private void zoomImage(double scale) {
        if (imageIcon != null) {
            scaleFactor *= scale;
            int newWidth = (int) (imageIcon.getIconWidth() * scaleFactor);
            int newHeight = (int) (imageIcon.getIconHeight() * scaleFactor);
            Image scaledImage = imageIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }

    private void fitImageToWindow() {
        if (imageIcon != null) {
            int labelWidth = imageLabel.getWidth();
            int labelHeight = imageLabel.getHeight();
            int imgWidth = imageIcon.getIconWidth();
            int imgHeight = imageIcon.getIconHeight();

            double widthScale = (double) labelWidth / imgWidth;
            double heightScale = (double) labelHeight / imgHeight;
            scaleFactor = Math.min(widthScale, heightScale);

            int newWidth = (int) (imgWidth * scaleFactor);
            int newHeight = (int) (imgHeight * scaleFactor);
            Image scaledImage = imageIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageViewerApp::new);
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private double scaleFactor = 1.0;
    private final JLabel imageLabel;
    private final JFileChooser openFileChooser;
    private final JScrollPane scrollPane1;
    private BufferedImage originalBI;

    public ImagePanel() {
        setLayout(new BorderLayout()); // Устанавливаем макет для текущей панели

        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        openFileChooser = new JFileChooser();
        openFileChooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        scrollPane1 = new JScrollPane(imageLabel);

        JButton fileButton = new JButton("File");

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(this::openFileButtonActionPerformed);
        popupMenu.add(openItem);

        fileButton.addActionListener(e -> popupMenu.show(fileButton, 0, fileButton.getHeight()));
        buttonPanel1.add(fileButton);

        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");
        JButton fitToWindowButton = new JButton("Fit To Window");

        buttonPanel2.add(zoomInButton);
        buttonPanel2.add(zoomOutButton);
        buttonPanel2.add(fitToWindowButton);

        zoomInButton.addActionListener(e -> zoomIn());
        zoomOutButton.addActionListener(e -> zoomOut());
        fitToWindowButton.addActionListener(e -> fitImageToWindow());

        add(buttonPanel1, BorderLayout.NORTH); // Добавляем панель кнопок в верхнюю часть
        add(scrollPane1, BorderLayout.CENTER); // Добавляем скролл с изображением в центр
        add(buttonPanel2, BorderLayout.SOUTH); // Добавляем панель кнопок в нижнюю часть
    }

    private void zoomIn() {
        if (originalBI != null) {
            scaleFactor *= 1.2;
            scaleImage();
        } else {
            JOptionPane.showMessageDialog(this, "No image loaded!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void zoomOut() {
        if (originalBI != null) {
            scaleFactor /= 1.2;
            scaleImage();
        } else {
            JOptionPane.showMessageDialog(this, "No image loaded!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void scaleImage() {
        if (originalBI != null) {
            int newWidth = (int) (originalBI.getWidth() * scaleFactor);
            int newHeight = (int) (originalBI.getHeight() * scaleFactor);

            Image scaledImage = originalBI.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.revalidate();
            imageLabel.repaint();
        }
    }

    private void fitImageToWindow() {
        if (originalBI != null) {
            int width = scrollPane1.getViewport().getWidth();
            int height = scrollPane1.getViewport().getHeight();

            Image scaledImage = originalBI.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.revalidate();
            imageLabel.repaint();

            scaleFactor = (double) width / originalBI.getWidth();
        } else {
            JOptionPane.showMessageDialog(this, "No image loaded!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void openFileButtonActionPerformed(ActionEvent e) {
        int returnValue = openFileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = openFileChooser.getSelectedFile();
                originalBI = ImageIO.read(selectedFile);

                imageLabel.setIcon(new ImageIcon(originalBI));

                JOptionPane.showMessageDialog(this, "File opened successfully!", "File Open", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "File selection canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

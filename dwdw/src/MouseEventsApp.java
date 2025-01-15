import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEventsApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse Events Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Panel rysowania
        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.WHITE);

        // Panel boczny
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(200, 400));
        JLabel sideLabel = new JLabel("Informacje o zdarzeniach");
        sidePanel.add(sideLabel);

        // Dodanie listenerów
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sideLabel.setText("Kursor na obszarze rysowania.");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sideLabel.setText("Kursor opuścił obszar rysowania.");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                sideLabel.setText("Przycisk myszy wciśnięty.");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                sideLabel.setText("Przycisk myszy zwolniony.");
            }
        });

        drawingPanel.addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            drawingPanel.resizeShape(notches);
            sideLabel.setText("Zmiana rozmiaru figury o: " + notches);
        });

        // Layout
        frame.setLayout(new BorderLayout());
        frame.add(drawingPanel, BorderLayout.CENTER);
        frame.add(sidePanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}

// Panel rysowania
class DrawingPanel extends JPanel {
    private int shapeSize = 50;
    private int shapeX = 100;
    private int shapeY = 100;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(shapeX, shapeY, shapeSize, shapeSize);
    }

    public void resizeShape(int notches) {
        shapeSize = Math.max(10, shapeSize - notches * 5); // Minimalny rozmiar to 10
        repaint();
    }
}

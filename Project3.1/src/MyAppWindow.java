import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class MyAppWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DrawingFrame::new);
    }
}

class DrawingFrame extends JFrame {
    public DrawingFrame() {
        setTitle("Rysowanie na płótnie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);

        setVisible(true);
    }
}

class DrawingPanel extends JPanel {
    private Shape selectedShape = null;
    private int offsetX, offsetY;

    // Figury do rysowania
    private final Rectangle square = new Rectangle(100, 100, 100, 100);
    private final Ellipse2D.Double circle = new Ellipse2D.Double(300, 100, 100, 100);

    public DrawingPanel() {
        setBackground(Color.WHITE);

        // Obsługa zdarzeń myszy
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Sprawdzamy, czy kliknięto w którąś z figur
                if (square.contains(e.getPoint())) {
                    selectedShape = square;
                    offsetX = e.getX() - square.x;
                    offsetY = e.getY() - square.y;
                } else if (circle.contains(e.getPoint())) {
                    selectedShape = circle;
                    offsetX = (int) (e.getX() - circle.getX());
                    offsetY = (int) (e.getY() - circle.getY());
                } else {
                    selectedShape = null;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Po zwolnieniu myszy resetujemy wybraną figurę
                selectedShape = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedShape != null) {
                    if (selectedShape == square) {
                        square.setLocation(e.getX() - offsetX, e.getY() - offsetY);
                    } else if (selectedShape == circle) {
                        circle.setFrame(e.getX() - offsetX, e.getY() - offsetY, circle.getWidth(), circle.getHeight());
                    }
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fill(square);

        g2d.setColor(Color.RED);
        g2d.fill(circle);
    }
}

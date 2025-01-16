import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class MovingRectanglesGame extends JFrame {
    private DrawPanel drawPanel;
    private JSlider xSlider, ySlider;
    private JButton huntButton;
    private ArrayList<MovingRectangle> rectangles;

    public MovingRectanglesGame() {
        setTitle("Moving Rectangles Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        rectangles = new ArrayList<>();

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        xSlider = new JSlider(0, 800, 400);
        ySlider = new JSlider(JSlider.VERTICAL, 0, 600, 300);

        huntButton = new JButton("Poluj");
        huntButton.addActionListener(e -> hunt());

        controlPanel.add(xSlider, BorderLayout.SOUTH);
        controlPanel.add(ySlider, BorderLayout.WEST);
        controlPanel.add(huntButton, BorderLayout.NORTH);

        add(controlPanel, BorderLayout.WEST);

        setVisible(true);
    }

    private void hunt() {
        int targetX = xSlider.getValue();
        int targetY = ySlider.getValue();

        rectangles.removeIf(rectangle -> rectangle.contains(targetX, targetY));
        drawPanel.repaint();
    }

    private class DrawPanel extends JPanel {
        private Point startPoint = null;

        public DrawPanel() {
            setBackground(Color.WHITE);
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (startPoint == null) {
                        startPoint = e.getPoint();
                    } else {
                        Point endPoint = e.getPoint();
                        MovingRectangle rect = new MovingRectangle(startPoint, endPoint);
                        rectangles.add(rect);
                        new Thread(rect).start();
                        startPoint = null;
                        repaint();
                    }
                }
            });
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (MovingRectangle rect : rectangles) {
                rect.draw(g);
            }
            g.setColor(Color.RED);
            g.drawLine(xSlider.getValue(), 0, xSlider.getValue(), getHeight());
            g.drawLine(0, ySlider.getValue(), getWidth(), ySlider.getValue());
        }
    }

    private class MovingRectangle implements Runnable {
        private int x, y, width, height;
        private int dx, dy;
        private Color color;
        private Random rand = new Random();

        public MovingRectangle(Point start, Point end) {
            this.x = Math.min(start.x, end.x);
            this.y = Math.min(start.y, end.y);
            this.width = Math.abs(start.x - end.x);
            this.height = Math.abs(start.y - end.y);
            this.dx = rand.nextInt(5) + 1;
            this.dy = rand.nextInt(5) + 1;
            this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        }

        public void run() {
            while (true) {
                x += dx;
                y += dy;
                if (x < 0 || x + width > drawPanel.getWidth()) dx = -dx;
                if (y < 0 || y + height > drawPanel.getHeight()) dy = -dy;
                drawPanel.repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ignored) {}
            }
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }

        public boolean contains(int px, int py) {
            return px >= x && px <= x + width && py >= y && py <= y + height;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovingRectanglesGame::new);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class RectangleHunt extends JFrame {
    private DrawArea drawArea;
    private JSlider sliderX;
    private JSlider sliderY;
    private JButton huntButton;
    private ArrayList<MovingRectangle> rectangles;

    public RectangleHunt() {
        setTitle("Rectangle Hunt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        rectangles = new ArrayList<>();

        drawArea = new DrawArea();
        add(drawArea, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 1));

        sliderX = new JSlider(0, 800, 400);
        sliderX.setMajorTickSpacing(100);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);
        controlPanel.add(new JLabel("Horizontal Slider (X):"));
        controlPanel.add(sliderX);

        sliderY = new JSlider(0, 600, 300);
        sliderY.setOrientation(JSlider.VERTICAL);
        controlPanel.add(new JLabel("Vertical Slider (Y):"));
        controlPanel.add(sliderY);

        huntButton = new JButton("Hunt Rectangles");
        huntButton.addActionListener(e -> huntRectangles());
        controlPanel.add(huntButton);

        add(controlPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void huntRectangles() {
        int huntX = sliderX.getValue();
        int huntY = sliderY.getValue();

        rectangles.removeIf(rectangle -> rectangle.contains(huntX, huntY));
        drawArea.repaint();
    }

    private class DrawArea extends JPanel {
        private Point startPoint;

        public DrawArea() {
            setBackground(Color.WHITE);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Point endPoint = e.getPoint();
                    if (startPoint != null) {
                        Rectangle rect = new Rectangle(Math.min(startPoint.x, endPoint.x),
                                Math.min(startPoint.y, endPoint.y),
                                Math.abs(startPoint.x - endPoint.x),
                                Math.abs(startPoint.y - endPoint.y));
                        MovingRectangle movingRectangle = new MovingRectangle(rect);
                        rectangles.add(movingRectangle);
                        new Thread(movingRectangle).start();
                        startPoint = null;
                        repaint();
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (MovingRectangle rectangle : rectangles) {
                rectangle.draw(g);
            }
        }
    }

    private class MovingRectangle implements Runnable {
        private Rectangle rectangle;
        private int dx, dy;

        public MovingRectangle(Rectangle rectangle) {
            this.rectangle = rectangle;
            Random random = new Random();
            dx = random.nextInt(5) + 1;
            dy = random.nextInt(5) + 1;
        }

        public void draw(Graphics g) {
            g.setColor(Color.BLUE);
            g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }

        public boolean contains(int x, int y) {
            return rectangle.contains(x, y);
        }

        @Override
        public void run() {
            while (true) {
                rectangle.x += dx;
                rectangle.y += dy;

                if (rectangle.x < 0 || rectangle.x + rectangle.width > drawArea.getWidth()) {
                    dx = -dx;
                }
                if (rectangle.y < 0 || rectangle.y + rectangle.height > drawArea.getHeight()) {
                    dy = -dy;
                }

                drawArea.repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RectangleHunt::new);
    }
}
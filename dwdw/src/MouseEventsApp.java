import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MouseEventsApp extends JFrame {
    private final JLabel infoLabel; // Панель для отображения сообщений
    private final DrawingPanel drawingPanel; // Панель для рисования

    public MouseEventsApp() {
        setTitle("Mouse Events Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Создаем панель для сообщений
        infoLabel = new JLabel("Welcome to the Drawing Panel!");
        infoLabel.setPreferredSize(new Dimension(800, 30));
        add(infoLabel, BorderLayout.SOUTH);

        // Создаем панель для рисования
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MouseEventsApp();
    }

    static class DrawingPanel extends JPanel {
        private final ArrayList<Figure> figures = new ArrayList<>();
        private Figure activeFigure; // Фигура, на которой используется колесо мыши

        public DrawingPanel() {
            setBackground(Color.WHITE);

            // Добавляем несколько фигур
            figures.add(new Figure(100, 100, 50, 50, Color.YELLOW, "rectangle"));
            figures.add(new Figure(200, 150, 60, 60, Color.BLUE, "circle"));

            // Добавляем обработчики мыши
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    updateInfo("Mouse entered the drawing area.");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    updateInfo("Mouse exited the drawing area.");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    updateInfo("Mouse button pressed.");
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    updateInfo("Mouse button released.");
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // Определяем активную фигуру при клике
                    for (Figure figure : figures) {
                        if (e.getX() >= figure.x && e.getX() <= figure.x + figure.width &&
                                e.getY() >= figure.y && e.getY() <= figure.y + figure.height) {
                            activeFigure = figure;
                            updateInfo("Active figure selected.");
                            return;
                        }
                    }
                    activeFigure = null;
                    updateInfo("No figure selected.");
                }
            });

            addMouseWheelListener(e -> {
                if (activeFigure != null) {
                    int notches = e.getWheelRotation();
                    if (notches < 0) {
                        activeFigure.increaseSize();
                        updateInfo("Figure size increased.");
                    } else {
                        activeFigure.decreaseSize();
                        updateInfo("Figure size decreased.");
                    }
                    repaint();
                }
            });
        }

        private void updateInfo(String message) {
            // Обновляем текст на панели сообщений
            Container parent = getParent();
            if (parent instanceof JFrame) {
                JLabel label = ((MouseEventsApp) parent).infoLabel;
                label.setText(message);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            for (Figure figure : figures) {
                g2d.setColor(figure.color);
                if (figure.shapeType.equals("rectangle")) {
                    g2d.fillRect(figure.x, figure.y, figure.width, figure.height);
                } else if (figure.shapeType.equals("circle")) {
                    g2d.fillOval(figure.x, figure.y, figure.width, figure.height);
                }
            }
        }

        static class Figure {
            int x, y, width, height;
            Color color;
            String shapeType;

            public Figure(int x, int y, int width, int height, Color color, String shapeType) {
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
                this.color = color;
                this.shapeType = shapeType;
            }

            public void increaseSize() {
                width += 10;
                height += 10;
            }

            public void decreaseSize() {
                width = Math.max(10, width - 10);
                height = Math.max(10, height - 10);
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DragAndMovePanel extends JPanel {

    private final ArrayList<Figure> figures = new ArrayList<>();
    private Figure selectedShape = null;

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

        public boolean contains(int px, int py) {
            return px >= x && px <= x + width && py >= y && py <= y + height;
        }

        public void moveTo(int newX, int newY) {
            this.x = newX - width / 2;
            this.y = newY - height / 2;
        }
    }

    public DragAndMovePanel() {

        figures.add(new Figure(10, 50, 50, 50, Color.YELLOW, "rectangle"));
        figures.add(new Figure(100, 50, 60, 60, Color.BLUE, "circle"));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                for (Figure figure : figures) {
                    if (figure.contains(mouseX, mouseY)) {
                        selectedShape = figure;
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedShape = null; // Сбрасываем выделенную фигуру
            }
        });

        // Обработчик перемещения мыши
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedShape != null) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();
                    selectedShape.moveTo(mouseX, mouseY); // Перемещаем фигуру
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Figure figure : figures) {
            g2d.setColor(figure.color);
            switch (figure.shapeType) {
                case "rectangle":
                    g2d.fillRect(figure.x, figure.y, figure.width, figure.height);
                    break;
                case "circle":
                    g2d.fillOval(figure.x, figure.y, figure.width, figure.height);
                    break;
            }
        }
    }
}

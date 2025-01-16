import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KeyEventsPanel extends JPanel {

    private Figure selectedShape = null;
    private boolean isKeyPressed = false;
    private final ArrayList<Figure> figures = new ArrayList<>();

    static class Figure {
        int x, y, width, height;
        Color color;
        String shapeType;
        Color previousColor;

        public Figure(int x, int y, int width, int height, Color color, String shapeType) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.previousColor = color;
            this.shapeType = shapeType;

        }

        public boolean contains(int px, int py) {
            return px >= x && px <= x + width && py >= y && py <= y + height;
        }

    }

    public KeyEventsPanel() {
        // Добавляем фигуры
        figures.add(new Figure(10, 50, 50, 50, Color.GREEN, "rectangle"));
        figures.add(new Figure(100, 50, 60, 60, Color.BLUE, "circle"));

        addMouseListener(new MouseAdapter() {
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
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isKeyPressed) {
                    isKeyPressed = true;
                    char keyChar = e.getKeyChar();
                    if (keyChar == 'A' || keyChar == 'a') { // Увеличиваем размеры фигур
                            selectedShape.width += 10;
                            selectedShape.height += 10;

                    }else if(keyChar == 'B' || keyChar == 'b') {
                        selectedShape.previousColor = selectedShape.color;
                        selectedShape.color = Color.YELLOW;
                    }
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                isKeyPressed = false; // Сбрасываем флаг
                char keyChar = e.getKeyChar();
                if (keyChar == 'A' || keyChar == 'a') { // Возвращаем размеры фигур
                    selectedShape.width -= 10;
                    selectedShape.height -= 10;
                }else if(keyChar == 'B' || keyChar == 'b') {
                    selectedShape.color = selectedShape.previousColor;
                }
                repaint();
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
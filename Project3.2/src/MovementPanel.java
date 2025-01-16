import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MovementPanel extends JPanel {

    private final ArrayList<Figure> figures = new ArrayList<>();

     static class Figure {
        int x, y, width, height;
        int dx, dy;
        Color color;
        String shapeType;

        public Figure(int x, int y, int width, int height, Color color, String shapeType, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.shapeType = shapeType;
            this.dx = dx;
            this.dy = dy;
        }

        public void move() {
            x += dx;
            y += dy;

            // Проверка границ
            if (x < 0 || x + width > 680) { // 680 — ширина панели, измените при необходимости
                dx = -dx;
            }
            if (y < 0 || y + height > 325) { // 325 — высота панели
                dy = -dy;
            }
        }

        public boolean contains(int px, int py) {
            return px >= x && px <= x + width && py >= y && py <= y + height;
        }

        public void setDirection(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public void stop() {
            this.dx = 0;
            this.dy = 0;
        }
    }

    public MovementPanel() {
        // Добавляем фигуры
        figures.add(new Figure(10, 50, 50, 50, Color.YELLOW, "rectangle", 0, 0));
        figures.add(new Figure(100, 50, 60, 60, Color.BLUE, "circle", 0, 0));


        Timer timer = new Timer(5, e -> {
            for (Figure figure : figures) {
                figure.move(); // Обновляем координаты каждой фигуры
            }
            repaint(); // Перерисовываем панель
        });
        timer.start(); // Таймер запускается сразу

        // Добавляем обработчик кликов для управления движением каждой фигуры
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Figure figure : figures) {
                    if (figure.contains(e.getX(), e.getY())) {
                        // Определяем, куда нажали: верх, низ, лево, право
                        int centerX = figure.x + figure.width / 2;
                        int centerY = figure.y + figure.height / 2;

                        int dx = 0, dy = 0;
                        if (e.getX() < centerX) { // Лево
                            dx = -2;
                        } else if (e.getX() > centerX) { // Право
                            dx = 2;
                        }
                        if (e.getY() < centerY) { // Верх
                            dy = -2;
                        } else if (e.getY() > centerY) { // Низ
                            dy = 2;
                        }

                        figure.setDirection(dx, dy);
                    }
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (keyChar == 'A' || keyChar == 'a') { // Увеличиваем размеры фигур
                    for (Figure figure : figures) {
                        figure.stop();
                    }
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

            // Рисуем в зависимости от типа фигуры
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
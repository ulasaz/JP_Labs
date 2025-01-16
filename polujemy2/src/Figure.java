import java.awt.*;
import java.util.Random;

public class Figure {
    ApplicationPanel ap;

    int x, y, width, height;
    int speedX, speedY;
    Color color;

    public Figure(int x, int y, int width, int height, Color color, ApplicationPanel ap) {
        this.ap = ap;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

        Random rand = new Random();
        // Генерация случайной скорости от -5 до 5, исключая 0 (чтобы фигура точно двигалась)
        speedX = rand.nextInt(11) - 5;
        speedY = rand.nextInt(11) - 5;

        // Проверка, чтобы скорость не была нулевой
        if (speedX == 0) speedX = 1;
        if (speedY == 0) speedY = 1;
    }
    public boolean contains(int px, int py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }

    public void update() {
        x += speedX;
        y += speedY;

        // Получаем размеры панели рисования через публичные методы
        int drawWidth = ap.getDrawPanelWidth();
        int drawHeight = ap.getDrawPanelHeight();

        // Ограничение по правой и левой границе
        if (x < 0) {
            x = 0;
            speedX = -speedX;
        } else if (x + width > drawWidth) {
            x = drawWidth - width;
            speedX = -speedX;
        }

        // Ограничение по верхней и нижней границе
        if (y < 0) {
            y = 0;
            speedY = -speedY;
        } else if (y + height > drawHeight) {
            y = drawHeight - height;
            speedY = -speedY;
        }
    }



    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x, y, width, height);
    }
}

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MouseHandler extends MouseAdapter {
    private final ApplicationPanel ap;
    private Point startPoint = null;

    public MouseHandler(ApplicationPanel ap) {
        this.ap = ap;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (startPoint == null) {
            // Первый клик — сохранить начальную точку
            startPoint = e.getPoint();
        } else {
            // Второй клик — создать прямоугольник
            Point endPoint = e.getPoint();

            // Вычисляем координаты и размеры прямоугольника
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);

            // Генерация случайного цвета
            Color randomColor = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));

            // Создание фигуры
            Figure newFigure = new Figure(x, y, width, height, randomColor, ap);

            // Создание двигающегося прямоугольника
            MovingRectangle movingRect = new MovingRectangle(ap, newFigure);

            // Добавление фигуры в панель
            ap.addFigure(newFigure);

            // Запуск потока для движения
            new Thread(movingRect).start();

            // Сброс начальной точки для следующей фигуры
            startPoint = null;

            // Перерисовка панели
            ap.repaint();
        }
    }
}

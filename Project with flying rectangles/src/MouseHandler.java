import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    private final ApplicationPanel ap;  // Панель для рисования
    private Point startPoint;

    public MouseHandler(ApplicationPanel ap) {
        this.ap = ap;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();  // Запоминаем начальную точку
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point endPoint = e.getPoint();

        // Вычисляем левый верхний угол
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);

        // Вычисляем ширину и высоту
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);

        // Создаём случайный цвет для фигуры
        Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());

        // Создаём фигуру
        Figure newFigure = new Figure(x, y, width, height, randomColor, ap);

        // Создаём двигающийся прямоугольник
        MovingRectangle rect = new MovingRectangle(ap, newFigure);

        // Добавляем фигуру на панель для отображения
        ap.addFigure(newFigure);

        // Запускаем движение фигуры в отдельном потоке
        new Thread(rect).start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationPanel extends JPanel {
    final int screenWidth = 668;
    final int screenHeight = 776;
    public boolean isGameOver = false;

    private final MouseHandler mouseH = new MouseHandler(this);
    private final List<Figure> figures = new ArrayList<>();

    private final JSlider xSlider;
    private final JSlider ySlider;
    private final JButton huntButton;  // Кнопка "Охота"

    private final DrawPanel drawPanel;

    public ApplicationPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setLayout(new BorderLayout());
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        // Панель для рисования
        drawPanel = new DrawPanel();
        drawPanel.addMouseListener(mouseH);
        this.add(drawPanel, BorderLayout.CENTER);

        // Горизонтальный слайдер (X)
        xSlider = new JSlider(0, screenWidth, screenWidth / 2);
        xSlider.setOrientation(JSlider.HORIZONTAL);

        // Вертикальный слайдер (Y)
        ySlider = new JSlider(0, screenHeight, screenHeight / 2);
        ySlider.setOrientation(JSlider.VERTICAL);

        // Кнопка "Охота"
        huntButton = new JButton("Охота");
        huntButton.addActionListener(e -> hunt());  // Логика охоты

        // Панель для размещения слайдеров и кнопки
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(xSlider, BorderLayout.SOUTH);
        controlPanel.add(ySlider, BorderLayout.WEST);
        controlPanel.add(huntButton, BorderLayout.NORTH);  // Кнопка сверху

        this.add(controlPanel, BorderLayout.WEST);
    }

    // Логика кнопки "Охота"
    private void hunt() {
        int targetX = xSlider.getValue();  // Текущая позиция по X
        int targetY = ySlider.getValue();  // Текущая позиция по Y

        // Удаляем фигуры, попавшие под перекрестие
        figures.removeIf(figure -> figure.contains(targetX, targetY));
        repaint();
    }

    // Метод для добавления фигур
    public void addFigure(Figure figure) {
        figures.add(figure);
        repaint();
    }

    // Панель для рисования фигур
    private class DrawPanel extends JPanel {
        public DrawPanel() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // Отрисовка всех фигур
            for (Figure figure : figures) {
                figure.draw(g2);
            }

            // Рисуем перекрестие по значениям слайдеров
            g2.setColor(Color.RED);
            g2.drawLine(xSlider.getValue(), 0, xSlider.getValue(), getHeight());
            g2.drawLine(0, ySlider.getValue(), getWidth(), ySlider.getValue());

            g2.dispose();
        }
    }
    // Геттер для панели рисования
// Геттер для ширины панели рисования
    public int getDrawPanelWidth() {
        return drawPanel.getWidth();
    }

    // Геттер для высоты панели рисования
    public int getDrawPanelHeight() {
        return drawPanel.getHeight();
    }

}

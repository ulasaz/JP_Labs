import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationPanel extends JPanel {
    final int screenWidth = 568;
    final int screenHeight = 776;
    public boolean isGameOver = false;

    KeyHandler keyH = new KeyHandler();

    private final List<Figure> figures = new ArrayList<>();

    public ApplicationPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);

        Figure figure = new Figure(this, keyH);
        FigureMove rect = new FigureMove(this, figure);
        addFigure(figure);
        new Thread(rect).start();
    }
    public void addFigure(Figure figure) {
        figures.add(figure);
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Figure figure : figures) {
            figure.draw(g2);
        }
        g2.dispose();
    }
}

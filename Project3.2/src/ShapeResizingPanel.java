import javax.swing.*;
import java.awt.*;

public class ShapeResizingPanel extends JPanel {
    int shapeSize = 50;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);
        g2d.fillRect(50, 50, shapeSize, shapeSize);

    }

    public void resizeShape(int notches) {
        shapeSize = Math.max(10, shapeSize - notches * 5);
        repaint();
    }
}
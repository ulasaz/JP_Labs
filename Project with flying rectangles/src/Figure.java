
import java.awt.*;

public class Figure {
    ApplicationPanel ap;

    int x, y, width, height, speed = 5;
    Color color;

    public Figure(int x, int y, int width, int height, Color color, ApplicationPanel ap) {
        this.ap = ap;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }


    public void update() {
        x += speed;
        if (x > ap.getWidth()) {
            x = -width;
        }
    }


    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x, y, width, height);
    }
}

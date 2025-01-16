import java.awt.*;
import javax.swing.*;

public class Figure {
    ApplicationPanel ap;
    KeyHandler keyH;

    int x, y, width, height, speed = 30;
    String direction;
    Color color;
    int ground = 746;
    boolean isFall = false;
    int fallSpeed = 50;

    Timer fallTimer;
    Timer moveTimer;

    public Figure( ApplicationPanel ap, KeyHandler keyH) {
        this.ap = ap;
        this.keyH = keyH;
        this.x = 150;
        this.y = 0;
        this.width = 30;
        this.height = 30;
        this.color = Color.BLUE;

        fallTimer = new Timer(1000, e -> fall());
        fallTimer.start();

        moveTimer = new Timer(1000, e -> update());  // Движение обновляется каждые 100 мс
        moveTimer.start();
    }


    public void fall() {
        if (y + fallSpeed < ground) {
            y += fallSpeed;
        } else {
            y = ground;
            createFigure();
            isFall = true;
            fallTimer.stop();
            moveTimer.stop();
        }
    }

    public void update() {
        if(!isFall) {

            if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
                if (x < 0) {
                    x = 0;
                }
                keyH.leftPressed = false;
            }

            if (keyH.rightPressed) {
                direction = "right";
                if (x + width + speed <= ap.getWidth()) {
                    x += speed;
                } else {
                    x = ap.getWidth() - width;
                }
                keyH.rightPressed = false;
            }
            if (keyH.downPressed) {
                fall();
            }
        }
    }

    public void createFigure() {

        Figure newFigure = new Figure(ap, keyH);

        FigureMove rect = new FigureMove(ap, newFigure);

        ap.addFigure(newFigure);

        new Thread(rect).start();
    }
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x, y, width, height);
    }
}

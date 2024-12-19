import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Obstacle {
    int x, y;
    int speed;
    public BufferedImage onPlace;
    GamePanel gp;
    Player player;



    public Obstacle(GamePanel gp, Player player, int speed) {
        this.player = player;
        this.gp = gp;
        this.speed = speed;
        randomPosition();
        loadImage();
    }

    private void randomPosition() {
        Random rand = new Random();
        x = gp.screenWidth + rand.nextInt(200);

        int minY = player.ground - 300;
        int maxY = player.ground;
        y = minY + rand.nextInt(maxY - minY + 1);

    }

    private void loadImage() {
        try {
            onPlace = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Enemy/enemy.png")));
        } catch (IOException e) {
            System.out.println("Error loading image: /Enemy/enemy.png");
            e.printStackTrace();
        }
    }

    public void update() {
        x -= speed;
        if (x + gp.tileSize < 0) {
            randomPosition();
        }
    }

    public void checkCollision() {
        if (player.x < x + gp.tileSize &&
                player.x + gp.tileSize > x &&
                player.y < y + gp.tileSize &&
                player.y + gp.tileSize > y) {
            gp.isGameOver = true;
            gp.isGamePaused = true;
            System.out.println("Game paused due to collision!");
            int response = JOptionPane.showOptionDialog(
                    gp,
                    "Your score is: " +  gp.getScore() +" Game Over! Try again?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[] {"Yes", "No"},
                    "Yes"
            );

            if (response == JOptionPane.YES_OPTION) {
                gp.restartGame(); // Вызов метода для перезапуска игры
            } else {
                System.exit(0); // Полностью закрываем игру
            }
            }

    }


    public void draw(Graphics2D g2) {
        g2.drawImage(onPlace, x, y, gp.tileSize, gp.tileSize, null);
    }
}

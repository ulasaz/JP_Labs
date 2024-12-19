import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Apple {
    GamePanel gp;
    Player player;

    boolean eaten = false;
    int x = 100;
    int y = 300;
    public BufferedImage onPlace;
    public int score = 0; // Счетчик рекорда

    public Apple(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            onPlace = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Apple/Apple.png")));
        } catch (IOException e) {
            System.out.println("Error loading image: /Apple/Apple.png");
            e.printStackTrace();
        }
    }

    public void update() {
        eaten = false;
        Random rand = new Random();

        // Ширина игрового поля (адаптируйте границы при необходимости)
        x = rand.nextInt(gp.screenWidth - gp.tileSize);

        // Высота игрового поля (яблоко появляется в пределах досягаемости прыжка игрока)
        int minY = player.ground - player.jumpHeight; // Минимальная высота, до которой игрок достаёт
        int maxY = player.ground;                     // Максимальная высота (уровень земли)
        y = minY + rand.nextInt(maxY - minY + 1);
    }

    public void checkCollision() {
        if (player.x < x + gp.tileSize &&
                player.x + gp.tileSize > x &&
                player.y < y + gp.tileSize &&
                player.y + gp.tileSize > y) {
            eaten = true;
            score++;
            System.out.println(score);
            gp.updateScore(score);

            try {
                File soundFile = new File(Objects.requireNonNull(getClass().getResource("/Sounds/apple_sound.wav")).getFile());
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            update();
        }
    }

    public void draw(Graphics2D g2) {
        if (!eaten) {
            g2.drawImage(onPlace, x, y, gp.tileSize, gp.tileSize, null);
        }
    }
}

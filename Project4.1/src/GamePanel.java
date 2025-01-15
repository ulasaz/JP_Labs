import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel {
    private final JFrame parentWindow;
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize; // 768
    final int screenHeight = maxScreenRow * tileSize; // 576
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private final BufferedImage backgroundImage;
    public boolean isGamePaused = false;
    boolean isGameOver = false;

    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    Apple apple = new Apple(this, player);

    Thread appleThread;
    Thread playerThread;
    Thread obstacleThread;

    private int score = 0;

    public GamePanel(JFrame parentWindow) {
        this.parentWindow = parentWindow;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true); // Ускоряет отрисовку
        this.addKeyListener(keyH);
        this.setFocusable(true);

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Background/back.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PlayerMove playerMove = new PlayerMove(this, player);
        playerThread = new Thread(playerMove);
        playerThread.start();

        AppleMove appleMove = new AppleMove(apple, this);
        appleThread = new Thread(appleMove);
        appleThread.start();

        ObstacleMove obstacleMove = new ObstacleMove(2, player, this);
        obstacleThread = new Thread(obstacleMove);
        obstacleThread.start();

        System.out.println("GamePanel initialized. Threads started.");
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (offScreenImage == null) {
            offScreenImage = createImage(screenWidth, screenHeight);
            offScreenGraphics = offScreenImage.getGraphics();
        }
        offScreenGraphics.clearRect(0, 0, screenWidth, screenHeight);

        Graphics2D g2 = (Graphics2D) g;

        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);
        }

        player.draw(g2);
        apple.draw(g2);

        for (Obstacle obstacle : ObstacleMove.getObstacles()) {
            obstacle.draw(g2);
        }

        drawScore(g2);

        g2.dispose();
    }

    private void drawScore(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        String scoreText = "Score: " + score;
        g2.drawString(scoreText, 20, 40);
    }

    public void updateScore(int score) {
        this.score = score;
        repaint();
    }

    public int getScore() {
        return this.score;
    }
    public void restartGame() {
        if (parentWindow != null) {
            parentWindow.dispose();
        }
        new Game();
    }

}

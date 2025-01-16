import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlatformerGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}

class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("Platformer Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        setVisible(true);
        gamePanel.startGame();
    }
}

class GamePanel extends JPanel {
    private Player player;
    private final CopyOnWriteArrayList<Obstacle> obstacles = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Resource> resources = new CopyOnWriteArrayList<>();
    private volatile int score = 0;

    public GamePanel() {
        setFocusable(true);
        setBackground(Color.CYAN);

        player = new Player(50, 500);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> player.moveLeft();
                    case KeyEvent.VK_RIGHT -> player.moveRight();
                    case KeyEvent.VK_SPACE -> player.jump();
                }
            }
        });
    }

    public void startGame() {
        new Thread(this::obstacleThread).start();
        new Thread(this::resourceThread).start();
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while (true) {
            player.update();
            for (Obstacle obstacle : obstacles) {
                obstacle.update();
                if (obstacle.collidesWith(player)) {
                    JOptionPane.showMessageDialog(this, "Game Over! Final Score: " + score);
                    System.exit(0);
                }
            }

            for (Resource resource : resources) {
                if (resource.collidesWith(player)) {
                    resources.remove(resource);
                    score += 10;
                }
            }
            repaint();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void obstacleThread() {
        while (true) {
            obstacles.add(new Obstacle(800, 500));
            try {
                Thread.sleep(2000); // Przeszkody co 2 sekundy
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void resourceThread() {
        while (true) {
            resources.add(new Resource((int) (Math.random() * 750), 500));
            try {
                Thread.sleep(3000); // Zasoby co 3 sekundy
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
        for (Resource resource : resources) {
            resource.draw(g);
        }

        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
    }
}

class Player {
    private int x, y;
    private int velocityY = 0;
    private boolean isJumping = false;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        x = Math.max(x - 10, 0);
    }

    public void moveRight() {
        x = Math.min(x + 10, 750);
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocityY = -15;
        }
    }

    public void update() {
        if (isJumping) {
            y += velocityY;
            velocityY += 1; // Gravity
            if (y >= 500) {
                y = 500;
                isJumping = false;
                velocityY = 0;
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }
}

class Obstacle {
    private int x, y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        x -= 5;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 50, 50);
    }

    public boolean collidesWith(Player player) {
        return new Rectangle(x, y, 50, 50).intersects(player.getBounds());
    }
}

class Resource {
    private int x, y;

    public Resource(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, 30, 30);
    }

    public boolean collidesWith(Player player) {
        return new Rectangle(x, y, 30, 30).intersects(player.getBounds());
    }
}

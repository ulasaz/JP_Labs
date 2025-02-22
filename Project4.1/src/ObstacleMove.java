import java.util.ArrayList;
import java.util.Random;

public class ObstacleMove implements Runnable {
    static ArrayList<Obstacle> obstacles;
    private final GamePanel gp;

    public ObstacleMove(int obstacleCount, Player player, GamePanel gp) {
        this.gp = gp;
        obstacles = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < obstacleCount; i++) {
            int speed = 4 + rand.nextInt(3);
            obstacles.add(new Obstacle(gp, player, speed));
        }
    }

    @Override
    public void run() {
        while (true) {
            if (!gp.isGamePaused) {
                for (Obstacle obstacle : obstacles) {
                    obstacle.update();

                    obstacle.checkCollision();
                }
                gp.repaint();
            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}

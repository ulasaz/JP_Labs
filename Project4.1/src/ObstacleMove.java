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
                    obstacle.update(); // Обновляем положение препятствий
                    obstacle.checkCollision(); // Проверяем столкновения с игроком
                }
                gp.repaint(); // Перерисовываем экран
            }

            try {
                Thread.sleep(16); // Ожидание для поддержания ~60 FPS
            } catch (InterruptedException e) {
                break; // Прерываем цикл, если поток был прерван
            }
        }
    }

    public static ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}

public class PlayerMove implements Runnable{
    private final GamePanel gp;
    private final Player player;

    public PlayerMove(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
    }

    @Override
    public void run() {
        while (!gp.isGameOver) {
            player.update(); // Обновляем состояние игрока
            gp.repaint(); // Перерисовываем экран
            try {
                Thread.sleep(16); // Задержка для ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

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
            player.update();
            gp.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

public class PlayerMove implements Runnable{
    private final ApplicationPanel ap;
    private final Player player;

    public PlayerMove(ApplicationPanel ap, Player player) {
        this.ap = ap;
        this.player = player;
    }

    @Override
    public void run() {
        while (!ap.isGameOver) {
            player.update();
            ap.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

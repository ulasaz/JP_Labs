public class AppleMove implements Runnable {
    private final Apple apple;
    private final GamePanel gp;

    public AppleMove(Apple apple, GamePanel gp) {
        this.apple = apple;
        this.gp = gp;
    }

    @Override
    public void run() {
        while (true) {
            apple.checkCollision();

            if (apple.eaten) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    break;
                }
                apple.update();
                apple.eaten = false;
                gp.repaint();
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}

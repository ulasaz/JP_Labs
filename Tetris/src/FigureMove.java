public class FigureMove implements Runnable{
    private final ApplicationPanel ap;
    private final Figure figure;

    public FigureMove(ApplicationPanel ap, Figure figure) {
        this.ap = ap;
        this.figure = figure;
    }

    @Override
    public void run() {
        while (!ap.isGameOver) {
            figure.update();
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

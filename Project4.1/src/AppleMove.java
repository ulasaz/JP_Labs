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
            apple.checkCollision(); // Проверка столкновения

            if (apple.eaten) { // Если яблоко съедено
                try {
                    Thread.sleep(2000); // Ожидаем 2 секунды
                } catch (InterruptedException e) {
                    break;
                }
                apple.update();      // Обновляем позицию яблока
                apple.eaten = false; // Сбрасываем состояние съеденности
                gp.repaint();        // Перерисовываем экран
            }

            try {
                Thread.sleep(50); // Маленькая пауза, чтобы снизить нагрузку на CPU
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}

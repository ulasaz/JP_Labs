public class AddingTemperature implements Runnable {

    private final Temperature temperature;

    public AddingTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public void run() {
        while (true) {
            try {
                double temp = temperature.generateTemperature();
                Temperature.temperatureBuffer.put(temp);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

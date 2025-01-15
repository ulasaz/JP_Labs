import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Temperature {
    public static final BlockingQueue<Double> temperatureBuffer = new LinkedBlockingQueue<>();

    public double generateTemperature() {
        Random rand = new Random();
        return rand.nextInt(30 - 10 + 1) + 10;
    }
}


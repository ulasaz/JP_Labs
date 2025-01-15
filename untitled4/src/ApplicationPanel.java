import javax.swing.*;
import java.awt.*;

public class ApplicationPanel extends JPanel {
    final int screenWidth = 768;
    final int screenHeight = 576;
    Thread temperatureThread;
    Temperature temp = new Temperature();
    public ApplicationPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);


        AddingTemperature addingTemperature = new AddingTemperature(temp);
        temperatureThread = new Thread(addingTemperature);
        temperatureThread.start();


        JLabel temperatureLabel = new JLabel("Temperature");
        this.add(temperatureLabel);
        Thread displayThread = new Thread(() -> {
            try {
                while (true) {
                    Double temp = Temperature.temperatureBuffer.take();
                    SwingUtilities.invokeLater(() -> temperatureLabel.setText("Temperature: " + temp));
                    Thread.sleep(500); // Частота обновления интерфейса
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        displayThread.start();
    }
}

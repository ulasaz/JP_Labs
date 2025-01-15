import javax.swing.JFrame;

public class Application {
    JFrame window;

    public Application() {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Application");

        ApplicationPanel applicationPanel = new ApplicationPanel();
        window.add(applicationPanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public static void launchApp() {
        new Application();
    }
}

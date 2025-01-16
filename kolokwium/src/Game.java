import javax.swing.*;

public class Game {
    JFrame window;

    public Game() {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");

        ApplicationPanel applicationPanel = new ApplicationPanel();
        window.add(applicationPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}


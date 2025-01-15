
import javax.swing.*;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {


        JFrame frame = new JFrame("My App");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 250, 700, 400);


        JTabbedPane tabPanel = new JTabbedPane();
        tabPanel.addTab("Login", new LoginPanel());
        tabPanel.addTab("Contacts", new ContactPanel());
        tabPanel.addTab("Calculator", new CalculatorPanel());
        tabPanel.addTab("Dynamic", new DynamicPanel());
        tabPanel.addTab("Image", new ImagePanel());

        frame.add(tabPanel);
        frame.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DynamicPanel extends JPanel {
    private final JButton button_target;
    private final JPanel centerPanel;

    public DynamicPanel() {
        setLayout(new BorderLayout()); // Устанавливаем макет для текущей панели

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        JButton button_change_color = new JButton("Change color");
        button_change_color.addActionListener(this::Button_change_color);
        JButton button_change_position = new JButton("Change position");
        button_change_position.addActionListener(this::Button_change_position);
        JButton button_change_size = new JButton("Change size");
        button_change_size.addActionListener(this::Button_change_size);

        topPanel.add(button_change_color);
        topPanel.add(button_change_position);
        topPanel.add(button_change_size);

        button_change_color.setPreferredSize(new Dimension(140, 60));
        button_change_size.setPreferredSize(new Dimension(140, 60));
        button_change_position.setPreferredSize(new Dimension(140, 60));

        centerPanel = new JPanel();
        centerPanel.setLayout(null);

        button_target = new JButton("Target Button");
        button_target.setBounds(280, 50, 140, 80);
        centerPanel.add(button_target);

        add(topPanel, BorderLayout.NORTH); // Добавляем верхнюю панель
        add(centerPanel, BorderLayout.CENTER); // Добавляем центральную панель
    }

    public void Button_change_color(ActionEvent e) {
        Color initialcolor = Color.RED;
        Color color = JColorChooser.showDialog(this,
                "Select a color", initialcolor);
        if (color != null) {
            button_target.setBackground(color);
        }
    }

    public void Button_change_size(ActionEvent e) {
        try {
            String widthStr = JOptionPane.showInputDialog("Enter the new width:");
            String heightStr = JOptionPane.showInputDialog("Enter the new height:");
            if (widthStr != null && heightStr != null) {
                int width = Integer.parseInt(widthStr);
                int height = Integer.parseInt(heightStr);

                button_target.setBounds(button_target.getX(), button_target.getY(), width, height);
                centerPanel.repaint();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Button_change_position(ActionEvent e) {
        try {
            String xStr = JOptionPane.showInputDialog("Enter the new x:");
            String yStr = JOptionPane.showInputDialog("Enter the new y:");
            if (xStr != null && yStr != null) {
                int x = Integer.parseInt(xStr);
                int y = Integer.parseInt(yStr);

                button_target.setBounds(x, y, button_target.getWidth(), button_target.getHeight());
                centerPanel.repaint();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class TextAppWithTabs extends JFrame {

    public TextAppWithTabs() {
        setTitle("Tabbed App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel textPanel = createTextPanel();
        tabbedPane.addTab("Text Panel", textPanel);

        MovementPanel movementPanel = new MovementPanel();
        tabbedPane.addTab("Movement Panel", movementPanel);

        KeyEventsPanel sizePanel = new KeyEventsPanel();
        tabbedPane.addTab("Key Events Panel", sizePanel);

        JPanel panelwith = MouseEventsPanel();
        tabbedPane.addTab("Mouse Events Panel", panelwith);

        DragAndMovePanel dragAndMovePanel = new DragAndMovePanel();
        tabbedPane.addTab("Drag and Move Panel", dragAndMovePanel);

        add(tabbedPane);

        setVisible(true);
    }

    private JPanel MouseEventsPanel() {
        JPanel mouseEventsPanel = new JPanel();
        mouseEventsPanel.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.setPreferredSize(new Dimension(300, 50));

        JLabel label1 = new JLabel();
        label1.setText("Information");
        bottomPanel.add(label1);

        ShapeResizingPanel shapeResizingPanel = new ShapeResizingPanel();

        mouseEventsPanel.add(shapeResizingPanel, BorderLayout.CENTER);
        mouseEventsPanel.add(bottomPanel, BorderLayout.SOUTH);

        shapeResizingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                label1.setText("The cursor left the drawing area.");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label1.setText("Cursor on drawing area.");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                label1.setText("Mouse button pressed.");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                label1.setText("Mouse button released.");
            }
        });

        shapeResizingPanel.addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            shapeResizingPanel.resizeShape(notches);
            label1.setText("Changing the size of a figure");
        });
        return mouseEventsPanel;
    }

    private JPanel createTextPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 30));
        panel.add(textField);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                switch (keyChar) {
                    case 'A':
                    case 'a':
                        textField.setForeground(Color.RED);
                        break;
                    case 'B':
                    case 'b':
                        textField.setForeground(Color.BLUE);
                        break;
                    case 'C':
                    case 'c':
                        textField.setText("");
                        break;
                }
            }
        });

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse is clicked at position: " + e.getX() + " " + e.getY());
            }
        });
        return panel;
    }

    public static void launchApp() {
        new TextAppWithTabs();
    }
}


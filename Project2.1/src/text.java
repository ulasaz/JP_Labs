import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.sqrt;

class TextApp extends JFrame implements ActionListener {

        private double currentNumber = 0;

        private String currentOperator = "";

        private final JTextArea textArea;
        private final JButton button_target;
        private final DefaultListModel<String> listModel;
        private final JList<String> jList;
        private final JTextField textField, textField1, textField2, textField3, textField_equals;
        private final JPasswordField passwordField;
        private final JPanel centerPanel;
        private final JLabel imageLabel;
        private final JScrollPane scrollPane1;
        private double scaleFactor = 1.0;



        private final JFileChooser openFileChooser;
        private BufferedImage originalBI;


        TextApp() {
                setTitle("My App");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension dimension = toolkit.getScreenSize();
                setBounds(dimension.width / 2 - 250, dimension.height / 2 - 250, 700, 400);

                JTabbedPane tabPanel = new JTabbedPane();

                JPanel panel1 = new JPanel();
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Login:");
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                textField = new JTextField(16);
                textField.setMaximumSize(textField.getPreferredSize());
                textField.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel label2 = new JLabel("Password:");
                label2.setAlignmentX(Component.CENTER_ALIGNMENT);
                passwordField = new JPasswordField(16);
                passwordField.setMaximumSize(passwordField.getPreferredSize());
                passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

                JButton button = new JButton("Submit");
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.addActionListener(this);


                textArea = new JTextArea(10, 10);
                textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
                textArea.setEditable(false);

                panel1.add(label);
                panel1.add(textField);
                panel1.add(Box.createVerticalStrut(5));
                panel1.add(label2);
                panel1.add(passwordField);
                panel1.add(Box.createVerticalStrut(10));
                panel1.add(button);
                panel1.add(Box.createVerticalStrut(10));
                panel1.add(textArea);

                JPanel panel2 = new JPanel();
                panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

                JPanel gridPanel = new JPanel();
                gridPanel.setLayout(new GridLayout(4, 2, 10, 10));

                JLabel label3 = new JLabel("Name:");
                textField1 = new JTextField(16);

                JLabel label4 = new JLabel("Phone:");
                textField2 = new JTextField(16);

                JLabel label5 = new JLabel("Email:");
                textField3 = new JTextField(16);

                gridPanel.add(label3);
                gridPanel.add(textField1);
                gridPanel.add(label4);
                gridPanel.add(textField2);
                gridPanel.add(label5);
                gridPanel.add(textField3);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

                listModel = new DefaultListModel<>();
                jList = new JList<>(listModel);
                jList.setVisibleRowCount(5);
                JScrollPane scrollPane = new JScrollPane(jList);
                scrollPane.setPreferredSize(new Dimension(600, 100));

                JButton button1 = new JButton("Add contact");
                button1.addActionListener(e -> addContact());
                JButton button2 = new JButton("Edit contact");
                button2.addActionListener(e -> editContact());
                JButton button3 = new JButton("Delete contact");
                button3.addActionListener(e -> deleteContact());

                buttonPanel.add(button1);
                buttonPanel.add(button2);
                buttonPanel.add(button3);

                panel2.add(gridPanel);
                panel2.add(Box.createVerticalStrut(10));
                panel2.add(buttonPanel);
                panel2.add(Box.createVerticalStrut(10));
                panel2.add(scrollPane);

                JPanel panel3 = new JPanel();
                panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                JPanel gridPanel1 = new JPanel();
                gridPanel1.setLayout(new GridLayout(5, 4, 5, 5));

                textField_equals = new JTextField();
                textField_equals.setEditable(false); // Только для чтения
                textField_equals.setHorizontalAlignment(JTextField.RIGHT);
                textField_equals.setPreferredSize(new Dimension(600, 40));
                textField_equals.setFont(new Font("Arial", Font.BOLD, 18));

                JButton button_AC = new JButton("AC");
                JButton button_plus_minus = new JButton("+/-");
                JButton button_percent = new JButton("%");
                JButton button_divide = new JButton("/");
                JButton button_1 = new JButton("1");
                JButton button_2 = new JButton("2");
                JButton button_3 = new JButton("3");
                JButton button_multiply = new JButton("x");
                JButton button_4 = new JButton("4");
                JButton button_5 = new JButton("5");
                JButton button_6 = new JButton("6");
                JButton button_minus = new JButton("-");
                JButton button_7 = new JButton("7");
                JButton button_8 = new JButton("8");
                JButton button_9 = new JButton("9");
                JButton button_plus = new JButton("+");
                JButton button_zero = new JButton("0");
                JButton button_comma = new JButton(",");
                JButton button_equals = new JButton("=");
                JButton button_sqrt = new JButton("√");

                button_equals.setPreferredSize(new Dimension(80, 60));

                button_1.addActionListener(this::appendNumber);
                button_2.addActionListener(this::appendNumber);
                button_3.addActionListener(this::appendNumber);
                button_4.addActionListener(this::appendNumber);
                button_5.addActionListener(this::appendNumber);
                button_6.addActionListener(this::appendNumber);
                button_7.addActionListener(this::appendNumber);
                button_8.addActionListener(this::appendNumber);
                button_9.addActionListener(this::appendNumber);
                button_zero.addActionListener(this::appendNumber);
                button_percent.addActionListener(this::appendNumber);
                button_divide.addActionListener(this::appendNumber);
                button_multiply.addActionListener(this::appendNumber);
                button_plus.addActionListener(this::appendNumber);
                button_minus.addActionListener(this::appendNumber);
                button_sqrt.addActionListener(this::appendNumber);
                button_comma.addActionListener(this::appendNumber);
                button_equals.addActionListener(this::appendNumber);
                button_AC.addActionListener(this::appendNumber);
                button_percent.addActionListener(this::appendNumber);
                button_plus_minus.addActionListener(this::appendNumber);
                button_comma.addActionListener(this::appendNumber);
                button_sqrt.addActionListener(e -> {
                        try {
                                double number = Double.parseDouble(textField_equals.getText());
                                if (number < 0) {
                                        textField_equals.setText("Error: Negative number");
                                } else {
                                        double result = sqrt(number);
                                        textField_equals.setText(String.valueOf(result));
                                }
                        } catch (NumberFormatException ignored) {
                                textField_equals.setText("Error");
                        }
                });



                gridPanel1.add(button_AC);
                gridPanel1.add(button_plus_minus);
                gridPanel1.add(button_percent);
                gridPanel1.add(button_divide);
                gridPanel1.add(button_7);
                gridPanel1.add(button_8);
                gridPanel1.add(button_9);
                gridPanel1.add(button_multiply);
                gridPanel1.add(button_4);
                gridPanel1.add(button_5);
                gridPanel1.add(button_6);
                gridPanel1.add(button_minus);
                gridPanel1.add(button_1);
                gridPanel1.add(button_2);
                gridPanel1.add(button_3);
                gridPanel1.add(button_plus);
                gridPanel1.add(button_sqrt);
                gridPanel1.add(button_zero);
                gridPanel1.add(button_comma);
                gridPanel1.add(button_equals);

                panel3.add(textField_equals);
                panel3.add(Box.createVerticalStrut(10));
                panel3.add(gridPanel1);


                JPanel panel4 = new JPanel();
                panel4.setLayout(new BorderLayout());

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

                panel4.add(topPanel, BorderLayout.NORTH);
                panel4.add(centerPanel, BorderLayout.CENTER);


                JPanel panel5 = new JPanel(new BorderLayout());

                JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

                openFileChooser = new JFileChooser();
                openFileChooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));

                imageLabel = new JLabel();
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                imageLabel.setVerticalAlignment(JLabel.CENTER);

                scrollPane1 = new JScrollPane(imageLabel);

                JButton fileButton = new JButton("File");

                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem openItem = new JMenuItem("Open");
                openItem.addActionListener(this::openFileButtonActionPerformed);
                popupMenu.add(openItem);


                fileButton.addActionListener(e -> popupMenu.show(fileButton, 0, fileButton.getHeight()));


                buttonPanel1.add(fileButton);

                JButton ZoomInButton = new JButton("Zoom In");
                JButton ZoomOutButton = new JButton("Zoom Out");
                JButton FitToWindowButton = new JButton("Fit To Window");


                buttonPanel2.add(ZoomInButton);
                buttonPanel2.add(ZoomOutButton);
                buttonPanel2.add(FitToWindowButton);
                ZoomInButton.addActionListener(e -> ZoomIn());
                ZoomOutButton.addActionListener(e -> ZoomOut());
                FitToWindowButton.addActionListener(e -> fitImageToWindow());


                panel5.add(buttonPanel2, BorderLayout.SOUTH);
                panel5.add(buttonPanel1, BorderLayout.NORTH);
                panel5.add(scrollPane1, BorderLayout.CENTER);


                tabPanel.addTab("Tab 1", panel1);
                tabPanel.addTab("Tab 2", panel2);
                tabPanel.addTab("Tab 3", panel3);
                tabPanel.addTab("Tab 4", panel4);
                tabPanel.addTab("Tab 5", panel5);

                add(tabPanel);
                setVisible(true);
        }

        private void ZoomIn() {
                if (originalBI != null) {
                        scaleFactor *= 1.2;
                        scaleImage();
                }
        }

        private void ZoomOut() {
                if (originalBI != null) {
                        scaleFactor /= 1.2;
                        scaleImage();
                }
        }
        private void scaleImage() {
                if (originalBI != null) {
                        int newWidth = (int) (originalBI.getWidth() * scaleFactor);
                        int newHeight = (int) (originalBI.getHeight() * scaleFactor);

                        Image scaledImage = originalBI.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                        imageLabel.setIcon(new ImageIcon(scaledImage));
                        imageLabel.revalidate();
                        imageLabel.repaint();
                }
        }


        private void fitImageToWindow(){
                if(originalBI != null){
                        int width = scrollPane1.getViewport().getWidth();
                        int height = scrollPane1.getViewport().getHeight();

                        Image scaledImage = originalBI.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        imageLabel.setIcon(new ImageIcon(scaledImage));
                        imageLabel.revalidate();
                        imageLabel.repaint();

                        scaleFactor = (double) width / originalBI.getWidth();
                }
        }
        public void openFileButtonActionPerformed(ActionEvent e) {
                int returnValue = openFileChooser.showOpenDialog(this);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                        try {
                                File selectedFile = openFileChooser.getSelectedFile();
                                originalBI = ImageIO.read(selectedFile);

                                imageLabel.setIcon(new ImageIcon(originalBI));

                                JOptionPane.showMessageDialog(this, "File opened successfully!", "File Open", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ioe) {
                                JOptionPane.showMessageDialog(this, "Error opening file: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                } else {
                        JOptionPane.showMessageDialog(this, "File selection canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
        }



        public void Button_change_color(ActionEvent e) {
                Color initialcolor = Color.RED;
                Color color = JColorChooser.showDialog(this,
                        "Select a color", initialcolor);
                button_target.setBackground(color);
        }
        public void Button_change_size(ActionEvent e) {
                String widthStr = JOptionPane.showInputDialog("Enter the new width:");
                String heightStr = JOptionPane.showInputDialog("Enter the new height:");
                int width = Integer.parseInt(widthStr);
                int height = Integer.parseInt(heightStr);

                button_target.setBounds(button_target.getX(), button_target.getY(), width, height);
                centerPanel.repaint();
        }
        public void Button_change_position(ActionEvent e) {
                String widthStr = JOptionPane.showInputDialog("Enter the new x:");
                String heightStr = JOptionPane.showInputDialog("Enter the new y:");
                int x = Integer.parseInt(widthStr);
                int y = Integer.parseInt(heightStr);
                button_target.setBounds(x, y, button_target.getWidth(), button_target.getHeight());
                centerPanel.repaint();

        }

        public void appendNumber(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String input = button.getText();
                if (Character.isDigit(input.charAt(0))) {
                        textField_equals.setText(textField_equals.getText() + input);
                } else if (input.equals("+") || input.equals("-") || input.equals("x") || input.equals("/")){
                        try{
                                currentNumber = Double.parseDouble(textField_equals.getText());
                                currentOperator = input;
                                textField_equals.setText("");
                        } catch(NumberFormatException ignored){
                                textField_equals.setText("Error");
                        }
                } else if(input.equals("%")){
                        try {
                                double number = Double.parseDouble(textField_equals.getText());
                                textField_equals.setText(String.valueOf(number / 10));
                        } catch (NumberFormatException ignored) {
                                textField_equals.setText("Error");
                        }
                }
                else if (input.equals("+/-")){
                        try {
                                double number = Double.parseDouble(textField_equals.getText());
                                number = number * -1;
                                textField_equals.setText(String.valueOf(number));
                        } catch (NumberFormatException ignored) {
                                textField_equals.setText("Error");
                        }
                }  else if (input.equals(",")){
                    if (textField_equals.getText().contains(".")) {
                        return;
                    }
                    textField_equals.setText(textField_equals.getText() + ".");
                }  else if (input.equals("=")) {
                        try {
                                double secondNumber = Double.parseDouble(textField_equals.getText()); // Число после оператора
                                double result = calculate(currentNumber, secondNumber, currentOperator); // Вычисляем результат
                                textField_equals.setText(String.valueOf(result)); // Отображаем результат
                        } catch (NumberFormatException ex) {
                                textField_equals.setText("Error");
                        }
                }
                else if (input.equals("AC")) {
                        resetCalculator();
                }
        }

        private double calculate(double firstNumber, double secondNumber, String operator) {
                switch (operator) {
                        case "+":
                                return firstNumber + secondNumber;
                        case "-":
                                return firstNumber - secondNumber;
                        case "x":
                                return firstNumber * secondNumber;
                        case "/":
                                if (secondNumber != 0) {
                                        return firstNumber / secondNumber;
                                } else {
                                        textField_equals.setText("Error: Division by 0");
                                        return 0;
                                }
                        default:
                                throw new IllegalArgumentException("Invalid operator");
                }
        }
        private void resetCalculator() {
                currentNumber = 0;
                currentOperator = "";
                textField_equals.setText("");
        }


        public void actionPerformed(ActionEvent e) {
                String loginText = textField.getText();
                String passwordText = new String(passwordField.getPassword());
                textArea.setText("Login: " + loginText + "\nPassword: " + passwordText);

                checkIfPasswordIsStrong();
        }

        public void addContact() {
                String loginName = textField1.getText().trim();
                String loginEmail = textField2.getText().trim();
                String loginPhone = textField3.getText().trim();

                if (!loginName.isEmpty() && !loginPhone.isEmpty() && !loginEmail.isEmpty()) {
                        listModel.addElement("Name: " + loginName + ", Phone: " + loginPhone + ", Email: " + loginEmail);
                        clearContactFields();
                } else {
                        JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                }
        }
        private void editContact() {
                int selectedIndex = jList.getSelectedIndex();
                if (selectedIndex != -1) {
                        String loginName = textField1.getText().trim();
                        String loginEmail = textField2.getText().trim();
                        String loginPhone = textField3.getText().trim();

                        if (!loginName.isEmpty() && !loginPhone.isEmpty() && !loginEmail.isEmpty()) {
                                listModel.set(selectedIndex, "Name: " + loginName + ", Phone: " + loginPhone + ", Email: " + loginEmail);
                                clearContactFields();

                        } else {
                                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                }

        }

        private void deleteContact() {
                int selectedIndex = jList.getSelectedIndex();
                if (selectedIndex != -1) {
                        listModel.remove(selectedIndex);
                } else {
                        JOptionPane.showMessageDialog(this, "Select a contact to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                }
        }

        private void clearContactFields() {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
        }

        public void checkIfPasswordIsStrong() {
                String loginText = textField.getText().trim();
                String passwordText = new String(passwordField.getPassword());
                int n = passwordText.length();
                boolean hasLower = false, hasUpper = false, hasDigit = false, specialChar = false;
                Set<Character> set = new HashSet<>(
                        Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));
                for (char i : passwordText.toCharArray()) {
                        if (Character.isLowerCase(i)) hasLower = true;
                        if (Character.isUpperCase(i)) hasUpper = true;
                        if (Character.isDigit(i)) hasDigit = true;
                        if (set.contains(i)) specialChar = true;
                }
                if (n >= 8 && hasLower && hasUpper && hasDigit && specialChar) {
                        JOptionPane.showMessageDialog(this, "Hi, " + loginText + "!\nYour password is strong!", "Password Strength", JOptionPane.INFORMATION_MESSAGE);
                } else {
                        String message = String.format("""
                    Hi, %s!
                    Your password is weak!
                    It should contain at least 8 characters, including:
                    - Lowercase letters
                    - Uppercase letters
                    - Numbers
                    - Special characters (!, @, #, etc.)
                    """, loginText);
                        JOptionPane.showMessageDialog(this,
                                message,
                                "Password Strength",
                                JOptionPane.WARNING_MESSAGE);
                }
        }

        public static void main(String[] args) {
                new TextApp();
        }
}

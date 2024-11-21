import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static java.lang.Math.sqrt;

public class CalculatorPanel extends JPanel {
    private final JTextField textField_equals;
    private double currentNumber = 0;
    private String currentOperator = "";

    public CalculatorPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
        button_sqrt.addActionListener(e -> calculateSquareRoot());
        button_comma.addActionListener(this::appendNumber);
        button_equals.addActionListener(this::appendNumber);
        button_AC.addActionListener(this::appendNumber);
        button_plus_minus.addActionListener(this::appendNumber);

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

        add(textField_equals);
        add(Box.createVerticalStrut(10));
        add(gridPanel1);
    }

    private void calculateSquareRoot() {
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
    }

    public void appendNumber(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String input = button.getText();
        if (Character.isDigit(input.charAt(0))) {
            textField_equals.setText(textField_equals.getText() + input);
        } else if ("+-x/".contains(input)) {
            try {
                currentNumber = Double.parseDouble(textField_equals.getText());
                currentOperator = input;
                textField_equals.setText("");
            } catch (NumberFormatException ignored) {
                textField_equals.setText("Error");
            }
        } else if (input.equals("%")) {
            try {
                double number = Double.parseDouble(textField_equals.getText());
                textField_equals.setText(String.valueOf(number / 10));
            } catch (NumberFormatException ignored) {
                textField_equals.setText("Error");
            }
        } else if (input.equals("+/-")) {
            try {
                double number = Double.parseDouble(textField_equals.getText());
                textField_equals.setText(String.valueOf(-number));
            } catch (NumberFormatException ignored) {
                textField_equals.setText("Error");
            }
        } else if (input.equals(",")) {
            if (!textField_equals.getText().contains(".")) {
                textField_equals.setText(textField_equals.getText() + ".");
            }
        } else if (input.equals("=")) {
            try {
                double secondNumber = Double.parseDouble(textField_equals.getText());
                double result = calculate(currentNumber, secondNumber, currentOperator);
                textField_equals.setText(String.valueOf(result));
            } catch (NumberFormatException ignored) {
                textField_equals.setText("Error");
            }
        } else if (input.equals("AC")) {
            resetCalculator();
        }
    }

    private double calculate(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+" -> {
                return firstNumber + secondNumber;
            }
            case "-" -> {
                return firstNumber - secondNumber;
            }
            case "x" -> {
                return firstNumber * secondNumber;
            }
            case "/" -> {
                if (secondNumber != 0) {
                    return firstNumber / secondNumber;
                } else {
                    textField_equals.setText("Error: Division by 0");
                    return 0;
                }
            }
            default -> throw new IllegalArgumentException("Invalid operator");
        }
    }

    private void resetCalculator() {
        currentNumber = 0;
        currentOperator = "";
        textField_equals.setText("");
    }
}

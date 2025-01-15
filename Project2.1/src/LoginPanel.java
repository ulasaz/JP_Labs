
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LoginPanel extends JPanel implements ActionListener {
    private final JTextField textField;
    private final JPasswordField passwordField;
    private final JTextArea textArea;

    public LoginPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

        add(label);
        add(textField);
        add(Box.createVerticalStrut(5));
        add(label2);
        add(passwordField);
        add(Box.createVerticalStrut(10));
        add(button);
        add(Box.createVerticalStrut(10));
        add(textArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char[] passwordChars = passwordField.getPassword();
        try {
            String loginText = textField.getText().trim();
            textArea.setText("Login: " + loginText + "\nPassword: " + String.valueOf(passwordChars));
            if (checkIfPasswordIsStrong(passwordChars)) {
                JOptionPane.showMessageDialog(this,
                        "Hi, " + loginText + "!\nYour password is strong!",
                        "Password Strength", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Hi, " + loginText + "!\nYour password is weak! It should contain at least 8 characters, including:\n" +
                                "- Lowercase letters\n" +
                                "- Uppercase letters\n" +
                                "- Numbers\n" +
                                "- Special characters (!, @, #, etc.)",
                        "Password Strength", JOptionPane.WARNING_MESSAGE);
            }
        } finally {
            Arrays.fill(passwordChars, '\0');
        }
    }

    private boolean checkIfPasswordIsStrong(char[] passwordChars) {
        int n = passwordChars.length;
        boolean hasLower = false, hasUpper = false, hasDigit = false, specialChar = false;
        Set<Character> specialSet = new HashSet<>(Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));

        for (char c : passwordChars) {
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (specialSet.contains(c)) specialChar = true;
        }

        return n >= 8 && hasLower && hasUpper && hasDigit && specialChar;
    }

}

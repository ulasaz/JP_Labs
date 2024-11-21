import javax.swing.*;
import java.awt.*;

public class ContactPanel extends JPanel {
    private final DefaultListModel<String> listModel;
    private final JList<String> jList;
    private final JTextField textField1, textField2, textField3;

    public ContactPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
        jList = new JList<>(listModel); // Присваиваем значение jList
        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setPreferredSize(new Dimension(600, 100));

        JButton button1 = new JButton("Add contact");
        button1.addActionListener(e -> addContact()); // Обработчик кнопки добавления контакта

        JButton button2 = new JButton("Edit contact");
        button2.addActionListener(e -> editContact()); // Обработчик кнопки редактирования контакта

        JButton button3 = new JButton("Delete contact");
        button3.addActionListener(e -> deleteContact()); // Обработчик кнопки удаления контакта

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        add(gridPanel);
        add(Box.createVerticalStrut(10));
        add(buttonPanel);
        add(Box.createVerticalStrut(10));
        add(scrollPane);
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
        } else {
            JOptionPane.showMessageDialog(this, "Select a contact to edit!", "Error", JOptionPane.ERROR_MESSAGE);
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
}

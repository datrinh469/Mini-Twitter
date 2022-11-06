package Widgets;

import javax.swing.*;

public class TextField implements Widgets {
    private JTextField textField;

    public TextField(String field) {
        textField = new JTextField(field);
    }

    @Override
    public void addWidget(JFrame panel, int x, int y) {
        textField.setBounds(x, y, 180, 50);
        panel.add(textField);
    }
}

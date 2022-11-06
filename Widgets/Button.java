package Widgets;

import javax.swing.*;

public class Button implements Widgets {
    private JButton button;

    public Button(String name) {
        button = new JButton(name);
    }

    @Override
    public void addWidget(JFrame panel, int x, int y) {
        button.setBounds(x, y, 180, 50);
        panel.add(button);
    }
}

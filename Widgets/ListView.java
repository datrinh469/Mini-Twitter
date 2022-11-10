package Widgets;

import javax.swing.*;

public class ListView implements Widgets {
    private JList<String> list;

    public ListView(DefaultListModel<String> data) {
        list = new JList<>(data);
    }

    @Override
    public void addWidget(JFrame panel, int x, int y) {
        list.setBounds(x, y, 380, 150);
        panel.add(list);
    }

    public void reload(DefaultListModel<String> data) {
        list = new JList<>(data);
    }

}

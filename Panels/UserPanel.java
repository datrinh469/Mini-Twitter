package Panels;

import Widgets.*;

import javax.swing.*;

//Composite
public class UserPanel {
    private JFrame user = new JFrame("User Control Panel");
    private Widgets[] userWidgets = new Widgets[6];
    private DefaultListModel<String> currentFollowing = new DefaultListModel<>();
    private DefaultListModel<String> newsFeed = new DefaultListModel<>();

    public UserPanel() {
        user.setSize(420,450);
        user.setLayout(null);
        addWidgets();
        user.setVisible(true);
    }

    //Apply functions here when buttons are pressed
    public void followUser() {

    }

    public void postTweet() {

    }

    private void update() {

    }

    private void addWidgets() {
        userWidgets[0] = new TextField("User ID");
        userWidgets[0].addWidget(user, 10, 10);

        userWidgets[1] = new Button("Follow User");
        userWidgets[1].addWidget(user, 210, 10);

        userWidgets[2] = new ListView(currentFollowing);
        userWidgets[2].addWidget(user, 10, 70);

        userWidgets[3] = new TextField("Tweet Message");
        userWidgets[3].addWidget(user, 10, 230);

        userWidgets[4] = new Button("Tweet Message");
        userWidgets[4].addWidget(user, 210, 230);

        userWidgets[5] = new ListView(newsFeed);
        userWidgets[5].addWidget(user, 10, 290);
    }
}

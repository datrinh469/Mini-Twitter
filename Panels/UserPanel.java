package Panels;

import Users.User;
import Users.UserBase;
import Widgets.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Composite
public class UserPanel implements ActionListener {
    private JFrame user;
    private User userData;
    private Widgets[] userWidgets = new Widgets[5];
    private DefaultListModel<String> currentFollowing = new DefaultListModel<>();

    public UserPanel(User userData) {
        this.userData = userData;
        user = new JFrame(userData.toString() + "'s Control Panel");
        user.setSize(420,450);
        user.setLayout(null);
        currentFollowing.addElement("Current Following");
        addWidgets();
        user.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String userID = ((TextField)userWidgets[0]).getText();
        String tweet = ((TextField)userWidgets[3]).getText();
        if(actionEvent.getSource() == ((Button)userWidgets[1]).getButton()) { //Follow User
            followUser(userID);
        }
        if(actionEvent.getSource() == ((Button)userWidgets[4]).getButton()) { //Tweet Message
            postTweet(tweet);
        }
    }

    //Apply functions here when buttons are pressed
    public void followUser(String id) {
        User followedUser = AdminControlPanel.getUser(id);
        if(followedUser != null && !userData.toString().equals(id)) {
            for(User followed : userData.getFollowing()) {
                if(followed.toString().equals(id)) {
                    return;
                }
            }
            followedUser.add(userData);
            userData.addFollowing(followedUser);
            currentFollowing.addElement("-  " + id);
            updateFollowers();
        }
    }

    public void postTweet(String tweet) {
        userData.addNewsFeed("-  " + userData.toString() + ": " + tweet);
        userData.update();
        userData.addUnmodifiedTweet(tweet);
        AdminControlPanel.incrementTotalUserMessages();
        updateAllFeeds("-  " + userData.toString() + ": " + tweet);
    }

    private void updateFollowers() {
        ((ListView)userWidgets[2]).reload(currentFollowing);
    }

    private void updateAllFeeds(String tweet) {
        for(UserBase user : userData.getFollowers()) {
            ((User)user).addNewsFeed(tweet);
            ((User)user).update();
        }
    }

    private void addWidgets() {
        userWidgets[0] = new TextField("User ID");
        userWidgets[0].addWidget(user, 10, 10);

        userWidgets[1] = new Button("Follow User");
        userWidgets[1].addWidget(user, 210, 10);
        ((Button)userWidgets[1]).setListener(this);

        userWidgets[2] = new ListView(currentFollowing);
        userWidgets[2].addWidget(user, 10, 70);

        userWidgets[3] = new TextField("Tweet Message");
        userWidgets[3].addWidget(user, 10, 230);

        userWidgets[4] = new Button("Tweet Message");
        userWidgets[4].addWidget(user, 210, 230);
        ((Button)userWidgets[4]).setListener(this);

        userData.getDisplayFeed().addWidget(user, 10, 290);
    }
}

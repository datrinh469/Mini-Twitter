package Users;

import Widgets.*;

import javax.swing.*;
import java.util.ArrayList;

//Observer
public class User extends UserBase{
    private ArrayList<User> following;
    private DefaultListModel<String> newsFeed;
    private ArrayList<String> unmodifiedTweet;
    private Widgets displayFeed;

    public User(String id) {
        uniqueID = id;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        newsFeed = new DefaultListModel<>();
        newsFeed.addElement("News Feed");
        unmodifiedTweet = new ArrayList<>();
        displayFeed = new ListView(newsFeed);
    }

    public ArrayList<User> getFollowing() { return following; }

    public void addNewsFeed(String tweet) {
        newsFeed.addElement(tweet);
    }

    public void addUnmodifiedTweet(String tweet) { unmodifiedTweet.add(tweet); }

    public ArrayList<String> getUnmodifiedTweet() { return unmodifiedTweet; }

    public void update() { ((ListView)displayFeed).reload(newsFeed); }

    public Widgets getDisplayFeed() { return displayFeed; }

    public void addFollowing(User toFollow) {
        following.add(toFollow);
    }

    public boolean isUser() { return true; }

}

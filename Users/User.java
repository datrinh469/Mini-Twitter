package Users;

import Widgets.*;

import javax.swing.*;
import java.util.ArrayList;

//Observer
public class User extends UserBase{
    private ArrayList<User> following;
    private DefaultListModel<String> newsFeed;
    private DefaultListModel<String> time;
    private ArrayList<String> unmodifiedTweet;
    private Widgets displayFeed;
    private Widgets displayTime;
    private long lastUpdateTime;

    public User(String id) {
        uniqueID = id;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        newsFeed = new DefaultListModel<>();
        time = new DefaultListModel<>();
        newsFeed.addElement("News Feed");
        unmodifiedTweet = new ArrayList<>();
        displayFeed = new ListView(newsFeed);
        creationTime = System.currentTimeMillis();
        lastUpdateTime = creationTime;
        time.addElement("Creation Time: " + creationTime);
        time.addElement("Last Update Time: " + lastUpdateTime);
        displayTime = new ListView(time);
    }

    public ArrayList<User> getFollowing() { return following; }

    public void addNewsFeed(String tweet) {
        newsFeed.addElement(tweet);
    }

    public void addUnmodifiedTweet(String tweet) { unmodifiedTweet.add(tweet); }

    public ArrayList<String> getUnmodifiedTweet() { return unmodifiedTweet; }

    public void update(long newUpdateTime) {
        ((ListView)displayFeed).reload(newsFeed);
        lastUpdateTime = newUpdateTime;
        time.set(1, "Last Update Time: " + lastUpdateTime);
        ((ListView)displayTime).reload(time);
    }

    public Widgets getDisplayFeed() { return displayFeed; }

    public Widgets getDisplayTime() { return displayTime; }

    public void addFollowing(User toFollow) {
        following.add(toFollow);
    }

    public boolean isUser() { return true; }

    public long getLastUpdateTime() { return lastUpdateTime; }
}

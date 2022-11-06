package Users;

import java.util.ArrayList;

//Observer
public class User extends UserBase{
    private ArrayList<User> following;
    private ArrayList<String> newsFeed;

    public User(String id) {
        uniqueID = id;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        newsFeed = new ArrayList<>();
        following.add(this);
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public ArrayList<String> getNewsFeed() {
        return newsFeed;
    }

    public void addNewsFeed(String tweet) {
        newsFeed.add(tweet);
    }

    public void addFollowing(User toFollow) {
        following.add(toFollow);
    }
}

package Users;

import java.util.ArrayList;

//Observer
public class User implements  UserInterface{
    private String uniqueID;
    private ArrayList<String> followers;
    private ArrayList<String> following;
    private ArrayList<String> newsFeed;

    public User(String id) {
        uniqueID = id;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        newsFeed = new ArrayList<>();
        following.add(uniqueID);
    }

}

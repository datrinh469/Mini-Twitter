package Users;

import java.util.ArrayList;

public class UserBase {
    String uniqueID;
    ArrayList<UserBase> followers;

    public void add(User user) {
        followers.add(user);
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public ArrayList<UserBase> getFollowers() {
        return followers;
    }
}

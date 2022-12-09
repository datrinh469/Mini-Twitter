package Users;

import java.util.ArrayList;

public abstract class UserBase {
    String uniqueID;
    ArrayList<UserBase> followers;
    long creationTime;

    public void add(User user) {
        followers.add(user);
    }

    public String toString() {
        return uniqueID;
    }

    public ArrayList<UserBase> getFollowers() { return followers; }

    public abstract boolean isUser();

    public long getCreationTime() {
        return creationTime;
    }
}

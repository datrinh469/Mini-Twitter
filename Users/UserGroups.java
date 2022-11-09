package Users;

import java.util.ArrayList;

//Composite
public class UserGroups extends UserBase{

    public UserGroups(String id) {
        uniqueID = id;
        followers = new ArrayList<>();
    }

    public void add(UserGroups userGroup) {
        followers.add(userGroup);
    }

    public int getNumOfUsers(int currentCount) {
        int count = currentCount;
        for (UserBase userBase : followers) {
            if(userBase.isUser()) {
                count++;
            }
            else {
                count = ((UserGroups)userBase).getNumOfUsers(count);
            }
        }
        return count;
    }

    public int getNumOfGroups(int currentCount) {
        int count = currentCount;
        for (UserBase userBase : followers) {
            if(!userBase.isUser()) {
                count++;
                count = ((UserGroups)userBase).getNumOfGroups(count);
            }
        }
        return count;
    }

    public int getSize() {
        return followers.size();
    }

    public boolean isUser() { return false; }
}

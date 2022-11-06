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

}

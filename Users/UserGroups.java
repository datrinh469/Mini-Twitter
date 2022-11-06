package Users;

import java.util.ArrayList;

//Composite
public class UserGroups implements UserInterface{
    private String uniqueID;
    private ArrayList<UserInterface> group;

    public UserGroups(String id) {
        uniqueID = id;
        group = new ArrayList<>();
    }

}

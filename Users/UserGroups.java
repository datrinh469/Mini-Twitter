package Users;

import java.util.ArrayList;

//Composite
public class UserGroups extends UserBase{

    public UserGroups(String id) {
        uniqueID = id;
        followers = new ArrayList<>();
        creationTime = System.currentTimeMillis();
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

    public ArrayList<String> getListOfUserFeed(ArrayList<String> currentList) {
        for (UserBase userBase : followers) {
            if(userBase.isUser()) {
                currentList.addAll(((User)userBase).getUnmodifiedTweet());
            }
            else {
                currentList = (((UserGroups)userBase).getListOfUserFeed(currentList));
            }
        }
        return currentList;
    }

    public ArrayList<String> getListOfUsers(ArrayList<String> currentList) {
        for (UserBase userBase : followers) {
            currentList.add(userBase.toString());
            if(!userBase.isUser()) {
                currentList = (((UserGroups)userBase).getListOfUsers(currentList));
            }
        }
        return currentList;
    }


    public static boolean checkForExistingUser(String id, UserGroups root) {
        for (UserBase userBase : root.followers) {
            if(userBase.isUser() && userBase.toString().equals(id)) {
                return true;
            }
            else if(!userBase.isUser()) {
                if(checkForExistingUser(id, (UserGroups) userBase)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkForExistingGroup(String id, UserGroups root) {
        for (UserBase userBase : root.followers) {
            if(!userBase.isUser() && userBase.toString().equals(id)) {
                return true;
            }
            else if(!userBase.isUser()) {
                if(checkForExistingGroup(id, (UserGroups) userBase)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static User getUser(String id, UserGroups root) {
        for (UserBase userBase : root.followers) {
            if(userBase.isUser() && userBase.toString().equals(id)) {
                return (User) userBase;
            }
            else if(!userBase.isUser()) {
                if(checkForExistingUser(id, (UserGroups) userBase)) {
                    return getUser(id, (UserGroups) userBase);
                }
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers(ArrayList<User> list) {
        for (UserBase userBase : followers) {
            if(userBase.isUser()) {
                list.add((User)userBase);
            }
            else {
                return ((UserGroups)userBase).getAllUsers(list);
            }
        }
        return list;
    }

    public boolean isUser() { return false; }
}

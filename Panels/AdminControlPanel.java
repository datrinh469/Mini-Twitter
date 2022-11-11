package Panels;//Singleton and Composite
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Widgets.*;
import Users.*;



public class AdminControlPanel implements ActionListener, TreeSelectionListener {
    private static final AdminControlPanel adminPanel = new AdminControlPanel();
    private static int totalMessages;
    private static UserGroups userRoot;
    private static String[] positiveWords = {"good", "positive", "happy", "great", "excellent", "fantastic", "love"};
    private JFrame admin = new JFrame("Admin Control Panel");
    private Widgets[] adminWidgets = new Widgets[10];
    private DefaultMutableTreeNode userRootNode;
    private DefaultMutableTreeNode selectedNode;

    private AdminControlPanel() {
        admin.setSize(600,400);
        admin.setLayout(null);
        userRoot = new UserGroups("Root");
        userRootNode = new DefaultMutableTreeNode(userRoot, true);
        addWidgets();
        admin.setVisible(true);
        totalMessages = 0;
    }

    public static AdminControlPanel getAdminControlPanel() {
        return adminPanel;
    }

    //Apply functions here for when button is pressed
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String userID = ((TextField)adminWidgets[1]).getText();
        String groupID = ((TextField)adminWidgets[3]).getText();

        if(actionEvent.getSource() == ((Button)adminWidgets[2]).getButton()) { //Add user
            if(!UserGroups.checkForExistingUser(userID, userRoot))
                addUser(userID, selectedNode);
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[4]).getButton()) { //Add group
            if(!UserGroups.checkForExistingGroup(groupID, userRoot)) {
                addGroup(groupID, selectedNode);
            }
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[5]).getButton()) { //Open user view
            openUserView(selectedNode);
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[6]).getButton()) { //Show user total
            showUserTotal();
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[7]).getButton()) { //Show group total
            showGroupTotal();
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[8]).getButton()) { //Show messages total
            showMessagesTotal();
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[9]).getButton()) { //Show positive percentage
            showPositivePercentage();
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        selectedNode = (DefaultMutableTreeNode) (((TreeView)adminWidgets[0]).getTree()).getLastSelectedPathComponent();
    }

    public void addUser(String userID, DefaultMutableTreeNode node) {

        User user = new User(userID);
        DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(user, false);

        if(node == null) {
            userRoot.add(user);
            userRootNode.setUserObject(userRoot);
            update(userRootNode, userNode);
        }
        else {
            if(node.getAllowsChildren()) {
                UserGroups currentGroup = (UserGroups) node.getUserObject();
                currentGroup.add(user);
                node.setUserObject(currentGroup);
                update(node, userNode);
            }
        }
    }

    public void addGroup(String groupID, DefaultMutableTreeNode node) {
        UserGroups group = new UserGroups(groupID);
        DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group, true);

        if(node == null) {
            userRoot.add(group);
            userRootNode.setUserObject(userRoot);
            update(userRootNode, groupNode);
        }
        else {
            if(node.getAllowsChildren()) {
                UserGroups currentGroup = (UserGroups) node.getUserObject();
                currentGroup.add(group);
                node.setUserObject(currentGroup);
                update(node, groupNode);
            }
        }
    }

    public void openUserView(DefaultMutableTreeNode node) {
        if(node != null && !node.getAllowsChildren()) {
            new UserPanel((User) node.getUserObject());
        }
    }

    public void showUserTotal() {
        int userCount = userRoot.getNumOfUsers(0);
        JFrame userTotal = new JFrame("Total Amount of Users");
        userTotal.setSize(300,75);
        userTotal.setLayout(null);
        JTextField field = new JTextField("The Total Number of Users Currently is " + userCount + ".");
        field.setBounds(0, 0, 300, 75);
        userTotal.add(field);
        userTotal.setVisible(true);
    }

    public void showGroupTotal() {
        int groupCount = userRoot.getNumOfGroups(1);
        JFrame groupTotal = new JFrame("Total Amount of Groups");
        groupTotal.setSize(300,75);
        groupTotal.setLayout(null);
        JTextField field = new JTextField("The Total Number of Groups Currently is " + groupCount + ".");
        field.setBounds(0, 0, 300, 75);
        groupTotal.add(field);
        groupTotal.setVisible(true);
    }

    public void showMessagesTotal() {
        JFrame groupTotal = new JFrame("Total Amount of Messages");
        groupTotal.setSize(300,75);
        groupTotal.setLayout(null);
        JTextField field = new JTextField("The Total Number of Messages Currently is " + totalMessages + ".");
        field.setBounds(0, 0, 300, 75);
        groupTotal.add(field);
        groupTotal.setVisible(true);
    }

    public void showPositivePercentage() {
        int numOfPositiveTweets = 0;
        ArrayList<String> list = new ArrayList<>();
        userRoot.getListOfUserFeed(list);

        for(String tweet : list) {
            tweet = tweet.toLowerCase();
            String[] words = tweet.split("\\s+");
            for(String word : words) {
                boolean solved = false;
                for(String positiveWord : positiveWords) {
                    if(word.equals(positiveWord)) {
                        numOfPositiveTweets++;
                        solved = true;
                        break;
                    }
                }
                if(solved) {
                    break;
                }
            }
        }
        float percentage;
        if(list.size() == 0) {
            percentage = 0;
        }
        else {
            percentage = numOfPositiveTweets / (float) list.size();
            percentage *= 100;
        }

        JFrame groupTotal = new JFrame("Percent of Positive Tweet Messages");
        groupTotal.setSize(600,75);
        groupTotal.setLayout(null);
        JTextField field = new JTextField("The Total Number of Positive Messages Currently is " + numOfPositiveTweets +
                " Out of " + list.size() + " Tweets Which Equates to " + (int)percentage + "%.");
        field.setBounds(0, 0, 600, 75);
        groupTotal.add(field);
        groupTotal.setVisible(true);
    }

    public static void incrementTotalUserMessages() {
        totalMessages++;
    }

    public static User getUser(String id) {
        return UserGroups.getUser(id, userRoot);
    }

    private void update(DefaultMutableTreeNode node, DefaultMutableTreeNode newNode) {
        ((TreeView)adminWidgets[0]).reload(node, newNode);
    }

    private void addWidgets() {
        adminWidgets[0] = new TreeView(userRootNode);
        adminWidgets[0].addWidget(admin, 0, 10);
        ((TreeView)adminWidgets[0]).setListener(this);

        adminWidgets[1] = new TextField("User ID");
        adminWidgets[1].addWidget(admin,200,10);

        adminWidgets[2] = new Button("Add User");
        adminWidgets[2].addWidget(admin, 400, 10);
        ((Button)adminWidgets[2]).setListener(this);

        adminWidgets[3] = new TextField("Group ID");
        adminWidgets[3].addWidget(admin,200,70);

        adminWidgets[4] = new Button("Add Group");
        adminWidgets[4].addWidget(admin,400,70);
        ((Button)adminWidgets[4]).setListener(this);

        adminWidgets[5] = new Button("Open User View");
        adminWidgets[5].addWidget(admin,200,130);
        ((Button)adminWidgets[5]).setListener(this);

        adminWidgets[6] = new Button("Show User Total");
        adminWidgets[6].addWidget(admin,200,260);
        ((Button)adminWidgets[6]).setListener(this);

        adminWidgets[7] = new Button("Show Group Total");
        adminWidgets[7].addWidget(admin,400,260);
        ((Button)adminWidgets[7]).setListener(this);

        adminWidgets[8] = new Button("Show Messages Total");
        adminWidgets[8].addWidget(admin,200,320);
        ((Button)adminWidgets[8]).setListener(this);

        adminWidgets[9] = new Button("Show Positive Percentage");
        adminWidgets[9].addWidget(admin,400,320);
        ((Button)adminWidgets[9]).setListener(this);
    }
}
package Panels;//Singleton and Composite
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Widgets.*;
import Users.*;



public class AdminControlPanel implements ActionListener, TreeSelectionListener {
    private static final AdminControlPanel adminPanel = new AdminControlPanel();
    private JFrame admin = new JFrame("Admin Control Panel");
    private Widgets[] adminWidgets = new Widgets[10];
    private UserGroups userRoot;
    private DefaultMutableTreeNode userRootNode;
    DefaultMutableTreeNode selectedNode;

    private AdminControlPanel() {
        admin.setSize(600,400);
        admin.setLayout(null);
        userRoot = new UserGroups("Root");
        userRootNode = new DefaultMutableTreeNode(userRoot, true);
        addWidgets();
        admin.setVisible(true);
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
            addUser(userID, selectedNode);
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[4]).getButton()) { //Add group
            addGroup(groupID, selectedNode);
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[5]).getButton()) { //Open user view

        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[6]).getButton()) { //Show user total
            showUserTotal();
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[7]).getButton()) { //Show group total
            showGroupTotal();
        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[8]).getButton()) { //Show messages total

        }
        else if(actionEvent.getSource() == ((Button)adminWidgets[9]).getButton()) { //Show positive percentage

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

    public void openUserView() {

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

    }

    public void showPositivePercentage() {

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


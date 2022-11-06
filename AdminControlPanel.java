//Singleton and Composite
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import Widgets.*;

public class AdminControlPanel {
    private static final AdminControlPanel adminPanel = new AdminControlPanel();
    private JFrame admin = new JFrame("Admin Control Panel");
    private Widgets[] adminWidgets = new Widgets[10];

    private AdminControlPanel() {
        admin.setSize(600,400);
        admin.setLayout(null);
        addWidgets();
        admin.setVisible(true);
    }

    public static AdminControlPanel getAdminControlPanel() {
        return adminPanel;
    }

    private void addWidgets() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        adminWidgets[0] = new TreeView(root);
        adminWidgets[0].addWidget(admin, 0, 10);

        adminWidgets[1] = new TextField("User ID");
        adminWidgets[1].addWidget(admin,200,10);

        adminWidgets[2] = new Button("Add User");
        adminWidgets[2].addWidget(admin, 400, 10);

        adminWidgets[3] = new TextField("Group ID");
        adminWidgets[3].addWidget(admin,200,70);

        adminWidgets[4] = new Button("Add Group");
        adminWidgets[4].addWidget(admin,400,70);

        adminWidgets[5] = new Button("Open User View");
        adminWidgets[5].addWidget(admin,200,130);

        adminWidgets[6] = new Button("Show User Total");
        adminWidgets[6].addWidget(admin,200,260);

        adminWidgets[7] = new Button("Show Group Total");
        adminWidgets[7].addWidget(admin,400,260);

        adminWidgets[8] = new Button("Show Messages Total");
        adminWidgets[8].addWidget(admin,200,320);

        adminWidgets[9] = new Button("Show Positive Percentage");
        adminWidgets[9].addWidget(admin,400,320);
    }
}

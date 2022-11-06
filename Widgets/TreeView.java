package Widgets;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeView implements Widgets {
    private JPanel treePanel;
    private JTree treeRoot;

    public TreeView(DefaultMutableTreeNode node) {
        treePanel = new JPanel();
        treeRoot = new JTree(node);
    }

    @Override
    public void addWidget(JFrame panel, int x, int y) {
        treePanel.setBounds(x, y, 180, 380);
        treeRoot.setBounds(0, 0,170, 370);
        treePanel.add(treeRoot);
        panel.add(treePanel);
    }
}

package Widgets;

import Panels.AdminControlPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

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
        treeRoot.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treePanel.add(treeRoot);
        panel.add(treePanel);
    }

    public void reload(DefaultMutableTreeNode node, DefaultMutableTreeNode newNode) {
        DefaultTreeModel model = (DefaultTreeModel) treeRoot.getModel();
        model.insertNodeInto(newNode, node, node.getChildCount());
    }

    public JTree getTree() {
        return treeRoot;
    }

    public void setListener(AdminControlPanel admin) {
        treeRoot.addTreeSelectionListener(admin);
    }
}

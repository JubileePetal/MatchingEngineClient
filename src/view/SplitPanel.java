package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import models.Order;

public class SplitPanel extends Observable {

	private DefaultTreeModel model;
	
	private DefaultMutableTreeNode root;
	private final JTree tree;
	private TabPanel tabPanel;
	
	public SplitPanel(TabPanel tabPanel) {
		
		this.tabPanel = tabPanel;
		root  = buildNode(OpStrings.STOCK);
		tree  = new JTree(root);
		
	}

	public JSplitPane buildSplitPanel(){
		
		//tabPanel = new TabPanel();
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				buildExplorerPanel(), tabPanel);
		split.setDividerLocation(150);
		return split;
		
		
	}
	
	private JPanel buildExplorerPanel() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);		
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT);
		panel.setLayout(flow);
		
		
		String instrumentData [] = {"∙ Ericsson B", "∙ Apple"};
		JList  instrumentList = new JList(instrumentData);
		
		

		tree.getSelectionModel().setSelectionMode
        (TreeSelectionModel.SINGLE_TREE_SELECTION);
		model = (DefaultTreeModel) tree.getModel();
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();

		    /* if nothing is selected */ 
		        if (node == null) return;

		    /* retrieve the node that was selected */ 
		        Object nodeInfo = node.getUserObject();
		        System.out.println(nodeInfo.toString());
		        update(nodeInfo.toString());
		    }
		});


		
		
				
		for (int i = 0; i < instrumentData.length; i++) {
			DefaultMutableTreeNode projectNode = buildNode(instrumentData[i]);		
			model.insertNodeInto(projectNode, root, i);
		}
		
		
		
		panel.add(tree);
				
		return panel;
	}
		
	

	
	private DefaultMutableTreeNode buildNode(String name) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);
		return node;
	}
	
	
	
	private void update(String item){
		
		setChanged();
		notifyObservers(item);
		
	}
}

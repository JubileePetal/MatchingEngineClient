package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class TreeList extends JPanel {

	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private final JTree tree;
	
	public TreeList() {
		//JPanel panel = new JPanel();
		
		root  = buildNode(OpStrings.STOCK);
		tree  = new JTree(root);
		
		this.setBackground(Color.WHITE);		
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT);
		this.setLayout(flow);
		
		
		String instrumentData [] = {"∙ Ericsson B", "∙ Apple"};
		JList  instrumentList = new JList(instrumentData);
		
		

		tree.getSelectionModel().setSelectionMode
        (TreeSelectionModel.SINGLE_TREE_SELECTION);
		model = (DefaultTreeModel) tree.getModel();
		



		
		
				
//		for (int i = 0; i < instrumentData.length; i++) {
//			DefaultMutableTreeNode projectNode = buildNode(instrumentData[i]);		
//			model.insertNodeInto(projectNode, root, i);
//		}
		
		
		
		this.add(tree);
				
		//return panel;
	}
	
	
	public void buildTreeNodes(String [] instrumentData){
		
		for (int i = 0; i < instrumentData.length; i++) {
			DefaultMutableTreeNode projectNode = buildNode(instrumentData[i]);		
			model.insertNodeInto(projectNode, root, i);
		}
		
	}
	
	
	private DefaultMutableTreeNode buildNode(String name) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);
		return node;
	}
	
	public JTree getTree(){
		
		return tree;
	}
}

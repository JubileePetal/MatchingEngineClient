package control;

import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import models.DataHolder;
import models.OpCodes;
import view.OpStrings;
import view.TreeList;
import view.View;

public class Updater {

	private ArrayList<View> views;
	//private JTree tree;
	private TreeList treeList;
	private DataHolder dataHolder;
	
	public Updater() {
		// TODO Auto-generated constructor stub
	}

	public void setViews(ArrayList<View> views) {
		this.views = views;
	}

	public void setTreeList(TreeList treeList) {
		this.treeList = treeList;
		addTreeListener();
		
		
	}
	
	public void buildTree(){
		
		String instrumentData [] = {"∙ Ericsson B", "∙ Apple"};
		treeList.buildTreeNodes(instrumentData);
		
	}
	
	private void addTreeListener(){
		treeList.getTree().addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		        DefaultMutableTreeNode node = 
		        	(DefaultMutableTreeNode)treeList.getTree().
		        		getLastSelectedPathComponent();

		    /* if nothing is selected */ 
		        if (node == null) return;

		    /* retrieve the node that was selected */ 
		        Object nodeInfo = node.getUserObject();
		        System.out.println(nodeInfo.toString() + " in updater!");
		        updateView();
		    }
		});

	}

	
	
	
	public void setDataHolder(DataHolder dataHolder) {
		this.dataHolder = dataHolder;
	}

	private void updateView(){
		
		
		for(View v: views){
			
			System.out.println(v.getMyName() + " is the name I got.");
			if(v.getMyName() == OpStrings.ORDERS){
				
				System.out.println("ANNAAAAAA");
				Object[] data = {"Anna" ,"är", "söt."};
				v.setTableData(data);
				
			}
		}
		
	}

}

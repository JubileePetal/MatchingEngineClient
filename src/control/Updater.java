package control;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import models.DataHolder;
import models.OpCodes;
import view.OpStrings;
import view.TreeList;
import view.View;

public class Updater implements Observer{

	private ArrayList<View> views;
	//private JTree tree;
	private TreeList treeList;
	private DataHolder dataHolder;
	private JFrame lordFrame; 
	
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
	
	public void setLordFrame(JFrame lordFrame) {
		this.lordFrame = lordFrame;
	}

	public void buildTree(){
		
		String instrumentNames [] = {"∙ Ericsson B", "∙ Apple"};
		
		//String[] test = dataHolder.getInstrumentNames();
		
//		for(String s : test){
//			
//			System.out.println("Value: " + s);
//			
//		}
		ArrayList<String> instrumentData = dataHolder.getInstrumentNames();
		String[] instruments = new String[instrumentData.size()];
		

		
		for (int i = 0; i < instrumentData.size(); i++) {
			System.out.println("name: " + instrumentData.get(i));
			instruments[i] = instrumentData.get(i);
		}
		
		
		
		treeList.buildTreeNodes(instruments);
		
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

	
	
	@Override
	public void update(Observable observed, Object objectChanged) {
		
		DataHolder dataHolder = (DataHolder)observed;
		
		if(lordFrame.isVisible()) {
			// lotsa derp
		} else {
			
			
			buildTree();	
			lordFrame.setVisible(true);
		}
		
		
	}

}

package control;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import models.DataHolder;
import models.InstrumentState;
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
	private String currentInstrument;
	
	public Updater() {
		
		currentInstrument = OpStrings.STOCK;
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
			//System.out.println("name: " + instrumentData.get(i));
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
		        //System.out.println(nodeInfo.toString() + " in updater!");
		        updateView(nodeInfo.toString());
		    }
		});

	}
	
	public void setDataHolder(DataHolder dataHolder) {
		this.dataHolder = dataHolder;
	}

	private void updateView(String instrumentName){
		
		setCurrentInstrument(instrumentName);
		
		for(View v: views){
			
			
			v.setTitledBorder(instrumentName);
			
			/*Stock is just the root of the tree, not relevant*/
			if(!instrumentName.equals(OpStrings.STOCK)){
				
				
				InstrumentState is = dataHolder.getInstrumentState(instrumentName);
				
				if(v.getMyName().equals(OpStrings.ORDERS)){			
					//System.out.println("Instrument name:" + instrumentName);

		
					v.setTableData(is.getOrders());
				}
				
				if(v.getMyName().equals(OpStrings.TRADES) ){
					
					v.setTableData(is.getTrades());
					
				}
				
				if(v.getMyName().equals(OpStrings.MD) ){
					
					v.setTableData(is.getMarketData());
					
				}
			

						
					
				

				
			}
		}
		
	}
	
	public void setCurrentInstrument(String currentInstrument) {
		this.currentInstrument = currentInstrument;
	}
	
	

	@Override
	public void update(Observable observed, Object objectChanged) {
		
		//System.out.println("------------ Trying to update --------");
	
		
		DataHolder dataHolder = (DataHolder)observed;
		
		if(lordFrame.isVisible()) {
			
			
			InstrumentState is = (InstrumentState)objectChanged;
			
			
			
			if(currentInstrument != null && 
					!currentInstrument.equals(OpStrings.STOCK) && 
					is.getInstrumentName().equals(currentInstrument)){
				
				
				
				for(View v : views){
					
					if(v.getMyName().equals(OpStrings.ORDERS)){			
			
						v.setTableData(is.getOrders());
					}
					
					if(v.getMyName().equals(OpStrings.TRADES) ){
						
						v.setTableData(is.getTrades());
						
					}
					
					if(v.getMyName().equals(OpStrings.MD) ){
						
						v.setTableData(is.getMarketData());
						
					}
					
				}
				
				
			}
			
			
			
		} else {
			
			
			buildTree();
			/*
			TreePath path = treeList.getTree().getPathForRow(2);
			treeList.getTree().setExpandsSelectedPaths(true);
			treeList.getTree().setSelectionPath(path);
			treeList.getTree().scrollPathToVisible(path);
			*/
			lordFrame.setVisible(true);
		}
		
		
	}

}

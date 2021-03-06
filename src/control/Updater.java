package control;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import models.DataHolder;
import models.InstrumentState;
import view.OpStrings;
import view.TreeList;
import view.View;

public class Updater implements Observer{

	private ArrayList<View> views;
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
		
		ArrayList<String> instrumentData = dataHolder.getInstrumentNames();
		String[] instruments = new String[instrumentData.size()];

		for (int i = 0; i < instrumentData.size(); i++) {
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
			lordFrame.setVisible(true);
		}
		
		
	}

	public String getCurrentInstrument() {

		return currentInstrument;
	}

}

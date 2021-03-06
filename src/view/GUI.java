package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import control.Controller;
import models.DataHolder;
import models.OpCodes;

public class GUI implements Observer {
	
	private JFrame lordFrame;
	
	private ArrayList<View> views;
	//private JTree tree;
	private TreeList treeList;
	
	private Controller controller;
	
	public GUI() {
	
		lordFrame = new JFrame();
		lordFrame.setTitle("Matching Engine Client");
		lordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lordFrame.setMinimumSize(new Dimension(800, 600));
		lordFrame.setLocationRelativeTo(null);
		//lordFrame.setBackground(new Color(100, 100, 100));
		lordFrame.setResizable(true);
		
		buildGUI();


	}
	
	private void buildGUI(){
		
		views = new ArrayList<View>();							
		View v1 = new View(OpStrings.TRADES);
		View v2 = new View(OpStrings.MD);
		View v3 = new View(OpStrings.ORDERS);
		
		views.add(v1);
		views.add(v2);
		views.add(v3);
		
		TabPanel tabPanel = new TabPanel();
		tabPanel.addTab(OpStrings.TRADES, v1);
		tabPanel.addTab(OpStrings.MD, v2);
		tabPanel.addTab(OpStrings.ORDERS, v3);

		
		treeList = new TreeList();
		//tree = treeList.getTree();
		
		SplitPanel splitPanel = new SplitPanel(tabPanel,treeList);
		lordFrame.add(splitPanel.buildSplitPanel());
		
		
	}
	

	
	public TreeList getTreeList() {
		return treeList;
	}




	public void addController(Controller controller) {
		this.controller = controller;
	}

	public void startGUI() {
		lordFrame.setVisible(true);
	}

	public ArrayList<View> getViews() {
		return views;
	}


	public JFrame getLordFrame() {
		return lordFrame;
	}

	//TODO GET RID OF THIS SHIZ
	@Override
	public void update(Observable observed, Object objectChanged) {
		
		DataHolder dataHolder = (DataHolder)observed;
		
		if(lordFrame.isVisible()) {
			// lotsa derp
		} else {
			
			//buildGUI();
			
			lordFrame.setVisible(true);
		}		
	}

}

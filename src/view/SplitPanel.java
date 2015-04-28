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

public class SplitPanel   {

	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	//private final JTree tree;
	private TabPanel tabPanel;
	private TreeList tlist;
	
	public SplitPanel(TabPanel tabPanel, TreeList tlist) {
		
		this.tabPanel 	= tabPanel;
		this.tlist 		= tlist;

		
	}

	public JSplitPane buildSplitPanel(){
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				tlist, tabPanel);
		split.setDividerLocation(150);
		return split;
		
		
	}

	
	
	

}

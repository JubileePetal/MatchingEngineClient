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
		
		views = new ArrayList<View>();							
		View v1 = new View(OpStrings.HISTORY);
		View v2 = new View(OpStrings.MD);
		View v3 = new View(OpStrings.ORDERS);
		
		views.add(v1);
		views.add(v2);
		views.add(v3);
		
		TabPanel tabPanel = new TabPanel();
		tabPanel.addTab(OpStrings.HISTORY, v1);
		tabPanel.addTab(OpStrings.MD, v2);
		tabPanel.addTab(OpStrings.ORDERS, v3);

		
		treeList = new TreeList();
		//tree = treeList.getTree();
		
		SplitPanel splitPanel = new SplitPanel(tabPanel,treeList);
		lordFrame.add(splitPanel.buildSplitPanel());
		
		lordFrame.setVisible(true);


	}
	
	

	
	public TreeList getTreeList() {
		return treeList;
	}




	public void addController(Controller controller) {
		this.controller = controller;
	}
	
	public void logInPrompt() {

		String nick = "Default";
		int userType = 0;
		
		LogInPanel logInPanel = new LogInPanel();

		if(logInPanel.logInAttemptConfirmed()) {
			
			nick = logInPanel.getName();
			userType = logInPanel.getUserType();
			
		} else {
			System.exit(0);
		}
		System.out.println("Nick: " + nick);
		controller.attemptLogIn(nick, userType);
		controller.sendOrder();
	
	}

	public void startGUI() {
		lordFrame.setVisible(true);
	}

	public ArrayList<View> getViews() {
		return views;
	}






	@Override
	public void update(Observable observed, Object objectChanged) {
		
		DataHolder dataHolder = (DataHolder)observed;
		
		if(lordFrame.isVisible()) {
			// lotsa derp
		} else {
			lordFrame.setVisible(true);
		}
		//System.out.println ("View      : Observable is " + observed.getClass() + ", object passed is " + objectChanged.getClass());
		
	}

}

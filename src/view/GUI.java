package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import control.Controller;
import models.DataHolder;
import models.OpCodes;

public class GUI implements Observer {
	
	JFrame lordFrame;
	
	private Controller controller;
	
	public GUI() {
	
		lordFrame = new JFrame();
		lordFrame.setTitle("Matching Engine Client");
		lordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lordFrame.setMinimumSize(new Dimension(800, 600));
		lordFrame.setLocationRelativeTo(null);
		//lordFrame.setBackground(new Color(100, 100, 100));
		lordFrame.setResizable(true);
		
		
		/****Should probably have dynamic tabs but for now, three**/
		
		ViewBuilder v1 = new ViewBuilder(OpStrings.ORDERS);
		ViewBuilder v2 = new ViewBuilder(OpStrings.HISTORY);
		ViewBuilder v3 = new ViewBuilder(OpStrings.MD);
		
		/**														**/
		
		
		TabPanel tabPanel = new TabPanel();
		tabPanel.buildViews(v1,v2,v3);
		SplitPanel splitPanel = new SplitPanel(tabPanel);
		
		splitPanel.addObserver(v1);
		splitPanel.addObserver(v2);
		splitPanel.addObserver(v3);
		
	
		lordFrame.add(splitPanel.buildSplitPanel());
		
		
		/***TEEEEEEEEEEEEEEEST**/
		
		
	
		/***********************/
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

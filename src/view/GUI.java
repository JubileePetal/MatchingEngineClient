package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		lordFrame.setSize(700, 700);
		lordFrame.setResizable(true);

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
		if(dataHolder.isLoggedIn()) {
			System.out.println("here: " + observed.getClass());
		}
		
	}

}

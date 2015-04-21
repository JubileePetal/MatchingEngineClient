package control;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.OpCodes;

public class GUI {
	
	JFrame lordFrame;
	
	private Controller controller;
	
	public GUI(Controller controller) {
	
		this.controller = controller;
	
		logInRequest();
		
		lordFrame = new JFrame();
		lordFrame.setTitle("Matching Engine Client");
		lordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lordFrame.setSize(700, 700);
		lordFrame.setResizable(true);

	}
	
	private void logInRequest() {
		JPanel userPanel = new JPanel();
		
		JTextField nickField = new JTextField(14);
		userPanel.add(new JLabel("Nickname:"));
		userPanel.add(nickField);
		
		String[] userTypes = {"Trader", "Admin", "ISVR", "Regulator"};
		JComboBox userTypesCombo = new JComboBox(userTypes);
		userTypesCombo.setSelectedIndex(0);
		userPanel.add(new JLabel("User type:"));
		userPanel.add(userTypesCombo);		

		/* THIS IS NOT SAFE!! */
		
		int result = JOptionPane.showConfirmDialog(null,  userPanel,
				"Please enter nickname and usertype", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			int userType = 0;
			switch (userTypesCombo.getSelectedIndex()) {
				case 0: userType = OpCodes.TRADER; break;
				case 1: userType = OpCodes.ADMIN; break;
				case 2: userType = OpCodes.ISVR; break;
				case 3: userType = OpCodes.REGULATOR; break;
			}
			controller.logIn(nickField.getText(), userType);
		} else {
			System.exit(0);
		}
	}
	
	public void startGUI() {
		lordFrame.setVisible(true);
	}

}

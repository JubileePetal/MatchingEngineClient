package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ButtonHolder {
	/*BUTTONS*/
	public static final JButton NEW_ORDER_BUTTON = new JButton("New Order");

	
	/*OTHER*/
	Prompter prompter;
	
	
	public ButtonHolder(){
		
		prompter = new Prompter();
	}
	
	
	public void setButtonListeners(){
		
		NEW_ORDER_BUTTON.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				System.out.println("NEW ORDER BUTTON PRESSED!");
				int result = JOptionPane.showConfirmDialog(null,  prompter.newOrderPrompt(),
						"Enter new order info", JOptionPane.OK_CANCEL_OPTION);
					//return result == JOptionPane.OK_OPTION ? true : false;
				
				
			}
		});
		
	}
}

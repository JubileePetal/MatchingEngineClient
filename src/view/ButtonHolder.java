package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonHolder {

	public static final JButton NEW_ORDER_BUTTON = new JButton("New Order");

	
	
	public void setButtonListeners(){
		
		NEW_ORDER_BUTTON.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				System.out.println("NEW ORDER BUTTON PRESSED!");
			}
		});
		
	}
}

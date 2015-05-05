package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import control.Controller;
import control.Updater;

import models.DataHolder;
import models.Order;

public class ButtonHolder {
	/*BUTTONS*/
	public static final JButton NEW_ORDER_BUTTON = new JButton("New Order");

	private Controller controller;
	private DataHolder dataHolder;
	private Updater updater;
	
	/*OTHER*/
	Prompter prompter;
	
	
	public ButtonHolder(){
		
		prompter = new Prompter();
	}
	
	
	public void setButtonListeners(){
		
		NEW_ORDER_BUTTON.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null,  prompter.newOrderPrompt(),
						"Enter new order info", JOptionPane.OK_CANCEL_OPTION);
				
				if(result == JOptionPane.OK_OPTION) {
					String currentInstrument = updater.getCurrentInstrument();
					Order order = prompter.getNewOrderInfo();	
					order.setOrderOwner(dataHolder.getNickName());
					order.setInstrument(dataHolder.getInstrument(currentInstrument));
					controller.sendOrder(order);
				}
				
			}
		});
		
	}


	public void addDataHolder(DataHolder dataHolder) {
		this.dataHolder = dataHolder;
		
	}


	public void addController(Controller controller) {
		this.controller = controller;
		
	}


	public void addUpdater(Updater updater) {
		this.updater = updater;
	}
}

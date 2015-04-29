package control;

import com.google.gson.Gson;

import models.DataHolder;
import models.Instrument;
import models.Message;
import models.OpCodes;
import models.Order;
import models.User;
import communication.Sender;


public class Controller {

	private Sender sender;
	private DataHolder dataHolder;
	
	public Controller() {
	
	}

	public void sendOrder() {
		/* TEST */
		Instrument instrument = new Instrument();
		instrument.setAbbreviation("ERB");
		instrument.setName("Ericsson B");
		instrument.setType(OpCodes.SHARE);
		
		Order order = new Order();
		order.setToBuyOrder();
		order.setTypeOfOrder(OpCodes.LIMIT_ORDER);
		order.setInstrument(instrument);
		order.setOrderOwner("Linda");
		order.setOrderQuantity(40);
		order.setPrice(30.0);
		
		Order order2 = new Order();
		order2.setToSellOrder();
		order2.setTypeOfOrder(OpCodes.LIMIT_ORDER);
		order2.setInstrument(instrument);
		order2.setOrderOwner("Linda");
		order2.setOrderQuantity(40);
		order2.setPrice(30.0);
		
		Order order3 = new Order();
		order3.setToSellOrder();
		order3.setTypeOfOrder(OpCodes.LIMIT_ORDER);
		order3.setInstrument(instrument);
		order3.setOrderOwner("Linda");
		order3.setOrderQuantity(300);
		order3.setPrice(40.0);
		
		Order order4 = new Order();
		order4.setToBuyOrder();
		order4.setTypeOfOrder(OpCodes.LIMIT_ORDER);
		order4.setInstrument(instrument);
		order4.setOrderOwner("Linda");
		order4.setOrderQuantity(20);
		order4.setPrice(20.0);
		
		Order order5 = new Order();
		order5.setToBuyOrder();
		order5.setTypeOfOrder(OpCodes.LIMIT_ORDER);
		order5.setInstrument(instrument);
		order5.setOrderOwner("Linda");
		order5.setOrderQuantity(10);
		order5.setPrice(25.0);
		
		Order order6 = new Order();
		order6.setToBuyOrder();
		order6.setTypeOfOrder(OpCodes.LIMIT_ORDER);
		order6.setInstrument(instrument);
		order6.setOrderOwner("Linda");
		order6.setOrderQuantity(20);
		order6.setPrice(25.0);
		
		sender.sendOrder(order);
		sender.sendOrder(order2);
		sender.sendOrder(order3);
		sender.sendOrder(order4);
		sender.sendOrder(order5);
		sender.sendOrder(order6);
		
	}

	public void sendOrder(Order order) {
		sender.sendOrder(order);
	}
	
	public void addDataHolder(DataHolder dataHolder) {
		this.dataHolder = dataHolder;
	}
	
	public void addSender(Sender sender) {
		this.sender = sender;
	}
	
	public void attemptLogIn(String nickname, int userType) {
		
		boolean logInSuccessful = sender.logIn(nickname, userType);
		
		if(!logInSuccessful) {
			/* another log in prompt?!*/
		}
	}


}

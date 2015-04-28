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
		
		sender.sendOrder(order);
		sender.sendOrder(order2);
		
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

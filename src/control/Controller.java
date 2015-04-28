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
		order.setTypeOfOrder(OpCodes.MARKET_ORDER);
		order.setInstrument(instrument);
		order.setOrderOwner("Linda");
		order.setOrderQuantity(40);
		order.setPrice(30.0);
		
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

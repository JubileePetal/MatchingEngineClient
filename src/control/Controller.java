package control;

import com.google.gson.Gson;

import models.DataHolder;
import models.Instrument;
import models.LimitOrder;
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
		LimitOrder order = new LimitOrder();
		/* test*/ 
		order.setOrderOwner("Li\nnda\n");
		order.setPrice(20.0);
		Instrument instrument = new Instrument();
		instrument.setAbbreviation("ERB");
		order.setInstrument(instrument);
		order.setOrderType(0, 2);
		order.setOrderQuantity(20);
		order.setId(20);
		
		sender.sendOrder(order);
	}
	
	public void nowLoggedIn() {
		dataHolder.loggedIn();
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

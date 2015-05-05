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

	public void addDataHolder(DataHolder dataHolder) {
		this.dataHolder = dataHolder;
	}
	
	public void addSender(Sender sender) {
		this.sender = sender;
	}
	
	public void sendOrder(Order order) {
		sender.sendOrder(order);
	}
	
	public boolean attemptLogIn(String nickname, int userType) {
		
		return sender.logIn(nickname, userType);
	}


}

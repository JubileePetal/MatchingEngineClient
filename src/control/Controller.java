package control;

import com.google.gson.Gson;

import models.DataHolder;
import models.Message;
import models.OpCodes;
import models.Order;
import models.User;
import communication.Sender;


public class Controller {

	private Sender sender;
	private DataHolder dataHolder;
	
	public Controller() {
/*		
		Order order = new Order(System.currentTimeMillis());
		order.setOrderOwner("Li\nnda\n");
		order.setOrderType(0, 1, 2);
		order.setOrderQuantity(20);
		sender.sendOrder(order);
*/		
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

package control;

import com.google.gson.Gson;
import models.Message;
import models.OpCodes;
import models.Order;
import models.User;
import communication.Sender;


public class Controller {

	private Sender sender;
	
	public Controller(Sender sender) {
		
		this.sender = sender;
	
		//logIn("Linda", OpCodes.TRADER);
		

/*		
		Order order = new Order(System.currentTimeMillis());
		order.setOrderOwner("Li\nnda\n");
		order.setOrderType(0, 1, 2);
		order.setOrderQuantity(20);
		sender.sendOrder(order);
*/		
	}
	
	public void logIn(String nickname, int userType) {
		
		boolean logInSuccessful = sender.logIn(nickname, userType);
		
	}
}

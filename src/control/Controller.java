package control;

import com.google.gson.Gson;
import models.Message;
import models.OpCodes;
import models.Order;
import models.User;
import communication.Sender;


public class Controller {

	private GUI userInterface;
	private Sender sender;
	
	public Controller(GUI userInterface, Sender sender) {
		
		this.userInterface = userInterface;
		this.sender = sender;
	
		logIn("Linda", OpCodes.TRADER);
		
		while (true) {
			
		}
/*		
		Order order = new Order(System.currentTimeMillis());
		order.setOrderOwner("Li\nnda\n");
		order.setOrderType(0, 1, 2);
		order.setOrderQuantity(20);
		sender.sendOrder(order);
*/		
	}
	
	private void logIn(String nickname, int userType) {
		
		boolean logInSuccessful = sender.logIn(nickname, userType);
		
	}
}

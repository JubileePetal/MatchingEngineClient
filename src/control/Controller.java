package control;
import models.Order;
import communication.Sender;


public class Controller {

	private GUI userInterface;
	private Sender sender;
	
	public Controller(GUI userInterface, Sender sender) {
		
		this.userInterface = userInterface;
		this.sender = sender;
	
		Order order = new Order(System.currentTimeMillis());
		order.setOrderOwner("Li\nnda\n");
		order.setOrderType(0, 1, 2);
		order.setOrderQuantity(20);
		sender.sendOrder(order);
		
		System.out.println("sent order, connected.");
	}
}

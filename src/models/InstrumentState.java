package models;

import java.util.ArrayList;
import java.util.HashMap;

public class InstrumentState {

	//ArrayList<Order> orders;
	HashMap<Long, Order> orders;
	ArrayList<Trade> trades;
	
	public InstrumentState() {
		//orders = new ArrayList<Order>();
		orders = new HashMap<Long,Order>();
		trades = new ArrayList<Trade>();
	}

	public void addOrder(Order order) {
		long id = order.getId();
		synchronized(orders) {
			orders.put(id, order);
		}
		
		System.out.println("Put order id: " + id + " in list.");
		
	}
	
}

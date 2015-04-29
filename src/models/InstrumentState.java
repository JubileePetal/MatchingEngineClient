package models;

import java.util.ArrayList;
import java.util.HashMap;

public class InstrumentState {

	//ArrayList<Order> orders;
	HashMap<Long, Order> orders;
	ArrayList<Trade> trades;
	private String myNickname;
	private String myInstrument;
	
	public InstrumentState(String nickname, String instrumentName) {
		//orders = new ArrayList<Order>();
		myInstrument = instrumentName;
		myNickname = nickname;
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

	public void addTrade(Trade trade) {
		synchronized(trades) {
			trades.add(trade);
		}
		
		int tradedQuantity = trade.getQuantity();		

		if(trade.getBuyer() == myNickname && trade.getSeller() == myNickname) {
			long buyID = trade.getBuyOrderID();
			updateOrder(buyID, tradedQuantity);
			System.out.println("buy id: " + buyID);
			long sellID = trade.getSelOrderID();
			updateOrder(sellID, tradedQuantity);
			System.out.println("sell id: " + sellID);
			
		} else if(trade.getBuyer() == myNickname) {
			long buyID = trade.getBuyOrderID();
			updateOrder(buyID, tradedQuantity);

		} else {
			long sellID = trade.getSelOrderID();
			updateOrder(sellID, tradedQuantity);
		}
		
		System.out.println("trade made! buyer: " + trade.getBuyer() + " seller: " + trade.getSeller() + " quantity: " + trade.getQuantity() + " price: " + trade.getPrice());
		
	}

	
	private void updateOrder(long id, int tradedQuantity) {
		System.out.println("Here id: " + id);
		Order order = orders.get(id);
		
		if(order != null) {
			
			int oldQuantity = order.getQuantity();
			
			if(oldQuantity == tradedQuantity) {
				orders.remove(id);
				System.out.println("removed order " + id);
			} else {
				order.setOrderQuantity(oldQuantity-tradedQuantity); 
				System.out.println("new quantity for order " + id + ": " + (oldQuantity - tradedQuantity));

			}
			
		}
	}
	
	public String getInstrumentName() {
		return myInstrument;
	}
}

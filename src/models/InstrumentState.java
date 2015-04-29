package models;

import java.util.ArrayList;
import java.util.HashMap;

public class InstrumentState {

	//ArrayList<Trade> trades;
	//ArrayList<Order> orders;
	HashMap<Long, Order> orders;
	HashMap<Long, Trade> trades;

	private String myNickname;
	private String myInstrument;
	
	public InstrumentState(String nickname, String instrumentName) {
		//orders = new ArrayList<Order>();
		myInstrument = instrumentName;
		myNickname = nickname;
		orders = new HashMap<Long,Order>();
		trades = new HashMap<Long,Trade>();
		//trades = new ArrayList<Trade>();
	}

	public void addOrder(Order order) {
		long id = order.getId();
		synchronized(orders) {
			orders.put(id, order);
		}
		
		System.out.println("Put order id: " + id + " in list.");
	}

	public void addTrade(Trade trade) {
		
		int tradedQuantity = trade.getQuantity();		
		
		if(trade.getBuyer().equals(myNickname) && trade.getSeller().equals(myNickname)) {
			if(trades.get(trade.getTradeID()) == null) {
				long buyID = trade.getBuyOrderID();
				updateOrder(buyID, tradedQuantity);
				long sellID = trade.getSelOrderID();
				updateOrder(sellID, tradedQuantity);
			}			
		} else if(trade.getBuyer().equals(myNickname)) {
			long buyID = trade.getBuyOrderID();
			updateOrder(buyID, tradedQuantity);

		} else {
			long sellID = trade.getSelOrderID();
			updateOrder(sellID, tradedQuantity);
		}
		synchronized(trades) {
			trades.put(trade.getTradeID(), trade);
		}		
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
	
	public ArrayList<Object[]> getOrders() {
		
		ArrayList<Object[]> orderInfoCollection = new ArrayList<Object[]>();
		
		for(Order order : orders.values()) {
			Object[] orderInfo = new Object[4];
			orderInfo[0] = order.getId();
			orderInfo[1] = order.isBuyOrSell();
			orderInfo[2] = order.getPrice();
			orderInfo[3] = order.getQuantity();
			orderInfoCollection.add(orderInfo);
		}
		/*
		for(Object[] o : orderInfoCollection) {
			System.out.println("Order" + o[0] + " " + (((Integer)o[1] == OpCodes.BUY_ORDER) ? "buy" : "sell") + " price: " + o[2] + " quantity: " + o[3]);
		}
		*/
		return orderInfoCollection;
	}
	
	public String getInstrumentName() {
		return myInstrument;
	}
}

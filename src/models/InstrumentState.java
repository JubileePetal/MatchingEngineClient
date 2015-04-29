package models;

import java.util.ArrayList;
import java.util.HashMap;
import models.BookStatus;

public class InstrumentState {

	BookStatus marketData;
	HashMap<Long, Order> orders;
	HashMap<Long, Trade> trades;

	private String myNickname;
	private String myInstrument;
	
	public InstrumentState(String nickname, String instrumentName) {
		
		myInstrument = instrumentName;
		myNickname = nickname;
		orders = new HashMap<Long,Order>();
		trades = new HashMap<Long,Trade>();

	}

	public void addOrder(Order order) {
		long id = order.getId();
		synchronized(orders) {
			orders.put(id, order);
		}
		
		System.out.println("Put order id: " + id + " in list.");
		getOrders();
	}

	public void addTrade(Trade trade) {
		//TODO HAVE TO CHANGE ORDERS SO BUYER AND SELLER RECEIVE DIFFERENT
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
		getTrades();
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
			orderInfo[1] = (order.isBuyOrSell() == OpCodes.BUY_ORDER) ? "Buy" : "Sell";
			orderInfo[2] = order.getPrice();
			orderInfo[3] = order.getQuantity();
			orderInfoCollection.add(orderInfo);
		}
		
		for(Object[] o : orderInfoCollection) {
			System.out.println("Order" + o[0] + " " + o[1] + " price: " + o[2] + " quantity: " + o[3]);
		}
		
		return orderInfoCollection;
	}
	
	public ArrayList<Object[]> getTrades() {
		
		ArrayList<Object[]> tradeInfoCollection = new ArrayList<Object[]>();
		
		for(Trade trade : trades.values()) {
			Object[] tradeInfo = new Object[4];
			tradeInfo[0] = trade.getTradeID();
			tradeInfo[1] = (trade.getBuyer().equals(myNickname) ? "Buy" : "Sell");
			tradeInfo[2] = trade.getPrice();
			tradeInfo[3] = trade.getQuantity();
			tradeInfoCollection.add(tradeInfo);
		}
		
		for(Object[] o : tradeInfoCollection) {
			System.out.println("Trade" + o[0] + " " + o[1] + " price: " + o[2] + " quantity: " + o[3]);
		}
		
		return tradeInfoCollection;
	}
	
	public String getInstrumentName() {
		return myInstrument;
	}

	
	public void newMD(BookStatus bookStatus) {
		marketData = bookStatus;
		
	}

	public ArrayList<Object[]> getMarketData() {
		
		ArrayList<Object[]> MD = new ArrayList<Object[]>();
		
		if(marketData != null) {
			for(Level level : marketData.getBuyLevels()) {
				Object[] levelInfo = new Object[4];
				levelInfo[0] = level.getQuantity();
				levelInfo[1] = level.getPrice();
				levelInfo[2] = "";
				levelInfo[3] = "";
				MD.add(levelInfo);
			}
			
			ArrayList<Level> sellLevels = marketData.getSellLevels();
			
			for(int i = 0; i < marketData.getSellLevels().size(); i++) {
				if(MD.size() < i+1) {
					Object[] levelInfo = new Object[4];
					levelInfo[0] = "";
					levelInfo[1] = "";
					levelInfo[2] = sellLevels.get(i).getPrice();
					levelInfo[3] = sellLevels.get(i).getQuantity();
				} else {
					MD.get(i)[2] = sellLevels.get(i).getPrice();
					MD.get(i)[3] = sellLevels.get(i).getQuantity();
				}
			}

			
			for(Object[] o : MD) {
				System.out.println("Buy price" + o[0] + " buy quant " + o[1] + " sell quant " + o[2] + " sell price " + o[3]);
			}
		}
		

		
		return MD;
	}
}

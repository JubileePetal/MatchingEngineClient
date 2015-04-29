package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class DataHolder extends Observable {
	
	private HashMap<String, InstrumentState> instrumentStates;
	String myNickname;
	
	public DataHolder() {
		instrumentStates = new HashMap<String, InstrumentState>();
	}
	
	private void update(InstrumentState instrumentState) {
		setChanged();
		notifyObservers(instrumentState);
	}
	
	public void loggedIn() {
		update(null);
	}

	public void setInstruments(Instrument[] instruments) {
		
		for(Instrument instrument : instruments) {
			String name = instrument.getName();
			instrumentStates.put(name, new InstrumentState(myNickname, name));
		}
		
	}
	
	public ArrayList<String> getInstrumentNames() {

		ArrayList<String> names = new ArrayList<String>();
		
		for(String name : instrumentStates.keySet()) {
			names.add(name);
		}

		return names;
	}

	public void addOrder(Order order) {
		
		String instrumentName = order.getInstrument().getName();
		InstrumentState instrumentState = 
				instrumentStates.get(instrumentName);
		instrumentState.addOrder(order);
		update(instrumentState);
		
	}

	public void addTrade(Trade trade) {
		
		String instrumentName = trade.getInstrument().getName();
		InstrumentState instrumentState = instrumentStates.get(instrumentName);
		instrumentState.addTrade(trade);
		update(instrumentState);
	}

	public String getNickName() {
		return myNickname;
	}
	
	public void setNickName(String nick) {
		this.myNickname = nick;
		
	}
}

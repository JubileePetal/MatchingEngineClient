package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class DataHolder extends Observable {
	
	private HashMap<String, InstrumentState> instrumentStates;
	String myUserName;
	
	public DataHolder() {
		instrumentStates = new HashMap<String, InstrumentState>();
	}
	
	private void update() {
		setChanged();
		notifyObservers(this);
	}
	
	public void loggedIn() {
		update();
	}

	public void setInstruments(Instrument[] instruments) {
		
		for(Instrument instrument : instruments) {
			String name = instrument.getName();
			instrumentStates.put(name, new InstrumentState());
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

		
	}

	public void addTrade(Trade trade) {
		
		
	}

	public void setNickName(String nick) {
		this.myUserName = nick;
		
	}
}

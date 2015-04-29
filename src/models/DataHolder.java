package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import models.BookStatus;

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
			System.out.println(name);
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
	
	public InstrumentState getInstrumentState(String instrumentName) {
		
		System.out.println("got instrumentName: " + instrumentName);
		for(String name : instrumentStates.keySet()) {
			System.out.println("I have: " + name);
		}
		
		return instrumentStates.get(instrumentName);
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

	public void newMD(BookStatus bookStatus) {
		String instrumentName = bookStatus.getInstrumentName();
		InstrumentState instrumentState = instrumentStates.get(instrumentName);
		if(instrumentState != null) {
			instrumentState.newMD(bookStatus);
			update(instrumentState);
		}
	}
}

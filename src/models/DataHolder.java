package models;

import java.util.HashMap;
import java.util.Observable;

public class DataHolder extends Observable {
	
	private HashMap<String, InstrumentState> instrumentStates;
	
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
	
	public String[] getInstrumentNames() {
		return (String[]) instrumentStates.keySet().toArray();
	}

	public void addOrder(Order order) {
		
		String instrumentName = order.getInstrument().getName();
		InstrumentState instrumentState = 
				instrumentStates.get(instrumentName);
		instrumentState.addOrder(order);

		
	}
}

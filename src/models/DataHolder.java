package models;

import java.util.HashMap;
import java.util.Observable;

public class DataHolder extends Observable {
	
	private HashMap<String, CurrentStateOfInstrument> instrumentStates;
	
	public DataHolder() {
		instrumentStates = new HashMap<String, CurrentStateOfInstrument>();
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
			instrumentStates.put(name, new CurrentStateOfInstrument());
		}
		
	}
}

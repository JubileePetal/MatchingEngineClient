package models;

import java.util.Observable;

public class DataHolder extends Observable {

	boolean loggedIn;
	
	public DataHolder() {
		loggedIn = false;
	}
	
	private void update() {
		setChanged();
		notifyObservers(this);
	}
	
	public void loggedIn() {
		loggedIn = true;
		update();
	}
	
	public void loggedOut() {
		loggedIn = false;
		update();
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
}

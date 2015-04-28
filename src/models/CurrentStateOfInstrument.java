package models;

import java.util.ArrayList;

public class CurrentStateOfInstrument {

	ArrayList<Order> orders;
	ArrayList<Trade> trades;
	
	public CurrentStateOfInstrument() {
		orders = new ArrayList<Order>();
		trades = new ArrayList<Trade>();
	}
	
}

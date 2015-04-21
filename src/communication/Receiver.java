package communication;

import java.io.BufferedReader;

import control.Controller;

public class Receiver {

	private Controller controller; 
	BufferedReader inFromServer;
	
	public Receiver(Controller controller, BufferedReader reader) {
		this.controller = controller;
		this.inFromServer = reader;
	}
}

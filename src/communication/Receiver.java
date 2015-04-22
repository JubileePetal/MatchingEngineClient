package communication;

import java.io.BufferedReader;
import java.io.IOException;

import models.Message;
import models.OpCodes;

import com.google.gson.Gson;

import control.Controller;

public class Receiver implements Runnable {

	private Controller controller; 
	private BufferedReader inFromServer;
	private Gson gson;
	
	public Receiver() {
		gson = new Gson();
	}
	
	public void addBufferedReader(BufferedReader reader) {
		inFromServer = reader;
	}
	
	public void addController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		
		while(true) {
			String received = readFromServer();
			Message message = unpackMessage(received);
			routeToDestination(message);
		}
		
	}
	
	private void routeToDestination(Message message) {
		
		int messageType = message.getType();
		
		switch (messageType) {
			case OpCodes.LOG_IN_ACCEPTED: System.out.println("Log-in accepted");
											controller.nowLoggedIn();
											System.out.println(message.getJson());
										   break; // SET MODEL TO HAVE THIS
		}
	}
	
	public Message unpackMessage(String received) {
		return gson.fromJson(received, Message.class);
	}
	
	public String readFromServer() {
		
		String received = null;
		
		try {
			received = inFromServer.readLine();
		} catch (IOException e) {
			System.out.println("Could not receive message from server, Receiver");
			e.printStackTrace();
		}
		
		return received;
	}
}

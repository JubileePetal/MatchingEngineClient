package communication;

import java.io.BufferedReader;
import java.io.IOException;

import models.Message;
import models.OpCodes;

import com.google.gson.Gson;

import control.Controller;

public class Receiver implements Runnable {

	private Controller controller; 
	BufferedReader inFromServer;
	Gson gson;
	
	public Receiver(Controller controller, BufferedReader reader) {
		this.controller = controller;
		this.inFromServer = reader;
		gson = new Gson();
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
			case OpCodes.LOG_IN_ACCEPTED: break; // SET MODEL TO HAVE THIS
		}
	}
	
	private Message unpackMessage(String received) {
		return gson.fromJson(received, Message.class);
	}
	
	private String readFromServer() {
		
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

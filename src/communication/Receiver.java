package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import models.Instrument;
import models.Message;
import models.OpCodes;

import com.google.gson.Gson;

import control.Controller;

public class Receiver implements Runnable {

	public Controller controller; 
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
	
	public void routeToDestination(Message message) {
		
		int messageType = message.getType();
		
		switch (messageType) {
			case OpCodes.LOG_IN_ACCEPTED: logInAccepted(message);
										   break; // SET MODEL TO HAVE THIS
		}
	}
	
	public void logInAccepted(Message message) {
		
		Instrument[] instruments;
		
		System.out.println("Log-in accepted");
		controller.nowLoggedIn();
		instruments = gson.fromJson(message.getJson(), Instrument[].class);
		for(Instrument i : instruments) {
			System.out
					.println(i.getName());
		}
		System.out.println(message.getJson());
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

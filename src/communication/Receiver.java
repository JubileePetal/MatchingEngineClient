package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import models.DataHolder;
import models.Instrument;
import models.Message;
import models.OpCodes;
import models.Order;

import com.google.gson.Gson;

import control.Controller;

public class Receiver implements Runnable {

	public Controller controller; 
	private BufferedReader inFromServer;
	private Gson gson;
	private DataHolder dataHolder;
	
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
			case OpCodes.ORDER_ADDED: orderAdded(message.getJson());
										break;
		}
	}
	
	public void orderAdded(String json) {
		Order order = gson.fromJson(json, Order.class);
		dataHolder.addOrder(order);
	}
	
	public void logInAccepted(Message message) {
		
		Instrument[] instruments;
		
		System.out.println("Log-in accepted");
		
		instruments = gson.fromJson(message.getJson(), Instrument[].class);
		for(Instrument i : instruments) {
			System.out
					.println(i.getName());
		}
		System.out.println(message.getJson());
		dataHolder.setInstruments(instruments);
		dataHolder.loggedIn();
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

	public void addDataHolder(DataHolder dataHolder) {
		this.dataHolder = dataHolder;
		
	}
}

package control;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import view.GUI;
import view.LogInPanel;

import models.DataHolder;

import com.google.gson.Gson;

import communication.Receiver;
import communication.Sender;

public class Initializer {

	private Controller controller;
	private Socket socket;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;
	private Sender sender;
	private Receiver receiver;
	private GUI userInterface;
	private DataHolder dataHolder;
	
	public Initializer(String host, int port) {
		
		System.out.println("Attempting to connect to " + host + " on port " + port);

		// Initialize Communication
		socket = initializeSocket(host, port);
/*		
		DataOutputStream outToServer = createOutputStream(socket);
		BufferedReader inFromServer = createBufferedReader(socket);

		Sender sender = new Sender();
		Receiver receiver = new Receiver();

		// Initialize Model View Controller
		controller = new Controller();		
		GUI userInterface = new GUI();
		DataHolder dataHolder = new DataHolder();		
		
		// Establish dependencies
		sender.addOutputStream(outToServer);
		receiver.addBufferedReader(inFromServer);
		receiver.addController(controller);
		
		controller.addSender(sender);
		controller.addDataHolder(dataHolder);
		
		userInterface.addController(controller);
		
		dataHolder.addObserver(userInterface);

		
		Thread t = new Thread(receiver);
		t.start();
		
		userInterface.logInPrompt();
		
		while(true) {
			
		}
*/	
	}
	public void promptUserToLogin() {
		userInterface.logInPrompt();
	}
	
	public void startListeningToServer() {
		Thread t = new Thread(receiver);
		t.start();
	}

	public void establishDependencies() {
		
		sender.addOutputStream(outToServer);
		receiver.addBufferedReader(inFromServer);
		receiver.addController(controller);
		
		controller.addSender(sender);
		controller.addDataHolder(dataHolder);
		
		userInterface.addController(controller);
		
		dataHolder.addObserver(userInterface);
	}
	
	public void initializeCommunicationObjects() {
		
		outToServer = createOutputStream(socket);
		inFromServer = createBufferedReader(socket);
		
		sender = new Sender();
		receiver = new Receiver();

	}
	
	public void initializeMVC() {
		
		controller = new Controller();		
		userInterface = new GUI();
		dataHolder = new DataHolder();	
	}
	
	
	
	private Socket initializeSocket(String host, int port) {

		Socket socket = null;		
		try {
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host when trying to connect socket, shutting down.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("IO exception when trying to connect socket, shutting down.");
			System.exit(0);
		}
		return socket;		
	}
	
	private BufferedReader createBufferedReader(Socket socket) {
		
		BufferedReader inFromServer = null;
		try {
			inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("IO exception when trying to create inputstream from server, shutting down.");
			System.exit(0);
		}
		return inFromServer;
		
	}
	
	private DataOutputStream createOutputStream(Socket socket) {
		
		DataOutputStream outToServer = null;
		
		try {
			outToServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("IO exception when trying to create outputstream to server, shutting down.");
			System.exit(0);
		}
		return outToServer;	
	}
}

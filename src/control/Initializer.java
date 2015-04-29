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
	private Updater updater;
	
	public Initializer(String host, int port) {
		
		System.out.println("Attempting to connect to " + host + " on port " + port);
		socket = initializeSocket(host, port);

	}
	/*
	public void promptUserToLogin() {
		userInterface.logInPrompt();
	}
	*/
	public void startListeningToServer() {
		Thread t = new Thread(receiver);
		t.start();
	}

	public void createUpdater(){
		
		updater = new Updater();
	}

	public void establishDependencies() {
		
		sender.addOutputStream(outToServer);
		receiver.addBufferedReader(inFromServer);
		receiver.addController(controller); 
		receiver.addDataHolder(dataHolder);
		
		controller.addSender(sender);
		
		userInterface.addController(controller);
		
		dataHolder.addObserver(userInterface);
		dataHolder.addObserver(updater);
		updater.setViews(userInterface.getViews());
		updater.setTreeList(userInterface.getTreeList());
		updater.setLordFrame(userInterface.getLordFrame());
		updater.setDataHolder(dataHolder);
	   // updater.buildTree();
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
	
	public void logInPrompt() {

		String nick = "Default";
		int userType = 0;
		
		LogInPanel logInPanel = new LogInPanel();

		if(logInPanel.logInAttemptConfirmed()) {
			nick = logInPanel.getNickName();
			userType = logInPanel.getUserType();
			
		} else {
			System.exit(0);
		}

		controller.attemptLogIn(nick, userType);
		controller.sendOrder(); // TODO REMOVE THIS SHIZ
		dataHolder.setNickName(nick);
	
	}
}

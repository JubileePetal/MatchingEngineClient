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

import com.google.gson.Gson;
import communication.Sender;

public class Initializer {

	private Controller controller;
	
	public Initializer(String host, int port) {
		
		System.out.println("Attempting to connect to " + host + " on port " + port);

		Socket socket = initializeSocket(host, port);
		
		DataOutputStream outToServer = createOutputStream(socket);
		BufferedReader inFromServer = createBufferedReader(socket);
		
		GUI userInterface = new GUI();
		Sender sender = new Sender(outToServer);
		controller = new Controller(userInterface, sender);
		
		/*
		
		
		List<Double> list = new ArrayList<Double>() {
		    public boolean add(Double mt) {
		        int index = Collections.binarySearch(this, mt);
		        if (index < 0) index = ~index;
		        super.add(index, mt);
		        return true;
		    }
		};
		
		list.add(2.0);
		list.add(1.0);
		list.add(4.0);
		list.add(3.0);
		
		for(Double val : list) {
			System.out.println(val);
		}

		Gson gson = new Gson();
		String sendy = gson.toJson(new Order());
		
		while(true) {
						
			try {
				sentence = inFromUser.readLine();
			} catch (IOException e) {}
			try {
				outToServer.writeBytes(sendy + '\n');
			} catch (IOException e) {}
			
			System.out.println("sent");
			
			String modifiedSentence = null;
			try {
				modifiedSentence = inFromServer.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("FROM SERVER: " + modifiedSentence);
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		*/

		
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

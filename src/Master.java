import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

public class Master {
	
	public static void main(String[] args) {
		
		System.out.println("Trying to connect..");

		Socket socket = null;
		try {
			socket = new Socket("192.168.251.11",1337);
		} catch (UnknownHostException e) {
			System.out.println("I don't know this host");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception!");
			e.printStackTrace();
		}
		System.out.println("I haf connected!!");
		
		DataOutputStream outToServer = null;
		BufferedReader inFromServer = null;
		String sentence = null;
		
		try {
			outToServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {}
		
		try {
			inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {}
		
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		
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
		



		
	}
}

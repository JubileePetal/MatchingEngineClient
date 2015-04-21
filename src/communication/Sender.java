package communication;

import java.io.DataOutputStream;
import java.io.IOException;

import com.google.gson.Gson;

import models.Message;
import models.OpCodes;
import models.Order;
import models.User;

public class Sender {
	
	DataOutputStream outToServer;
	Gson gson;
	
	public Sender(DataOutputStream outToServer) {
		this.outToServer = outToServer;
		gson = new Gson();
	}
	
	public void sendOrder(Order order) {
		
		Gson gson = new Gson();
		final String sendy = gson.toJson(order);
		
		System.out.println(sendy);
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					outToServer.writeBytes(sendy + '\n' + sendy + '\n' + sendy + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
	
		try {
			t.start();
			outToServer.writeBytes(sendy + '\n' + sendy + '\n' + sendy + '\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			
		}

	}
	
	public boolean logIn(String nickname, int userType) {
		
		User user = new User();
		user.setUsername(nickname);
		user.setUserType(userType);
		
		String gsonUser = gson.toJson(user);
		
		Message message = new Message();
		message.setType(OpCodes.LOG_IN);
		message.setMessage(gsonUser);
		
		String gsonMessage = gson.toJson(message);
		
		send(gsonMessage);
		
		return true;
	}
	
	private void send(String message) {
		
		try {
			outToServer.writeBytes(message + '\n');
		} catch (IOException e) {
			System.out.println("Couldn't send!");
		}
		
	}
}

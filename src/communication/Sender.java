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
	
	public Sender() {
		gson = new Gson();
	}
	
	public void addOutputStream(DataOutputStream outputStream) {
		outToServer = outputStream;
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
		message.setJson(gsonUser);
		
		String gsonMessage = gson.toJson(message);
		
		return send(gsonMessage);
	}
	
	private boolean send(String message) {
		
		boolean isLogInSuccessful = true;
		
		try {
			outToServer.writeBytes(message + '\n');
		} catch (IOException e) {
			System.out.println("Couldn't send!");
			isLogInSuccessful = false;
		}
		return isLogInSuccessful;
	}
}

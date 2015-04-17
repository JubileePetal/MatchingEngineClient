package communication;

import java.io.DataOutputStream;
import java.io.IOException;

import com.google.gson.Gson;

import models.Order;

public class Sender {
	
	DataOutputStream outToServer;

	public Sender(DataOutputStream outToServer) {
		
		this.outToServer = outToServer;
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
}

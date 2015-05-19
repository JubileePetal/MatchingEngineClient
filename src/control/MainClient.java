package control;

public class MainClient {

	public static void main(String[] args) {
		
		String host = args[0];
		int port = 1337;
		
		Initializer initializer = new Initializer(host, port);
		initializer.createUpdater();
		initializer.initializeCommunicationObjects();
		initializer.initializeMVC();
		initializer.establishDependencies();	
		
		initializer.startListeningToServer();
		
		initializer.logInPrompt();
		

	}

}

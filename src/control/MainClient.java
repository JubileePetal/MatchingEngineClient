package control;

public class MainClient {

	public static void main(String[] args) {
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		Initializer initializer = new Initializer(host, port);
		initializer.initializeCommunicationObjects();
		initializer.initializeMVC();
		initializer.establishDependencies();
		
		initializer.startListeningToServer();
		
		initializer.promptUserToLogin();

	}

}
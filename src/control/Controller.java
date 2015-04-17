package control;
import communication.Sender;


public class Controller {

	private GUI userInterface;
	private Sender sender;
	
	public Controller(GUI userInterface, Sender sender) {
		
		this.userInterface = userInterface;
		this.sender = sender;
	}
}

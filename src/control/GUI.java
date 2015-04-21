package control;

import javax.swing.JFrame;

public class GUI {
	
	JFrame lordFrame;
	
	public GUI() {
		lordFrame = new JFrame();
		lordFrame.setTitle("Matching Engine Client");
		lordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lordFrame.setSize(700, 700);
		lordFrame.setResizable(true);
	}

}

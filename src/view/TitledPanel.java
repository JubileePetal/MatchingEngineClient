package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class TitledPanel extends JPanel {

	private TitledBorder titledBorder;
	
	public TitledPanel() {
	
		titledBorder = new TitledBorder("Init");
		
		this.setBorder(titledBorder);
		this.setLayout(new BorderLayout());
	}

	public void setTitle(String title){
		
		titledBorder.setTitle(title);
	}
	


}

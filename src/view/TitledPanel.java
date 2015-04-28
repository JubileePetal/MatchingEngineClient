package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class TitledPanel extends JPanel {

	public TitledPanel(String title) {
	
		this.setBorder(new TitledBorder(title));
		this.setLayout(new BorderLayout());
	}

}

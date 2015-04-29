package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ViewPanel extends JPanel {

	public ViewPanel(String tabName) {
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		addTitledPanel(tabName);

	}
	
	
	private void addTitledPanel(String title){
		
		TitledPanel panel 	=  new TitledPanel();
//		TitledPanel secondPanel =  new TitledPanel(OpStrings.MD);
//		TitledPanel thirdPanel 	=  new TitledPanel(OpStrings.ORDERS);
//		
		//panel.add(new ViewBuilder().builListPane(), BorderLayout.CENTER);
//		secondPanel.add(new ListBuilder().builListPane(), BorderLayout.CENTER);		
//		thirdPanel.add(new ListBuilder().builListPane(), BorderLayout.CENTER);
		
		this.add(panel);
//		this.add(secondPanel);
//		this.add(thirdPanel);
	}
	
	
	private void updateView(){
		
		System.out.println("Hello");
	}

}

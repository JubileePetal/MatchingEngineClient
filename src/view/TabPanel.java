package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;





public class TabPanel extends JTabbedPane {

	private SplitPanel sp;

	
	public TabPanel() {
		
		this.setUI(new BasicTabbedPaneUI());
		
		//this.setBackground(new Color(100, 100, 100));
		this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.setTabPlacement(JTabbedPane.TOP);
		//buildTabbedPane();
	}
	
	
	public void buildTabbedPane() {
		
//		this.setUI(new BasicTabbedPaneUI());
//		
//		//this.setBackground(new Color(100, 100, 100));
//		this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//		this.setTabPlacement(JTabbedPane.TOP);
//	
//
//		
//		
//		this.addTab(OpStrings.ORDERS, v1.builListPane(OpStrings.ORDERS));
//		this.addTab(OpStrings.HISTORY, v2.builListPane(OpStrings.HISTORY));
//		this.addTab(OpStrings.MD, v3.builListPane(OpStrings.MD));
		


		
		
	}


	public void buildViews(ViewBuilder v1, ViewBuilder v2, ViewBuilder v3) {
		
		this.setUI(new BasicTabbedPaneUI());
		
		//this.setBackground(new Color(100, 100, 100));
		this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.setTabPlacement(JTabbedPane.TOP);

		
		this.addTab(v1.getName(), v1.builListPane(OpStrings.ORDERS));
		this.addTab(v2.getName(), v2.builListPane(OpStrings.HISTORY));
		this.addTab(v3.getName(), v3.builListPane(OpStrings.MD));
		
	}
	
	
	
	


}

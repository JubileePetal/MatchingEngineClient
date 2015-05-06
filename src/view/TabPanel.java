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
		this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.setTabPlacement(JTabbedPane.TOP);

	}

}

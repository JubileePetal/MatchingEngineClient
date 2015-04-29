package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import view.ViewBuilder.FilterRenderer;

public class View extends JPanel {

	private JTable table;
	private String myName;
	private DefaultTableModel model;
	private TitledPanel titlePanel;
	
	public View(String name) {

		myName = name;
		this.setLayout(new BorderLayout());
		table = new JTable();
		model = (DefaultTableModel) table.getModel();
		
		titlePanel 	=  new TitledPanel();
		titlePanel.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new BorderLayout());
		
		String[] attributes = {"Stock", "Bid", "Ask"};
		//Object[] data = {"First" ,"Second", "Third"};
		
		
		/** PROOOOOOOOOBREEEEEEEEEEEEEEEEM **/
		
		//model.setColumnIdentifiers(attributes);
		
		/*************************************/
		
		
		table.setFont(new Font("Serif", Font.ITALIC, 14));
	
		table.setRowHeight(25);
		
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		
		

		
		table.setEnabled(false);
		
		JTableHeader header = table.getTableHeader();
		


				

		
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		
	
	
		JScrollPane scroll = new JScrollPane(panel);
		
		
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
		
		titlePanel.add(scroll, BorderLayout.CENTER);
		
		if(myName == OpStrings.ORDERS){
			
			
			model.setColumnIdentifiers(OpStrings.ORDER_COLS);
			JPanel ordersPanel = new JPanel();
			//ordersPanel.setBackground(Color.BLACK);
			JButton testButton = new JButton("Bananaaa");
			ordersPanel.add(testButton);
			
			titlePanel.add(ordersPanel, BorderLayout.EAST);
			
		}
		
		if(myName == OpStrings.MD){
			model.setColumnIdentifiers(OpStrings.MD_COLS);
			
		}
		
		if(myName == OpStrings.HISTORY){
			
			model.setColumnIdentifiers(OpStrings.HIST_COLS);
		}
		
		
		this.add(titlePanel, BorderLayout.CENTER);

	
	
	}

	public void setTableData(ArrayList<Object[]> dataArray){

		
		int rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--){
		   model.removeRow(i); 
		}
		
		for(Object [] dataRow: dataArray){
			
			model.addRow(dataRow);
			
		}		
		
		
		for (int i = 0; i < table.getColumnCount(); i++) {
		    TableColumn column = table.getColumnModel().getColumn(i);
		    column.setCellRenderer(new FilterRenderer());
		    column.setMinWidth(100);
		}

		table.repaint();

	}
		
	public String getMyName() {
		return myName;
	}

	public void  setTitledBorder(String title){
		
		titlePanel.setTitle(title);
		titlePanel.repaint();
	}

	public class FilterRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
	        if (row % 2 == 0) {
	            setOpaque(true);
	            setBackground(new Color(240,240,240));
	        } else {
	            setOpaque(false);
	        }
	        return this;
	    }
	}
}

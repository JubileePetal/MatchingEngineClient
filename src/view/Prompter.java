package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Prompter {

	public Prompter() {
		// TODO Auto-generated constructor stub
	}

	public JPanel newOrderPrompt() {

		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(400, 100));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		
		
		/**Buy/Sell panel*/
		
		JPanel typePanel = new JPanel(new BorderLayout());
		String[] types = {OpStrings.BUY, OpStrings.SELL};
		JComboBox typesCombo = new JComboBox(types);
		
		typePanel.add(new JLabel("Buy/Sell"), BorderLayout.WEST);
		typePanel.add(typesCombo);


		/**Quantity panel*/
		
		JPanel quantityPanel = new JPanel();
		JTextField quantityField = new JTextField(7);
		quantityPanel.add(new JLabel("Quantity"));
		quantityPanel.add(quantityField);

		
		JPanel contentPanel = new JPanel();
		JPanel rigthtPad = new JPanel();
		JPanel holderPanel = new JPanel(new BorderLayout());
		
		contentPanel.add(typePanel);
		contentPanel.add(quantityPanel);

		holderPanel.add(contentPanel, BorderLayout.WEST);
		holderPanel.add(rigthtPad, BorderLayout.EAST);
		
	
		
		topPanel.add(holderPanel);

		
		
		
        final JPanel pricePanel = new JPanel(); 
        pricePanel.add(new JLabel("Price: "));
        
        JTextField priceField = new JTextField(7);
        pricePanel.add(priceField); 
        final JCheckBox checkBox = new JCheckBox("Limit Order", true); 
        checkBox.setFocusPainted(false); 
        
        ComponentTitledBorder componentBorder = 
                new ComponentTitledBorder(checkBox, pricePanel 
                , BorderFactory.createEtchedBorder()); 
        checkBox.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                boolean enable = checkBox.isSelected(); 
                Component comp[] = pricePanel.getComponents(); 
                for(int i = 0; i<comp.length; i++){ 
                    comp[i].setEnabled(enable); 
                } 
            } 
        }); 
        
        
        pricePanel.setBorder(componentBorder); 
        
        mainPanel.add(topPanel);
        mainPanel.add(pricePanel, BorderLayout.SOUTH);
        
        
        return mainPanel;
//        JFrame frame = new JFrame("New Order"); 
//        Container contents = frame.getContentPane(); 
//        contents.setLayout(new FlowLayout());
//        contents.add(mainPanel);
//		frame.setLocationRelativeTo(null);
//        frame.pack(); 
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//        frame.setVisible(true); 
    } 
		
}



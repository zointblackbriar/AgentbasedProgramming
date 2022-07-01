package de.tudresden.agentontology.supplychainsimulation;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaleGUI extends JFrame{
	
	private SellerAgent myAgent; 
	private JTextField productName, productPrice; 
	
	public SaleGUI(SellerAgent sa) {
		super(sa.getLocalName());
		myAgent = sa; 
		
		JPanel p = new JPanel();
		
	}
	
	public void showGUI()  {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		int centerXCoordinate = (int) screenSize.getWidth() / 2; 
		int centerYCoordinate = (int) screenSize.getHeight() / 2; 
		setLocation(centerXCoordinate - getWidth() / 2, centerYCoordinate - getHeight() / 2); 
		super.setVisible(true);
	}
	

}

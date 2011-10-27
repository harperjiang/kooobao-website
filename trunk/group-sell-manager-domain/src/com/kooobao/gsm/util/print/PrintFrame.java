package com.kooobao.gsm.util.print;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class PrintFrame extends JFrame {

	private static final long serialVersionUID = -4575388604833678740L;

	public PrintFrame() {
		setTitle("打印");
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		createContactPanel();
		
		setVisible(true);
	}
	
	protected void createContactPanel() {
		
	}
	
	JTextField refNumberField;
	
	JButton loadButton;
	
	
}

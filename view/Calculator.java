package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculator extends JFrame{

	public Calculator() {
	
	organizeLayout();
	setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	setSize(232, 322);
	}
	
	private void organizeLayout() {
	Display d = new Display();
	d.setPreferredSize(new Dimension(233, 70));
	add(d, BorderLayout.NORTH);
	
	
	Keyboard k = new Keyboard();
	add(k, BorderLayout.CENTER);
		
		
	}

	public static void main(String[] args) {
		new Calculator();
	}
	
}

package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Memory;
import modelo.ObserverMemory;

@SuppressWarnings("serial")
public class Display extends JPanel implements ObserverMemory{
	
	private JLabel l;
	
	public Display() {
		Memory.getInstancia().AddObserver(this);
		setBackground(Color.BLACK);
		l = new JLabel(Memory.getInstancia().getText());
		l.setForeground(Color.white);
		l.setFont(new Font("courier", Font.PLAIN, 30));
	
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		
		add(l);
	}
	@Override
	public void changedValue(String newValue) {
		l.setText(newValue);
	}

}

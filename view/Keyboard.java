package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import modelo.Memory;

@SuppressWarnings("serial")
public class Keyboard extends JPanel implements ActionListener {

	private final Color COLOR_GREY_DARK = new Color(68, 68, 68);
	private final Color COLOR_GREY_LIGHT = new Color(99, 99, 99);
	private final Color COLOR_ORANGE = new Color(242, 163, 60);
	
	
	public Keyboard() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		
		setLayout(layout);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		//LINE 1
		c.gridwidth = 2;
		addbutton("AC", COLOR_GREY_DARK, c, 0, 0);
		c.gridwidth = 1;
		addbutton("±", COLOR_GREY_DARK, c, 2, 0);
		addbutton("/", COLOR_ORANGE, c, 3, 0);
		//LINE 2
		addbutton("7", COLOR_GREY_LIGHT, c, 0, 1);
		addbutton("8", COLOR_GREY_LIGHT, c, 1, 1);
		addbutton("9", COLOR_GREY_LIGHT, c, 2, 1);
		addbutton("*", COLOR_ORANGE, c, 3, 1);
		//LINE 3
		addbutton("4", COLOR_GREY_LIGHT, c, 0, 2);
		addbutton("5", COLOR_GREY_LIGHT, c, 1, 2);
		addbutton("6", COLOR_GREY_LIGHT, c, 2, 2);
		addbutton("-", COLOR_ORANGE, c, 3, 2);
		//LINE 4
		addbutton("1", COLOR_GREY_LIGHT, c, 0, 3);
		addbutton("2", COLOR_GREY_LIGHT, c, 1, 3);
		addbutton("3", COLOR_GREY_LIGHT, c, 2, 3);
		addbutton("+", COLOR_ORANGE, c, 3, 3);
		//LINE 5
		c.gridwidth = 2;
		addbutton("0", COLOR_GREY_LIGHT, c, 0, 4);
		c.gridwidth = 1;
		addbutton(",", COLOR_GREY_LIGHT, c, 2, 4);
		addbutton("=", COLOR_ORANGE, c, 3, 4);
	}

	private void addbutton(String text, Color color, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Button b = new Button(text, color);
		b.addActionListener(this);
		add(b, c);
	}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource() instanceof JButton) {
	JButton button = (JButton) e.getSource();
	Memory.getInstancia().processCommand(button.getText());
	}
}
}
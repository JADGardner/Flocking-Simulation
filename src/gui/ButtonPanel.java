package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel buttonLabel;
	
	public ButtonPanel(String labelName, JButton button) {
		buttonLabel = new JLabel(labelName);
		buttonLabel.setBackground(Color.white);
		buttonLabel.setBorder(null);
		buttonLabel.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		add(buttonLabel);
		add(button);
		setBackground(Color.white);
		setMinimumSize(new Dimension(350, 110));
		setMaximumSize(new Dimension(350, 110));
	}

	public JLabel getButtonLabel() {
		return buttonLabel;
	}
	
}

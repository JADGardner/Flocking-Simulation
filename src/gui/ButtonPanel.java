/*
 * ButtonPanel.java				19/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This Class represents a ButtonPanel which is 
 * an extension of the JPanel Class. It combines a 
 * JButton with a JLabel to display some information
 * about what the button.
 * 
 * @author Y3843317
 *
 */
public class ButtonPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel buttonLabel;
	
	/**
	 * Default Constructor builds the ButtonLabel and 
	 * sets up the clean look of the ButtonPanel.
	 * 
	 * @param labelName A String with information about the Button
	 * @param button A JButton that will perform that action.
	 * @param width The desired width of the panel.
	 * @param height The desired height of the panel.
	 */
	public ButtonPanel(String labelName, JButton button, int width, int height) {
		/* Creating the JLabel that will be associated with
		 * the JButton. */
		buttonLabel = new JLabel(labelName);
		buttonLabel.setBackground(Color.white);
		buttonLabel.setBorder(null);
		buttonLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		
		add(buttonLabel);
		add(button);
		setBackground(Color.white);
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
	}

	public JLabel getButtonLabel() {
		return buttonLabel;
	}
	
}

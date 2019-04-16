package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Utils;

public class LowerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel boidCounter;
	JButton addBoidButton;
	JButton restartButton;
	JButton removeBoidButton;
	
	public LowerPanel(GUI g){
		setMinimumSize(new Dimension(g.size, (int) (0.0654*g.size)));
		setMaximumSize(new Dimension(g.size, (int) (0.0654*g.size)));
		setBackground(Color.white);
		
		boidCounter = new JLabel("Boids: " + g.numberOfBoids);
		boidCounter.setBackground(Color.white);
		boidCounter.setBorder(null);
		boidCounter.setFont(new Font("Segoe UI", Font.PLAIN, 40));
	
		addBoidButton = new JButton();
		addBoidButton.setBackground(Color.white);
		addBoidButton.setBorder(null);
	
		
		ImageIcon plusIcon = new ImageIcon("plus.png");
		plusIcon = Utils.scaleImageIcon(plusIcon, (int) (0.0654*g.size));
	
		addBoidButton.setIcon(plusIcon);
		addBoidButton.setToolTipText("Add a boid"); // show a message 
		
		restartButton = new JButton();
		restartButton.setBackground(Color.white);
		restartButton.setBorder(null);
		
		ImageIcon restartIcon = new ImageIcon("restart.png");
		restartIcon = Utils.scaleImageIcon(restartIcon, (int) (0.0654*g.size));
	
		restartButton.setIcon(restartIcon);
		restartButton.setToolTipText("Reset the program"); // show a message 
		
		removeBoidButton = new JButton();
		removeBoidButton.setBackground(Color.white);
		removeBoidButton.setBorder(null);
		
		ImageIcon subtractIcon = new ImageIcon("subtract.png");
		subtractIcon = Utils.scaleImageIcon(subtractIcon, (int) (0.0654*g.size));
		
		removeBoidButton.setIcon(subtractIcon);
		removeBoidButton.setToolTipText("Remove a boid"); // show a message 
		
		add(boidCounter, BorderLayout.WEST);
		add(addBoidButton, BorderLayout.CENTER);
		add(restartButton, BorderLayout.CENTER);
		add(removeBoidButton, BorderLayout.CENTER);
	}
}

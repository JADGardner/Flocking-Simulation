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
	
	JLabel predatorCounter;
	JButton addPredatorButton;
	JButton removePredatorButton;
	
	ImageIcon plusIcon;
	ImageIcon subtractIcon;
	
	public LowerPanel(GUI g){
		
		plusIcon = new ImageIcon("plus.png");
		plusIcon = Utils.scaleImageIcon(plusIcon, (int) (0.0654*g.size));
		
		subtractIcon = new ImageIcon("subtract.png");
		subtractIcon = Utils.scaleImageIcon(subtractIcon, (int) (0.0654*g.size));
		
		
		JPanel eastPanel = setUpEastPanel(g);
		JPanel westPanel = setUpWestPanel(g);
		
		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.EAST);
		
		setMinimumSize(new Dimension(g.size, (int) (0.0654*g.size)));
		setMaximumSize(new Dimension(g.size, (int) (0.0654*g.size)));
		setBackground(Color.white);

	}
	
	private JPanel setUpWestPanel(GUI g){
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.white);
		
		boidCounter = new JLabel("Boids: " + g.numberOfBoids);
		boidCounter.setBackground(Color.white);
		boidCounter.setBorder(null);
		boidCounter.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		
		addBoidButton = new JButton();
		addBoidButton.setBackground(Color.white);
		addBoidButton.setBorder(null);
	
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
		removeBoidButton.setIcon(subtractIcon);
		removeBoidButton.setToolTipText("Remove a boid"); // show a message 
		
		westPanel.add(boidCounter, BorderLayout.WEST);
		westPanel.add(addBoidButton, BorderLayout.CENTER);
		//westPanel.add(restartButton, BorderLayout.CENTER);
		westPanel.add(removeBoidButton, BorderLayout.CENTER);
		
		return westPanel;
	}
	
	private JPanel setUpEastPanel(GUI g){
		JPanel eastPanel = new JPanel();
		eastPanel.setBackground(Color.white);
		
		predatorCounter = new JLabel("Predators: " + g.numberOfPredators);
		predatorCounter.setBackground(Color.white);
		predatorCounter.setBorder(null);
		predatorCounter.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		

		addPredatorButton = new JButton();
		addPredatorButton.setBackground(Color.white);
		addPredatorButton.setBorder(null);
		addPredatorButton.setIcon(plusIcon);
		addPredatorButton.setToolTipText("Add a predator"); // show a message 
		
		removePredatorButton = new JButton();
		removePredatorButton.setBackground(Color.white);
		removePredatorButton.setBorder(null);
		removePredatorButton.setIcon(subtractIcon);
		removePredatorButton.setToolTipText("Remove a predator"); // show a message 
		
		eastPanel.add(predatorCounter, BorderLayout.WEST);
		eastPanel.add(addPredatorButton, BorderLayout.CENTER);
		eastPanel.add(removePredatorButton, BorderLayout.CENTER);
		
		return eastPanel;
	
	}
}

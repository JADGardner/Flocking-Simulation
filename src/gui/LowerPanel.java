/*
 * LowerPanel.java				19/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Utils;

/**
 * This Class represents a LowerPanel, which is an
 * extension of a JPanel designed to be used at the 
 * bottom of the GUI. It contains the Add Boid and
 * Add Predator functionality.
 * 
 * @author Y3843317
 *
 */
public class LowerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel boidCounter;
	JButton addBoidButton;
	JButton removeBoidButton;
	
	JLabel predatorCounter;
	JButton addPredatorButton;
	JButton removePredatorButton;
	
	ImageIcon plusIcon;
	ImageIcon subtractIcon;
	
	private int height = 100;
	
	/**
	 * Default Constructor, sets up the default settings 
	 * for a LowerPanel and spits the Panel into 
	 * an East and West portion with the West being about
	 * the Boids and the East being about the predators. 
	 * 
	 * @param g A GUI Object the SidePanel is part of.
	 */
	public LowerPanel(GUI g){
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setMinimumSize(new Dimension(g.getWidth(), height));
		setMaximumSize(new Dimension(g.getWidth(), height));
		
		/* Importing the ImageIcons and catching the exception
		 * of the path being incorrect. The ImageIcons are 
		 * then scaled to the correct size. */
		
		try {
			plusIcon = Utils.importImageIcon("./Icons/plus.png");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace(); 
			System.out.println("File not found: \"" + "plus.png" + "\". Terminating!");
			System.exit(0);
		}
		
		plusIcon = Utils.scaleImageIcon(plusIcon, g.getIconSize());
		
		try {
			subtractIcon = Utils.importImageIcon("./Icons/subtract.png");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace(); 
			System.out.println("File not found: \"" + "subtract.png" + "\". Terminating!");
			System.exit(0);
		}
		
		subtractIcon = Utils.scaleImageIcon(subtractIcon, g.getIconSize());
		
		
		JPanel eastPanel = setUpEastPanel(g);
		JPanel westPanel = setUpWestPanel(g);
		
		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.EAST);
		

		setBackground(Color.white);

	}
	
	/**
	 * Initialises the JButtons and JLabels for 
	 * use in controlling adding and removing Boids.
	 * Returns a JPanel for this functionality.
	 * 
	 * @param g
	 * @return JPanel The panel for Adding Boids.
	 */
	private JPanel setUpWestPanel(GUI g){
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.white);
		
		/* boidCounter has the word Boids: followed by the
		 * number of boids on screen at that time. This updates
		 * as the user adds more Boids. */
		boidCounter = new JLabel("Boids: " + g.numberOfBoids);
		boidCounter.setBackground(Color.white);
		boidCounter.setBorder(null);
		boidCounter.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		
		addBoidButton = new JButton();
		addBoidButton.setBackground(Color.white);
		addBoidButton.setBorder(null);
		addBoidButton.setIcon(plusIcon);
		addBoidButton.setToolTipText("Add a boid"); // show a message 
		
		removeBoidButton = new JButton();
		removeBoidButton.setBackground(Color.white);
		removeBoidButton.setBorder(null);
		removeBoidButton.setIcon(subtractIcon);
		removeBoidButton.setToolTipText("Remove a boid"); // show a message 
		
		westPanel.add(boidCounter, BorderLayout.WEST);
		westPanel.add(addBoidButton, BorderLayout.WEST);
		westPanel.add(removeBoidButton, BorderLayout.WEST);
		
		return westPanel;
	}
	
	/**
	 * Initialises the JButtons and JLabels for 
	 * use in controlling adding and removing Predators.
	 * Returns a JPanel for this functionality.
	 * 
	 * @param g
	 * @return JPanel The panel for Adding Predators.
	 */
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
		
		eastPanel.add(predatorCounter, BorderLayout.EAST);
		eastPanel.add(addPredatorButton, BorderLayout.EAST);
		eastPanel.add(removePredatorButton, BorderLayout.EAST);
		
		return eastPanel;
	
	}
}

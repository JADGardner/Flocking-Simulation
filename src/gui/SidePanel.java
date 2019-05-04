/*
 * SidePanel.java				19/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.Utils;

/**
 * This Class represents a SidePanel which is 
 * an extension of JPanel. It contains multiple SliderPanels
 * and JButtons to provide the user with interactivity 
 * within the program.
 * 
 * @author Y3843317
 *
 */
public class SidePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* These are using no modifier as they
	 * need to be visible to the ActionListeners Class. */
	SliderPanel maxSpeedSlider;
	SliderPanel cohesionSlider;
	SliderPanel separationSlider;
	SliderPanel alignmentSlider;
	SliderPanel mouseAvoidanceSlider;
	
	JButton addWallButton;
	JButton addVortexButton;
	JButton addWindButton;
	
	ButtonPanel wallButtonPanel; 
	ButtonPanel vortexButtonPanel;
	ButtonPanel windButtonPanel;
	
	private ImageIcon wallIcon;
	private ImageIcon vortexIcon;
	private ImageIcon windIcon;

	/**
	 * Default Constructor initialises all the SliderPanels 
	 * and imports the icons for the buttonPanels.
	 * 
	 * @param g A GUI Object the SidePanel is part of.
	 */
	public SidePanel(GUI g, int width, int height, int sectionHeight, int iconSize){
		/* Setting up default properties of a SidePanel. */
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.white);
		BoxLayout sideLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(sideLayout);
		
		/* Initialising all the SliderPanels with their 
		 * default minimum, maximum and initial settings. */
		maxSpeedSlider = new SliderPanel("Max Speed", g.MIN_SPEED, g.MAX_SPEED, g.INITIAL_SPEED);
		cohesionSlider = new SliderPanel("Cohesion", g.MIN_COHESION_CONSTANT, g.MAX_COHESION_CONSTANT, g.INITIAL_COHESION_CONSTANT);
		separationSlider = new SliderPanel("Seperation", g.MIN_SEPERATION_CONSTANT, g.MAX_SEPERATION_CONSTANT, g.INITIAL_SEPERATION_CONSTANT);
		alignmentSlider = new SliderPanel("Alignment", g.MIN_ALIGNMENT_CONSTANT, g.MAX_ALIGNMENT_CONSTANT, g.INITIAL_ALIGNMENT_CONSTANT);
		mouseAvoidanceSlider = new SliderPanel("Mouse Avoidance", g.MIN_MOUSE_AVOID_CONSTANT, g.MAX_MOUSE_AVOID_CONSTANT, g.INITIAL_MOUSE_AVOID_CONSTANT);

		add(maxSpeedSlider);
		add(cohesionSlider);
		add(separationSlider);
		add(alignmentSlider);
		add(mouseAvoidanceSlider);
		
		/* Setting up buttons */
		
		addWallButton = new JButton();
		addWallButton.setBackground(Color.white);
		addWallButton.setBorder(null);
		
		/* Importing the custom icons to be used 
		 * on the JButtons. Handling a potential error
		 * if the path is wrong. */ 
		try {
			wallIcon = Utils.importImageIcon("./Icons/wall.png");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace(); 
			System.out.println("File not found: \"" + "wall.png" + "\". Terminating!");
			System.exit(0);
		}
		
		/* Scaling the ImageIcon. */
		wallIcon = Utils.scaleImageIcon(wallIcon, iconSize);
		
		addWallButton.setIcon(wallIcon);
		addWallButton.setToolTipText("Add walls");
		
		addVortexButton = new JButton();
		addVortexButton.setBackground(Color.white);
		addVortexButton.setBorder(null);
		
		try {
			vortexIcon = Utils.importImageIcon("./Icons/vortex.png");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace(); 
			System.out.println("File not found: \"" + "vortex.png" + "\". Terminating!");
			System.exit(0);
		}
		
		vortexIcon = Utils.scaleImageIcon(vortexIcon, iconSize);
		
		addVortexButton.setIcon(vortexIcon);
		addVortexButton.setToolTipText("Add vortex");
		
		addWindButton = new JButton();
		addWindButton.setBackground(Color.white);
		addWindButton.setBorder(null);
		
		try {
			windIcon = Utils.importImageIcon("./Icons/wind.png");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace(); 
			System.out.println("File not found: \"" + "wind.png" + "\". Terminating!");
			System.exit(0);
		}
		
		windIcon = Utils.scaleImageIcon(windIcon, iconSize);
		
		addWindButton.setIcon(windIcon);
		addWindButton.setToolTipText("Add wind");
	
		/* Creating the ButtonPanels using the new Icons. */
		wallButtonPanel = new ButtonPanel("Place Walls ", addWallButton, width, sectionHeight);
		vortexButtonPanel = new ButtonPanel("Add Vortex ", addVortexButton, width, sectionHeight);
		windButtonPanel = new ButtonPanel("Turn Wind On ", addWindButton, width, sectionHeight);
		
		add(wallButtonPanel);
		add(vortexButtonPanel);
		add(windButtonPanel);
	}

	public ButtonPanel getWallButtonPanel() {
		return wallButtonPanel;
	}

	public ButtonPanel getVortexButtonPanel() {
		return vortexButtonPanel;
	}

	public ButtonPanel getWindButtonPanel() {
		return windButtonPanel;
	}

	
}

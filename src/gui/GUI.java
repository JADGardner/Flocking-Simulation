/*
 * GUI.java				19/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawing.Canvas;
import main.FlockingSimulator;

/**
 * This Class represents a GUI Object that
 * is the top level of all other gui objects.
 * 
 * @author Y3843317
 *
 */
public class GUI {
	private JFrame frame;
	private Canvas canvas;
	private SidePanel sidePanel;
	private LowerPanel lowerPanel;
	
	/* Default display sizes. */
	private int width = 1500;
	private int height = 1000;
	private int lowerPanelHeight = 100;
	private int sidePanelWidth = 350;
	private int sidePanelSectionHeight = 110;
	private int iconSize = 70;
	private int fontSize = 40;

	/* Default setting for sliders. */
	final int MIN_SPEED = 100;
	final int INITIAL_SPEED = 300;
	final int MAX_SPEED = 500;
	
	final int MIN_COHESION_CONSTANT = 0;
	final int INITIAL_COHESION_CONSTANT = 1;
	final int MAX_COHESION_CONSTANT = 10;
	
	final int MIN_SEPERATION_CONSTANT = 0;
	final int INITIAL_SEPERATION_CONSTANT = 10;
	final int MAX_SEPERATION_CONSTANT = 100;
	
	final int MIN_ALIGNMENT_CONSTANT = 0;
	final int INITIAL_ALIGNMENT_CONSTANT = 120;
	final int MAX_ALIGNMENT_CONSTANT = 1000;
	
	final int MIN_MOUSE_AVOID_CONSTANT = 0;
	final int INITIAL_MOUSE_AVOID_CONSTANT = 100;
	final int MAX_MOUSE_AVOID_CONSTANT = 300;
	
	int numberOfBoids;
	int numberOfPredators;

	/**
	 * The constructor for the GUI. Generates an overall JFrame
	 * and populates that Frame with multiple JPanels all with
	 * different functionality. 
	 * 
	 * @param FS A FlockingSimulator Object
	 */
	public GUI(FlockingSimulator FS) {
		this.numberOfBoids = FS.getNumberOfBoids();
		this.numberOfPredators = FS.getNumberOfPredators();
		
		frame = new JFrame();
		frame.setTitle("Java Assignment, Student Y3843317");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		/* Main component things are drawn too in each loop. */
		canvas = new Canvas();

		/* These contain the user controls for the program. */
		sidePanel = new SidePanel(this, sidePanelWidth, height, sidePanelSectionHeight, iconSize);
		lowerPanel = new LowerPanel(this, width, lowerPanelHeight, iconSize, fontSize);
		
		frame.add(sidePanel, BorderLayout.EAST);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		frame.add(canvas, BorderLayout.CENTER);
		
		frame.revalidate();

	}
	
	public SidePanel getSidePanel() {
		return sidePanel;
	}

	public LowerPanel getLowerPanel() {
		return lowerPanel;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLowerPanelHeight() {
		return lowerPanelHeight;
	}

	public int getSidePanelWidth() {
		return sidePanelWidth;
	}

	public int getIconSize() {
		return iconSize;
	}
	
}

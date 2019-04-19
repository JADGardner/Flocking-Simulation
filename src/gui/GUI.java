package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawing.Canvas;
import main.FlockingSimulator;


public class GUI {
	private JFrame frame;
	private Canvas canvas;
	private SidePanel sidePanel;
	private LowerPanel lowerPanel;

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
	
	int width = 1500;
	int height = 1000;
	int iconSize = 70;
	
	public GUI(FlockingSimulator FS) {
		frame = new JFrame();
		frame.setTitle("Flocking Simulation");
		this.numberOfBoids = FS.getNumberOfBoids();
		this.numberOfPredators = FS.getNumberOfPredators();

		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		canvas = new Canvas();

		sidePanel = new SidePanel(this);
		lowerPanel = new LowerPanel(this);
		
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

	public int getIconSize() {
		return iconSize;
	}
	
	

}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import drawing.Canvas;

public class GUI {
	private JFrame frame;
	private Canvas canvas;
	private JPanel sidePanel;
	private JPanel lowerPanel;
	private BoxLayout sideLayout;
	private SliderPanel maxSpeedSlider;
	private SliderPanel cohesionSlider;
	private SliderPanel sperationSlider;
	private SliderPanel alignmentSlider;
	private SliderPanel mouseAvoidanceSlider;
	private JButton addBoidButton;
	private JButton removeBoidButton;
	
	private final int MIN_SPEED = 100;
	private final int MAX_SPEED = 500;
	private final int MIN_COHESION_CONSTANT = 0;
	private final int MAX_COHESION_CONSTANT = 100;
	private final int MIN_SEPERATION_CONSTANT = 0;
	private final int MAX_SEPERATION_CONSTANT = 100;
	private final int MIN_ALIGNMENT_CONSTANT = 0;
	private final int MAX_ALIGNMENT_CONSTANT = 1000;
	private final int MIN_MOUSE_AVOID_CONSTANT = 0;
	private final int MAX_MOUSE_AVOID_CONSTANT = 300;
	
	private int size;
	
	public GUI() {
		frame = new JFrame();
		frame.setTitle("Flocking Simulation");
		
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		size = (int) (0.5*screenDimension.getWidth());
		
		frame.setSize(size, size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		canvas = new Canvas();
		
		createSidePanel();
		createLowerPanel();
		
		frame.add(sidePanel, BorderLayout.EAST);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		frame.add(canvas, BorderLayout.CENTER);
		
		frame.revalidate();

	}
	
	private void createSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension((int) (0.374*size), size));
		sidePanel.setBackground(Color.white);
		sideLayout = new BoxLayout(sidePanel, BoxLayout.Y_AXIS);
		sidePanel.setLayout(sideLayout);
		
		maxSpeedSlider = new SliderPanel("Speed", MIN_SPEED, MAX_SPEED);
		cohesionSlider = new SliderPanel("Cohesion",MIN_COHESION_CONSTANT, MAX_COHESION_CONSTANT);
		sperationSlider = new SliderPanel("Seperation",MIN_SEPERATION_CONSTANT, MAX_SEPERATION_CONSTANT);
		alignmentSlider = new SliderPanel("Alignment",MIN_ALIGNMENT_CONSTANT, MAX_ALIGNMENT_CONSTANT);
		mouseAvoidanceSlider = new SliderPanel("Mouse Avoidance",MIN_MOUSE_AVOID_CONSTANT, MAX_MOUSE_AVOID_CONSTANT);
		
		sidePanel.add(maxSpeedSlider);
		sidePanel.add(cohesionSlider);
		sidePanel.add(sperationSlider);
		sidePanel.add(alignmentSlider);
		sidePanel.add(mouseAvoidanceSlider);
		
		addBoidButton = new JButton("Add");
		removeBoidButton = new JButton("Remove");
		
		addBoidButton.setToolTipText("Add a boid"); // show a message 
		
		sidePanel.add(addBoidButton);
		sidePanel.add(removeBoidButton);
	}
	
	private void createLowerPanel() {
		lowerPanel = new JPanel();
		lowerPanel.setPreferredSize(new Dimension(size, (int) (0.0654*size)));
		lowerPanel.setBackground(Color.white);
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public static void main(String[] args) {
		new GUI();
	}

}

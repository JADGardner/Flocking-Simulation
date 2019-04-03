package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
	private final int INITIAL_SPEED = 200;
	private final int MAX_SPEED = 500;
	
	private final int MIN_COHESION_CONSTANT = 0;
	private final int INITIAL_COHESION_CONSTANT = 1;
	private final int MAX_COHESION_CONSTANT = 10;
	
	private final int MIN_SEPERATION_CONSTANT = 0;
	private final int INITIAL_SEPERATION_CONSTANT = 10;
	private final int MAX_SEPERATION_CONSTANT = 100;
	
	private final int MIN_ALIGNMENT_CONSTANT = 0;
	private final int INITIAL_ALIGNMENT_CONSTANT = 120;
	private final int MAX_ALIGNMENT_CONSTANT = 1000;
	
	private final int MIN_MOUSE_AVOID_CONSTANT = 0;
	private final int INITIAL_MOUSE_AVOID_CONSTANT = 100;
	private final int MAX_MOUSE_AVOID_CONSTANT = 300;
	
	private int size;
	
	public GUI() {
		frame = new JFrame();
		frame.setTitle("Flocking Simulation");
		
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		size = (int) (0.5*screenDimension.getWidth());
		
		frame.setSize((int) (1.374*size), size);
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
		
		maxSpeedSlider = new SliderPanel("Max Speed", MIN_SPEED, MAX_SPEED, INITIAL_SPEED);
		cohesionSlider = new SliderPanel("Cohesion",MIN_COHESION_CONSTANT, MAX_COHESION_CONSTANT, INITIAL_COHESION_CONSTANT);
		sperationSlider = new SliderPanel("Seperation",MIN_SEPERATION_CONSTANT, MAX_SEPERATION_CONSTANT, INITIAL_SEPERATION_CONSTANT);
		alignmentSlider = new SliderPanel("Alignment",MIN_ALIGNMENT_CONSTANT, MAX_ALIGNMENT_CONSTANT, INITIAL_ALIGNMENT_CONSTANT);
		mouseAvoidanceSlider = new SliderPanel("Mouse Avoidance",MIN_MOUSE_AVOID_CONSTANT, MAX_MOUSE_AVOID_CONSTANT, INITIAL_MOUSE_AVOID_CONSTANT);
		

		
		sidePanel.add(maxSpeedSlider);
		sidePanel.add(cohesionSlider);
		sidePanel.add(sperationSlider);
		sidePanel.add(alignmentSlider);
		sidePanel.add(mouseAvoidanceSlider);
		

		
	}
	
	private void createLowerPanel() {
		lowerPanel = new JPanel();
		lowerPanel.setMinimumSize(new Dimension(size, (int) (0.0654*size)));
		lowerPanel.setMaximumSize(new Dimension(size, (int) (0.0654*size)));
		lowerPanel.setBackground(Color.white);
		
		addBoidButton = new JButton();
		addBoidButton.setBackground(Color.white);
		addBoidButton.setBorder(null);
		
		ImageIcon plusIcon = new ImageIcon("plus.png");
		plusIcon = scaleImageIcon(plusIcon, (int) (0.0654*size));
		
	
		addBoidButton.setIcon(plusIcon);
		addBoidButton.setToolTipText("Add a boid"); // show a message 
		
		removeBoidButton = new JButton();
		removeBoidButton.setBackground(Color.white);
		removeBoidButton.setBorder(null);
		
		ImageIcon subtractIcon = new ImageIcon("subtract.png");
		subtractIcon = scaleImageIcon(subtractIcon, (int) (0.0654*size));
		
		removeBoidButton.setIcon(subtractIcon);
		removeBoidButton.setToolTipText("Remove a boid"); // show a message 
		
		lowerPanel.add(addBoidButton, BorderLayout.CENTER);
		lowerPanel.add(removeBoidButton, BorderLayout.CENTER);
		
	}

	public Canvas getCanvas() {
		return canvas;
	}
		
	private ImageIcon scaleImageIcon(ImageIcon i, int size) {
		Image image = i.getImage(); // transform it 
		Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		i = new ImageIcon(newimg);  // transform it back
		return i;
	}
	
	public SliderPanel getMaxSpeedSlider() {
		return maxSpeedSlider;
	}

	public SliderPanel getCohesionSlider() {
		return cohesionSlider;
	}

	public SliderPanel getSperationSlider() {
		return sperationSlider;
	}

	public SliderPanel getAlignmentSlider() {
		return alignmentSlider;
	}

	public SliderPanel getMouseAvoidanceSlider() {
		return mouseAvoidanceSlider;
	}
	
	public JButton getAddBoidButton() {
		return addBoidButton;
	}
	
	public JButton getRemoveBoidButton() {
		return removeBoidButton;
	}
	
	public static void main(String[] args) {
		new GUI();
	}

}

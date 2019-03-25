import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boids.IntelligentBoid;
import drawing.Canvas;
import gui.SliderPanel;
import tools.Utils;

public class FlockingSimulator {
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
	
	private final int WINDOW_X_SIZE = 1800;
	private final int WINDOW_Y_SIZE = 1500;
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
	
	private List<IntelligentBoid> boids;
	
	public FlockingSimulator(){
		setupGUI();
		setUpBoids();
		gameLoop();
	}
	
	
	private void setupGUI(){
		frame = new JFrame();
		frame.setTitle("Flocking Simulation");
		frame.setSize(WINDOW_X_SIZE, WINDOW_Y_SIZE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		canvas = new Canvas();
		sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(300, WINDOW_Y_SIZE));
		sidePanel.setBackground(Color.white);
		sideLayout = new BoxLayout(sidePanel, BoxLayout.Y_AXIS);
		sidePanel.setLayout(sideLayout);
		
		lowerPanel = new JPanel();
		lowerPanel.setPreferredSize(new Dimension(1500, 70));
		lowerPanel.setBackground(Color.white);
		
		maxSpeedSlider = new SliderPanel("Speed", MIN_SPEED, MAX_SPEED);
		cohesionSlider = new SliderPanel("Cohesion",MIN_COHESION_CONSTANT, MAX_COHESION_CONSTANT);
		sperationSlider = new SliderPanel("Seperation",MIN_SEPERATION_CONSTANT, MAX_SEPERATION_CONSTANT);
		alignmentSlider = new SliderPanel("Alignment",MIN_ALIGNMENT_CONSTANT, MAX_ALIGNMENT_CONSTANT);
		mouseAvoidanceSlider = new SliderPanel("Mouse Avoidance",MIN_MOUSE_AVOID_CONSTANT, MAX_MOUSE_AVOID_CONSTANT);
		
		addBoidButton = new JButton();
		addBoidButton.setBackground(Color.white);
		addBoidButton.setBorder(null);
		addBoidButton.setIcon(new ImageIcon("plus.png"));
		addBoidButton.setToolTipText("Add a boid"); // show a message 

		removeBoidButton = new JButton();
		removeBoidButton.setBackground(Color.white);
		removeBoidButton.setBorder(null);
		removeBoidButton.setIcon(new ImageIcon("subtract.png"));
		removeBoidButton.setToolTipText("Remove a boid"); // show a message 
		
		frame.add(sidePanel, BorderLayout.EAST);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		sidePanel.add(maxSpeedSlider);
		sidePanel.add(cohesionSlider);
		sidePanel.add(sperationSlider);
		sidePanel.add(alignmentSlider);
		sidePanel.add(mouseAvoidanceSlider);
		
		
		
		lowerPanel.add(addBoidButton);
		lowerPanel.add(removeBoidButton);
		
		
		
		frame.add(canvas, BorderLayout.CENTER);
		
		maxSpeedSlider.getSlider().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setMaxSpeed(maxSpeedSlider.getSlider().getValue());
					}
				}		
			}
		});
		
		cohesionSlider.getSlider().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setCohesion((cohesionSlider.getSlider().getValue()/100));
					}
				}		
			}
		});
		
		sperationSlider.getSlider().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setSeperation(sperationSlider.getSlider().getValue());
					}
				}		
			}
		});
		
		alignmentSlider.getSlider().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAlignment((alignmentSlider.getSlider().getValue()/1000));
					}
				}
			}
		});
		
		mouseAvoidanceSlider.getSlider().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAvoidMouseConstant(mouseAvoidanceSlider.getSlider().getValue());
					}
				}
			}
		});
		
		addBoidButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (boids){
					boids.add(new IntelligentBoid(canvas, -100, -100));
				}
				
			}
		});
		
		removeBoidButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (boids){
					if(boids.size() > 0){
						boids.get(boids.size() - 1).unDraw();
						boids.remove(boids.size() - 1);
					}
				}
			}
		});
		
		alignmentSlider.getCheckBox().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(alignmentSlider.getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setAlignmentOn(true);
							intelligentBoid.setAlignment(0.120);
							alignmentSlider.getSlider().setValue(120);
						}
					}
				} else {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setAlignmentOn(false);
						}
					}
				}

				
			}
		});
		
		
		frame.revalidate();

	}
	
	
	private void setUpBoids(){
		
		boids = Collections.synchronizedList(new ArrayList<IntelligentBoid>());
		
		int x = 0;
		int y = 0;
		for(int i = 0; i < 600; i++){
			int r = Utils.randomInt(4);
			switch(r) {
			case 0:
				x = Utils.randomInt(canvas.getWidth());
				y = -Utils.randomInt(200);
				break;
			case 1:
				x = canvas.getWidth() + Utils.randomInt(200);
				y = Utils.randomInt(canvas.getHeight());
				break;
			case 2:
				x = Utils.randomInt(canvas.getWidth());
				y = canvas.getHeight() + Utils.randomInt(200);
				break;
			case 3:
				x = -Utils.randomInt(200);
				y = Utils.randomInt(canvas.getHeight());
				break;
			}
			boids.add(new IntelligentBoid(canvas, x, y));
		}
	}

	
	private void gameLoop(){
	
		int deltaTime = 20;
		boolean continueRunning = true;
		// game loop
		while (continueRunning){

			
			Point mousePoint = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(mousePoint, canvas);
			
			synchronized (boids){
				for (IntelligentBoid intelligentBoid : boids) {
					intelligentBoid.calculateVelocity(boids, canvas.getWidth(), canvas.getHeight(), mousePoint);
					intelligentBoid.update(deltaTime);
					//intelligentBoid.wrapPosition(canvas.getWidth(), canvas.getHeight());
				}
			}
			
			synchronized (boids){
				for (IntelligentBoid intelligentBoid : boids) {
					intelligentBoid.unDraw();
					//System.out.println(intelligentBoid.getAngle());
				}
			}
			
			synchronized (boids){
				for (IntelligentBoid intelligentBoid : boids) {
					
					intelligentBoid.draw();
				}
			}
			Utils.pause(deltaTime);

		}
	}
	

	public static void main(String[] args) {
		new FlockingSimulator();

	}
	


}

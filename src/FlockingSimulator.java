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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boids.IntelligentBoid;
import drawing.Canvas;
import tools.Utils;

public class FlockingSimulator {
	private JFrame frame;
	private Canvas canvas;
	private JPanel sidePanel;
	private JPanel lowerPanel;
	private BoxLayout sideLayout;
	private JSlider maxSpeed;
	private JSlider cohesionSlider;
	private JSlider sperationSlider;
	private JSlider alignmentSlider;
	private JSlider mouseAvoidanceSlider;
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
		lowerPanel.setPreferredSize(new Dimension(WINDOW_X_SIZE, 70));
		lowerPanel.setBackground(Color.white);
		
		maxSpeed = new JSlider(MIN_SPEED, MAX_SPEED);
		cohesionSlider = new JSlider(MIN_COHESION_CONSTANT, MAX_COHESION_CONSTANT);
		sperationSlider = new JSlider(MIN_SEPERATION_CONSTANT, MAX_SEPERATION_CONSTANT);
		alignmentSlider = new JSlider(MIN_ALIGNMENT_CONSTANT, MAX_ALIGNMENT_CONSTANT);
		mouseAvoidanceSlider = new JSlider(MIN_MOUSE_AVOID_CONSTANT, MAX_MOUSE_AVOID_CONSTANT);
		
		addBoidButton = new JButton("Add");
		removeBoidButton = new JButton("Remove");
		
		addBoidButton.setToolTipText("Add a boid"); // show a message 


		frame.add(sidePanel, BorderLayout.EAST);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		sidePanel.add(maxSpeed);
		sidePanel.add(cohesionSlider);
		sidePanel.add(sperationSlider);
		sidePanel.add(alignmentSlider);
		sidePanel.add(mouseAvoidanceSlider);
		sidePanel.add(addBoidButton);
		sidePanel.add(removeBoidButton);
		
		frame.add(canvas, BorderLayout.CENTER);
		
		maxSpeed.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setMaxSpeed(maxSpeed.getValue());
					}
				}		
			}
		});
		
		cohesionSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setCohesion((cohesionSlider.getValue()/100));
					}
				}		
			}
		});
		
		sperationSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setSeperation(sperationSlider.getValue());
					}
				}		
			}
		});
		
		alignmentSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAlignment((alignmentSlider.getValue()/1000));
					}
				}
			}
		});
		
		mouseAvoidanceSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAvoidMouseConstant(mouseAvoidanceSlider.getValue());
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
		
		
		frame.revalidate();

	}
	
	
	private void setUpBoids(){
		
		boids = Collections.synchronizedList(new ArrayList<IntelligentBoid>());
		
		int x = 0;
		int y = 0;
		for(int i = 0; i < 700; i++){
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

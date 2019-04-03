import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boids.IntelligentBoid;
import boids.Predator;
import gui.GUI;
import tools.Utils;

public class FlockingSimulator {

	private GUI gui;
	private List<IntelligentBoid> boids;

	public FlockingSimulator(){
		setupGUI();
		setUpBoids();
		gameLoop();
	}


	private void setupGUI(){
		gui = new GUI();

		gui.getMaxSpeedSlider().getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setMaxSpeed(gui.getMaxSpeedSlider().getSlider().getValue());
					}
				}
			}
		});

		gui.getCohesionSlider().getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setCohesion((gui.getCohesionSlider().getSlider().getValue()/10));
					}
				}
			}
		});

		gui.getSperationSlider().getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setSeperation(gui.getSperationSlider().getSlider().getValue()/10);
					}
				}
			}
		});

		gui.getAlignmentSlider().getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAlignment((		gui.getAlignmentSlider().getSlider().getValue()/1000));
					}
				}
			}
		});

		gui.getMouseAvoidanceSlider().getSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				synchronized (boids) {
					for(IntelligentBoid intelligentBoid : boids) {
						intelligentBoid.setAvoidMouseConstant(gui.getMouseAvoidanceSlider().getSlider().getValue());
					}
				}

			}
		});

		gui.getAddBoidButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (boids){
					boids.add(new IntelligentBoid(gui.getCanvas(), -100, -100));
				}

			}
		});

		gui.getRemoveBoidButton().addActionListener(new ActionListener() {

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

		gui.getAlignmentSlider().getCheckBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(gui.getAlignmentSlider().getCheckBox().isSelected()) {
					synchronized (boids){
						for(IntelligentBoid intelligentBoid : boids) {
							intelligentBoid.setAlignmentOn(true);
							intelligentBoid.setAlignment(0.120);
							gui.getAlignmentSlider().getSlider().setValue(120);
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

	}


	private void setUpBoids(){

		boids = Collections.synchronizedList(new ArrayList<IntelligentBoid>());

		int x = 0;
		int y = 0;
		for(int i = 0; i < 700; i++){
			int r = Utils.randomInt(4);
			switch(r) {
			case 0:
				x = Utils.randomInt(gui.getCanvas().getWidth());
				y = -Utils.randomInt(200);
				break;
			case 1:
				x = gui.getCanvas().getWidth() + Utils.randomInt(200);
				y = Utils.randomInt(gui.getCanvas().getHeight());
				break;
			case 2:
				x = Utils.randomInt(gui.getCanvas().getWidth());
				y = gui.getCanvas().getHeight() + Utils.randomInt(200);
				break;
			case 3:
				x = -Utils.randomInt(200);
				y = Utils.randomInt(gui.getCanvas().getHeight());
				break;
			}
			boids.add(new IntelligentBoid(gui.getCanvas(), x, y));
		}
	}


	private void gameLoop(){

		int deltaTime = 20;
		boolean continueRunning = true;
		// game loop
		while (continueRunning){


			Point mousePoint = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(mousePoint, gui.getCanvas());

			synchronized (boids){
				for (IntelligentBoid intelligentBoid : boids) {
					intelligentBoid.calculateVelocity(boids, gui.getCanvas().getWidth(), gui.getCanvas().getHeight(), mousePoint);
					intelligentBoid.update(deltaTime);
				}
			}

			synchronized (boids){
				for (IntelligentBoid intelligentBoid : boids) {
					intelligentBoid.unDraw();
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

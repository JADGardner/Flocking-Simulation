package main;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;

import boids.IntelligentBoid;
import boids.Predator;
import gui.ActionListeners;
import gui.GUI;
import tools.Utils;

public class FlockingSimulator {

	private GUI gui;
	private ActionListeners actionListeners;
	private List<IntelligentBoid> boids;
	private List<Predator> predators;
	private int numberOfBoids = 500;
	private int boidSize = 10;
	private int predatorSize = 30;

	public FlockingSimulator(){
		gui = new GUI(this);
		
		setUpBoids();
		//setUpPredators();
		
		actionListeners = new ActionListeners(this);
		
		gameLoop();
	}
	
	private void setUpPredators(){
		predators = Collections.synchronizedList(new ArrayList<Predator>());
		predators.add(new Predator(gui.getCanvas(), 100, 100, predatorSize));
	}
	
	private void setUpBoids(){

		boids = Collections.synchronizedList(new ArrayList<IntelligentBoid>());

		int x = 0;
		int y = 0;
		for(int i = 0; i < numberOfBoids; i++){
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
			boids.add(new IntelligentBoid(gui.getCanvas(), x, y, boidSize));
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
	
	

	public List<IntelligentBoid> getBoids() {
		return boids;
	}

	public GUI getGUI(){
		return gui;
	}
	
	public int getNumberOfBoids() {
		return numberOfBoids;
	}
	

	public int getBoidSize() {
		return boidSize;
	}

	public static void main(String[] args) {
		new FlockingSimulator();

	}
	
	

}

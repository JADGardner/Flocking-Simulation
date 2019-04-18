package main;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;

import boids.IntelligentBoid;
import boids.Predator;
import drawing.Portal;
import drawing.Wall;
import gui.ActionListeners;
import gui.GUI;
import tools.Utils;

public class FlockingSimulator {

	private GUI gui;
	
	@SuppressWarnings("unused")
	private ActionListeners actionListeners;
	
	private List<IntelligentBoid> boids;
	private List<Predator> predators;
	private List<Portal> portals;
	private List<Wall> walls;
	
	private int numberOfBoids = 500;
	private int numberOfPredators = 0;
	private int boidSize = 10;
	private int predatorSize = 20;
	
	boolean wallPlace = false;
	int mouseClickX;
	int mouseClickY;
	

	public FlockingSimulator(){
		gui = new GUI(this);
		
		mouseListnerSetUp();
		
		setUpBoids();
		
		predators = Collections.synchronizedList(new ArrayList<Predator>());
		portals = Collections.synchronizedList(new ArrayList<Portal>());
		walls = Collections.synchronizedList(new ArrayList<Wall>());
		
		actionListeners = new ActionListeners(this);
		
		gameLoop();
	}
	
	
	private void mouseListnerSetUp(){
		gui.getFrame().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickX = e.getX();
				mouseClickY = e.getY();
				if(wallPlace == true) {
					walls.add(new Wall(mouseClickX, mouseClickY-40));
				}
			}
		});
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
					intelligentBoid.calculateVelocity(boids, predators, walls, portals, gui.getCanvas().getWidth(), gui.getCanvas().getHeight(), mousePoint);
					intelligentBoid.update(deltaTime);
				}
			}
			
			synchronized (predators){
				for (Predator predator : predators) {
					predator.calculateVelocity(predators, boids, walls, portals, gui.getCanvas().getWidth(), gui.getCanvas().getHeight(), mousePoint);
					predator.update(deltaTime);
				}
			}
			
			gui.getCanvas().clear();
			
			synchronized (portals){
				for (Portal portal : portals) {
					gui.getCanvas().addPortal(portal);
				}
			}
			
			synchronized (boids){
				for (IntelligentBoid intelligentBoid : boids) {
					intelligentBoid.draw();
				}
			}

			synchronized (predators){
				for (Predator predator : predators) {
					predator.draw();
				}
			}
			
			synchronized (walls){
				for (Wall wall : walls) {
					gui.getCanvas().addWall(wall);
				}
			}
			
			gui.getCanvas().repaint();
			
			Utils.pause(deltaTime);

		}
	}
	
	public List<IntelligentBoid> getBoids() {
		return boids;
	}

	public List<Predator> getPredators() {
		return predators;
	}

	public List<Portal> getPortals() {
		return portals;
	}
	
	public List<Wall> getWalls() {
		return walls;
	}

	public GUI getGUI(){
		return gui;
	}
	
	public int getNumberOfBoids() {
		return numberOfBoids;
	}
	
	public int getNumberOfPredators() {
		return numberOfPredators;
	}

	public int getBoidSize() {
		return boidSize;
	}
	
	public int getPredatorSize(){
		return predatorSize;
	}
	
	public void setWallPlace() {
		wallPlace = !wallPlace;
	}

	public static void main(String[] args) {
		new FlockingSimulator();
	}

}

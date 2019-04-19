/*
 * FlockingSimulator.java				18/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 * This is the top level of a Java implementation
 * of the popular Craig Reynolds program Boids. 
 * It is designed demonstrate how complex animal
 * flocking like behaviour can arise from a small
 * set of simple rules. This contains the main 
 * entry to the program.
 * 
 */

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

/**
 * This Class represents a FlockingSimulator Object which initialises 
 * the required objects for the program to run and contains the 
 * main game loop. 
 * 
 * @author Y3843317
 *
 */
public class FlockingSimulator {

	private GUI gui;
	
	@SuppressWarnings("unused")	
	/* Without this, a warning stating the
	 * actionListeners Class was unused. 
	 * This is not the case when the program
	 * is running. */
	private ActionListeners actionListeners;
	
	/* Lists to contain the main game objects. */
	private List<IntelligentBoid> boids;
	private List<Predator> predators;
	private List<Portal> portals;
	private List<Wall> walls;
	
	private int numberOfBoids = 500;
	private int numberOfPredators = 0;
	
	/* This controls the lengths of the sides of 
	 * each type of Boid. */
	private int boidSize = 10;
	private int predatorSize = 20;
	
	/* Has the place a wall button been pressed. */
	boolean wallPlace = false;
	
	/* Used as location for placing a Wall. */
	int mouseClickX;
	int mouseClickY;

	/**
	 * Initialises all the necessary objects needed for the 
	 * program to run. Makes calls to all set-up methods 
	 * and then starts the game loop.
	 */
	public FlockingSimulator(){
		gui = new GUI(this);
		
		mouseListnerSetUp();
		
		setUpBoids();
		
		/* Creating empty lists, these are filled by the user in game. 
		 * These collection are synchronised to deal with the issue of
         * concurrent accesses to the collection. */
		predators = Collections.synchronizedList(new ArrayList<Predator>());
		portals = Collections.synchronizedList(new ArrayList<Portal>());
		walls = Collections.synchronizedList(new ArrayList<Wall>());

		actionListeners = new ActionListeners(this);
		
		gameLoop();
	}
	
	/**
	 * This generates a MouseAdapter Anonymous Class
	 * to detect when the mouse is clicked. The location
	 * of the click is then stored into the variables
	 * mouseClickX-Y. 
	 */
	private void mouseListnerSetUp(){
		/* MouseAdapter used rather than MouseListener
		 * as no need for other MouseListener functions
		 * such as mouseReleased. */
		gui.getFrame().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickX = e.getX();
				mouseClickY = e.getY();
				if(wallPlace == true) {
					walls.add(new Wall(mouseClickX, mouseClickY));
				}
			}
		});
	}
	
	/**
	 * Generates a collection of IntelligentBoids called boids and 
	 * sets their locations randomly around the edges of the Canvas
	 * JPanel that they are drawn too. They are initially set off 
	 * screen so as to give a nicer initial start-up as they all 
	 * flow onto the screen.
	 */
	private void setUpBoids(){

		boids = Collections.synchronizedList(new ArrayList<IntelligentBoid>());

		int x = 0;
		int y = 0;
		int screenOffset = 200;
		for(int i = 0; i < numberOfBoids; i++){
			/* For each boid, choose one of the four
			 * sides of the canvas, top, right, bottom, left.
			 * Then set the boid randomly along that edge but off
			 * screen by up-to 200 pixels. */
			int side = Utils.randomInt(4);	
			switch(side) {
			case 0:
				/* Top side of the screen. */
				x = Utils.randomInt(gui.getCanvas().getWidth());
				y = -Utils.randomInt(screenOffset);
				break;
			case 1:
				/* Right side of the screen. */
				x = gui.getCanvas().getWidth() + Utils.randomInt(screenOffset);
				y = Utils.randomInt(gui.getCanvas().getHeight());
				break;
			case 2:
				/* Bottom side of the screen. */
				x = Utils.randomInt(gui.getCanvas().getWidth());
				y = gui.getCanvas().getHeight() + Utils.randomInt(screenOffset);
				break;
			case 3:
				/* Left side of the screen. */
				x = -Utils.randomInt(screenOffset);
				y = Utils.randomInt(gui.getCanvas().getHeight());
				break;
			}
			boids.add(new IntelligentBoid(gui.getCanvas(), x, y, boidSize));
		}
	}

	
	/**
	 * The main game loop. This updates the location of 
	 * the boids and predators and the un-draws and re-draws
	 * the boids in the new location. The loop the pauses for 
	 * deltaTime, 20 milliseconds. 
	 */
	private void gameLoop(){
		
		System.out.println(gui.getCanvas().getWidth());
		System.out.println(gui.getCanvas().getHeight());

		int deltaTime = 20;
		boolean continueRunning = true;
		
		/* Game loop */
		while (continueRunning){
			
			/* This checks the location of the mouse pointer and 
			 * converts that location to the coordinate system of 
			 * the Canvas. This is used for the avoid mouse feature
			 * of the boids. */
			Point mousePoint = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(mousePoint, gui.getCanvas());
			
			/* The next two sections is updating the position of both the boids
			 * and predators based on the previous frames information. */
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
			
			/* The Canvas is cleared of all objects. */
			gui.getCanvas().clear();
			
			/* All necessary objects are added to the 
			 * Canvas. */
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
			
			/* The Canvas is told to repaint all the 
			 * new objects. */
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
	
	/* This is the entry point to the program. */
	public static void main(String[] args) {
		new FlockingSimulator();
	}

}

package boids;

import drawing.Canvas;
import geometry.Vector;

public class Predator extends IntelligentBoid {

	public Predator(Canvas myCanvas, Vector position) {
		super(myCanvas, position);
		// TODO Auto-generated constructor stub
	}
	
	public Predator(Canvas myCanvas, double xPosition, double yPosition) {
		super(myCanvas, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Predator is going to chase the nearest boid
	 * will group with other predators but will individually 
	 * break off??
	 * 
	 * has a sine wave wiggle to it's movement
	 * is larger and a separate colour to the prey
	 * */

}
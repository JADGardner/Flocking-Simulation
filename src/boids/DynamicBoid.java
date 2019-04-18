/*
 * DynamicBoid.java				18/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package boids;

import drawing.Canvas;
import geometry.Vector;

/**
 * This Class represents a DynamicBoid Object. This adds some 
 * extended functionality to the basic Boid by providing it with 
 * a Velocity field and enabling the updating of it's position on
 * the Canvas.
 * 
 * @author Y3843317
 *
 */
public class DynamicBoid extends Boid {
	private double MILLISECONDS_PER_SECOND = 1000;
	
	/* Max speed of boids in pixels per second. */
	protected int maxSpeed = 200; 
	protected Vector velocity;
	
	/**
	 * Default constructor sets up values for Boid using Vector for position.
	 * 
	 * @param canvas A Canvas object the Boid will draw too.
	 * @param position Vector location in 2D plane
	 * @param size How big the boid will be
	 */
	public DynamicBoid(Canvas canvas, Vector position, int size){
		super(canvas, position, size);
		velocity = new Vector();
		draw();
	}
	
	/**
	 * Constructor sets up values for Boid using double for position.
	 * 
	 * @param canvas A Canvas object the Boid will draw too.
	 * @param xPosition x value of location in 2D plane.
	 * @param yPosition y value of location in 2D plane.
	 * @param size How big the boid will be
	 */
	public DynamicBoid(Canvas canvas, double xPosition, double yPosition, int size){
		super(canvas, xPosition, yPosition, size);
		velocity = new Vector();
		draw();
	}

	/**
	 * The update function calculates the new position of the 
	 * Boid based on its velocity Vector and the time that has passed
	 * since the previous frame.
	 * 
	 * @param time The time between each frame being updated.
	 */
	public void update(double time){
		/* The angle must be set so that the Boid is 
		 * drawn facing the direction of motion. */
		setAngle(velocity.getAngle());
		
		/* A tempVelocityVector is created so as not to
		 * affect the actual velocity when scaled for time. */
		Vector tempVelocityVector = new Vector();
		
		tempVelocityVector.equals(velocity);
		
		/* Velocity is scaled for the amount of time that has passed.
		 * From a speed to a Distance travelled in that time. */
		tempVelocityVector.scale(time/MILLISECONDS_PER_SECOND);
		
		/* The position is then updated by that distance travelled. */
		position.add(tempVelocityVector);
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

}
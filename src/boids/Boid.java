/*
 * Boid.java				18/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package boids;

import drawing.Canvas;
import geometry.*;

/**
 * This Class represents a Boid Object which contains the basic 
 * functionality of a Boid. This includes a position in 2D Space,
 * an angle that it is currently facing and the required methods to
 * draw the Boid using it's position and angle.
 * 
 * @author Y3843317
 *
 */
public class Boid {
	private Canvas canvas;
	
	/* 0 degrees is facing directly to the right. */
	private double currentAngle = 0;
	
	/* The size of each side of the Boid. */
	private int size;
	
	protected Vector position;

	/**
	 * Default constructor sets up values for Boid using Vector for position.
	 * 
	 * @param canvas A Canvas object the Boid will draw too.
	 * @param position Vector location in 2D plane
	 * @param size How big the boid will be
	 */
	public Boid(Canvas canvas, Vector position, int size) {
		this.canvas = canvas;
		this.position = position;
		this.size = size;
	}
	
	/**
	 * Constructor sets up values for Boid using double for position.
	 * 
	 * @param canvas A Canvas object the Boid will draw too.
	 * @param xPosition x value of location in 2D plane.
	 * @param yPosition y value of location in 2D plane.
	 * @param size How big the boid will be
	 */
	public Boid(Canvas canvas, double xPosition, double yPosition, int size){
		this.canvas = canvas;
		position = new Vector(xPosition, yPosition);
		this.size = size;
	}
	
	/**
	 * Draws an equilateral triangle with side length of size. This is 
	 * done by generating LineSegments for each side based on the current
	 * angle and position.
	 */
	public void draw(){
        /* The position is stored in tempPosition as after the generation of 
         * the side using turn and move, there is a small change to the position 
         * Vector due to Math functions having a limited accuracy. So the 
         * position is restored exactly after the LineSegments are generated. */
		Vector tempPosition;
		tempPosition = position;
		
        turn(150);
        createLineSegment(size);
        turn(120);
        createLineSegment(size);
        turn(120);
        createLineSegment(size);
       
        position = tempPosition;
    }
	
	/**
	 * This generates a LineSegment that is sent to the Canvas. Based on
	 * the current location, the current angle and the distance.
	 * 
	 * @param distance The number of pixels to move.
	 */
	public void createLineSegment(double distance) {
		Vector nextPosition;
		LineSegment lines;
		double xLength, yLength;
		double xValue, yValue;

		/* The contribution of the movement in the x direction. */
		xLength = distance*Math.cos(currentAngle);
		
		/* The second x coordinate. */
		xValue = Math.round(position.getX() + xLength);

		/* The contribution of the movement in the y direction. */
		yLength = -distance*Math.sin(currentAngle);

		/* The second y coordinate. */
		yValue = Math.round(position.getY() - yLength);

		nextPosition = new Vector(xValue, yValue);

		/* The line is between the first and second positions. */
		lines = new LineSegment(position, nextPosition);
	
		canvas.drawLineSegment(lines);
		
		position = nextPosition;
		
	}

	/**
	 * Rotates the Boid clockwise by the specified angle in degrees.
	 * 
	 * @param angle The number of degrees to turn.
	 */
	public void turn(double angle) {
		// Positive numbers are clockwise
		currentAngle = currentAngle + Math.toRadians(angle);
		
		/* Keeping it within 360 degrees. */
		if(currentAngle < 0) {
			currentAngle = Math.PI*2 - (-currentAngle);
		}
	}
	
	/**
	 * A Boid is made up of three lines so three 
	 * must be removed to clear that Boid.
	 */
	public void unDraw(){
		canvas.removeMostRecentLine();
		canvas.removeMostRecentLine();
		canvas.removeMostRecentLine();
	}

	public void setPosition(Vector newPosition){
		position.equals(newPosition);
	}
	
	public void setAngle(double i){
		currentAngle = i;
	}
	
	public double getAngle(){
		return currentAngle;
	}
	
	public Vector getPosition() {
		return position;
	}
}

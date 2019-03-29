package boids;

import drawing.Canvas;
import geometry.*;

public class Boid {
	private Canvas myCanvas;
	private double currentAngle = 0;
	
	protected Vector position;

	public Boid(Canvas myCanvas, Vector position) {
		this.myCanvas = myCanvas;
		this.position = position;
	}
	
	public Boid(Canvas myCanvas, double xPosition, double yPosition){
		this.myCanvas = myCanvas;
		position = new Vector(xPosition, yPosition);
	}

	/**
	 * The boid is moved in its current direction for the given number of pixels. 
	 * If the pen is down when the robot moves, a line will be drawn on the floor.
	 * 
	 * @param i The number of pixels to move.
	 */
	public void move(double distance) {
		Vector nextPosition;
		LineSegment lines;
		double xLength, yLength;
		double xValue, yValue;

		xLength = distance*Math.cos(currentAngle);

		xValue = Math.round(position.getX() + xLength);

		yLength = -distance*Math.sin(currentAngle);

		yValue = Math.round(position.getY() - yLength);

		nextPosition = new Vector(xValue, yValue);

		lines = new LineSegment(position, nextPosition);
	
		myCanvas.drawLineSegment(lines);
		
		position = nextPosition;
		
	}

	/**
	 * Rotates the turtle clockwise by the specified angle in degrees.
	 * 
	 * @param i The number of degrees to turn.
	 */
	public void turn(double angle) {
		// Positive numbers are clockwise
		currentAngle = currentAngle + Math.toRadians(angle);
		
		if(currentAngle < 0) {
			currentAngle = Math.PI*2 - (-currentAngle);
		}
	}

	public void draw(){
        /* Draws the basic turtle shape and equilateral triangle with side length 20*/
		Vector tempPosition;
		tempPosition = position;
		
        turn(150);
        move(10);
        turn(120);
        move(10);
        turn(120);
        move(10);
       
        position = tempPosition;
    }
	
	public void unDraw(){
		myCanvas.removeMostRecentLine();
		myCanvas.removeMostRecentLine();
		myCanvas.removeMostRecentLine();
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
	
	public void wrapPosition(double maxX, double maxY){
		if(position.getX() < 0){
			position.setX(maxX);
		}
		
		if (position.getX() > maxX){
			position.setX(0);
		}
		
		if(position.getY() < 0){
			position.setY(maxY);
		} 
		
		if (position.getY()  > maxY){
			position.setY(0);
		}

	}
	
	public Canvas getCanvas() {
		return myCanvas;
	}

}

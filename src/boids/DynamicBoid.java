package boids;

import drawing.Canvas;
import geometry.Vector;

public class DynamicBoid extends Boid {
	protected double MILLISECONDS_PER_SECOND = 1000;
	
	protected int maxSpeed = 200; // max speed of boids in pixels per second
	protected int minSpeed = 20;
	protected Vector velocity;
	
	public DynamicBoid(Canvas myCanvas, Vector position){
		super(myCanvas, position);
		velocity = new Vector();
		draw();
	}
	
	public DynamicBoid(Canvas myCanvas, double xPosition, double yPosition){
		super(myCanvas, xPosition, yPosition);
		velocity = new Vector();
		draw();
	}

	public void update(double time){
		setAngle(velocity.getAngle());
		Vector tempVeloctyVector = new Vector();
		tempVeloctyVector.equals(velocity);
		tempVeloctyVector.scale(time/MILLISECONDS_PER_SECOND);
		position.add(tempVeloctyVector);
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

}
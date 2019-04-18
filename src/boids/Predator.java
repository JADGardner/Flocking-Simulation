package boids;

import java.awt.Point;
import java.util.List;

import drawing.Canvas;
import drawing.Portal;
import drawing.Wall;
import geometry.Vector;

public class Predator extends IntelligentBoid implements IntelligentAgent {
	
	private int predatorPerception = 200;
	
	/**
	 * Default Constructor that creates an Predator of a specific
	 * size and at a location using a Vector.
	 * 
	 * @param canvas A Canvas object the Boid will draw too.
	 * @param position Vector location in 2D plane
	 * @param size How big the boid will be
	 */
	public Predator(Canvas canvas, Vector position, int size) {
		super(canvas, position, size);
	}
	
	/**
	 * Constructor that creates an Predator of a specific
	 * size and at a location using two doubles.
	 * 
	 * @param canvas A Canvas object the Boid will draw too.
	 * @param xPosition x value of location in 2D plane.
	 * @param yPosition y value of location in 2D plane.
	 * @param size How big the boid will be
	 */
	public Predator(Canvas canvas, double xPosition, double yPosition, int size) {
		super(canvas, xPosition, yPosition, size);
	}
	
	/**
	 * 
	 * Uses the following parameters to determine the new
	 * Velocity each frame. The use of Wildcards for the two
	 * lists of Boids is necessary to mix between using IntelligentBoids
	 * or Predators for friendlyBoids or otherBoids.
	 * 
	 * @param friendlyBoids A list of friendly Boids.
	 * @param otherBoids A list of other non-friendly Boids.
	 * @param walls A list of Wall objects.
	 * @param portals A list of Portal objects.
	 * @param maxX The width of the Canvas.
	 * @param maxY The height of the Canvas.
	 * @param mousePoint A Point object with the mouse pointer location.
	 */
	@Override
	public void calculateVelocity(List<? extends DynamicBoid> friendlyBoids, 
			List<? extends DynamicBoid> otherBoids, List<Wall> walls, 
			List<Portal> portals, int maxX, int maxY, Point mousePoint) {
	
		/* Call to the main set of rules that determine basic 
		 * Boid behaviour. */
		cohesionSeparationAlignment(friendlyBoids);
		
		/* Controls the effect of the Portal Objects. */
		Vector portalVector = new Vector();
		
		/* If there are Portals on screen. Make a call
		 * to the method for calculating their effect. */
		if(portals.size() > 0){
			portalVector = portalVector(portals);
		}
		
		velocity.add(cohesionVector);
		velocity.add(seperationVector);
		velocity.add(alignmentVector);
		velocity.add(portalVector);
		velocity.add(boundaryVector(maxX, maxY));
		velocity.add(chaseBoid(otherBoids));
		velocity.add(avoidWalls(walls));
		
		/* Limit the overall magnitude of Velocity otherwise
		 * it would forever increase and the Boids would 
		 * disappear off screen. */
		if(velocity.getMagnitude() > maxSpeed+50) {
			velocity.setMagnitude(maxSpeed+50);
		}
	}
	
	/**
	 * This method returns a velocity Vector that results in the 
	 * Predator chasing the average position of the local Boids in
	 * the list boids.
	 * 
	 * @param boids
	 * @return A velocity Vector.
	 */
	private Vector chaseBoid(List<? extends DynamicBoid> boids) {
		Vector chaseBoid = new Vector();
		Vector distanceApart = new Vector();
		
		/* This is to count the number of Boids to get an average. */
		double countChaseBoid = 0;
		
		synchronized (boids){
			/* For all the Boids in the list get the distance away
			 * relative to the Predator. */
			for (DynamicBoid boid : boids) {
				distanceApart.equals(boid.getPosition());
				distanceApart.sub(this.getPosition()); // included " this. " for readability
				
				
				/* If the distance is less than the perception of the predator.
				 * add the position of that Boid to the chaseBoid Vector and count 
				 * up on countChaseBoid int to keep track. */
				if(distanceApart.getMagnitude() < predatorPerception){
					chaseBoid.add(boid.getPosition());
					countChaseBoid++;
				}
			}
		}
		
		/* If countChaseBoid is zero then no Boids were near by. If it
		 * is greater than zero then the chaseBoid rule has applied and 
		 * needs scaling. */
		if(countChaseBoid > 0){
			chaseBoid.scale(1/countChaseBoid);
		}
		
		/* this.position is then subtracted to get a Vector that points
		 * in the direction the Predator must move to head to the average position. */
		chaseBoid.sub(position);
			
		return chaseBoid;
	}
}

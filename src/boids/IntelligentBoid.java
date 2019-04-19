/*
 * IntelligentBoid.java				18/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package boids;
import java.awt.Point;
import java.util.List;
import drawing.Canvas;
import drawing.Portal;
import drawing.Wall;
import geometry.Vector;

/**
 * This Class represents an IntelligentBoid Object, this is an 
 * extension to the DynamicBoid enabling the Boid to update its
 * own velocity based on information from the world around it.
 * This is an implementation of the popular Craig Reynolds program
 * Boids and demonstrates the emergent behaviour that occurs with a 
 * few simple rules.
 * 
 * @author Y3843317
 *
 */
public class IntelligentBoid extends DynamicBoid implements IntelligentAgent {
	protected int perceptionRadius = 100;
	protected int seperationRadius = 20;

	private double alignmentConstant = 0.120;
	private double cohesionConstant = 0.01;
	private double seperationConstant = 1;
	private double avoidMouseConstant = 100;
	private double windEffectConstant = 10;

	private boolean cohesionOn = true;
	private boolean separationOn = true;
	private boolean alignmentOn = true;
	private boolean boundryOn = true;
	private boolean mouseAvoidOn = true;
	private boolean windOn = false;
	
	protected boolean maxSpeedOn = true;
	
	protected Vector cohesionVector = new Vector();
	protected Vector seperationVector = new Vector();
	protected Vector alignmentVector = new Vector();

	/**
	 * Default Constructor that creates an IntelligentBoid of a specific
	 * size and at a location using a Vector.
	 * 
	 * @param canvas A Canvas object the Boid will draw too.
	 * @param position Vector location in 2D plane
	 * @param size How big the boid will be
	 */
	public IntelligentBoid(Canvas canvas, Vector position, int size) {
		super(canvas, position, size);
	}

	/**
	 * Constructor that creates an IntelligentBoid of a specific
	 * size and at a location using two doubles.
	 * 
	 * @param canvas A Canvas object the Boid will draw too.
	 * @param xPosition x value of location in 2D plane.
	 * @param yPosition y value of location in 2D plane.
	 * @param size How big the boid will be
	 */
	public IntelligentBoid(Canvas canvas, double xPosition, double yPosition, int size){
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
			List<? extends DynamicBoid> otherBoids, List<Wall> walls, List<Portal> portals, 
			int maxX, int maxY, Point mousePoint) {
		
		/* Call to the main set of rules that determine basic 
		 * Boid behaviour. */
		cohesionSeparationAlignment(friendlyBoids);
		
		/* Keeping the Boid on screen. */
		Vector boundaryVector = boundaryVector(maxX, maxY);
		
		/* Avoiding the mouse location. */
		Vector avoidMouseVector = avoidMouse(mousePoint);
		
		/* Controls the effect of the Portal Objects. */
		Vector portalVector = new Vector();
		
		/* If there are Portals on screen. Make a call
		 * to the method for calculating their effect. */
		if(portals.size() > 0){
			portalVector = portalVector(portals);
		}

		/* If the functionality for each respective rule is 
		 * turned on then add the result of that rules velocity to
		 * the overall velocity. */
		if(cohesionOn) {
			velocity.add(cohesionVector);
		}

		if(separationOn) {
			velocity.add(seperationVector);
		}

		if(alignmentOn) {
			velocity.add(alignmentVector);
		}

		if(boundryOn) {
			velocity.add(boundaryVector);
		}

		if(mouseAvoidOn) {
			velocity.add(avoidMouseVector);
		}
		
		if(windOn) {
			velocity.add(windVector(maxX, maxY));
		}
		
		
		/* Always add the velocity from these rules, 
		 * the velocity will just be zero if there 
		 * are none on screen. */
		velocity.add(portalVector);
		
		velocity.add(fleePredator(otherBoids));
		
		velocity.add(avoidWalls(walls));

		/* Limit the overall magnitude of Velocity otherwise
		 * it would forever increase and the Boids would 
		 * disappear off screen. */
		if(maxSpeedOn) {
			if(velocity.getMagnitude() > maxSpeed) {
				velocity.setMagnitude(maxSpeed);
			}
		}
	}

	/**
	 * This is the method for the implementation of the three most basic rules of 
	 * Craig Reynolds program boids. Cohesion, Separation and Alignment. This is 
	 * done in one method so that the list of Boids is only looped through once for
	 * all three rules to improve efficiency. Again the use of Wildcards for the list 
	 * is necessary so this method can be used by both IntelligentBoids and Predators. 
	 * 
	 * @param allBoids A list of DynamicBoids to apply the rules too.
	 */
	protected void cohesionSeparationAlignment(List<? extends DynamicBoid> allBoids){
		
		/* These are to count the number of Boids to get an average. */
		double countCohesion = 0;
		double countAlignment = 0;

		Vector distanceApart = new Vector();

		/* Setting the global Vectors back to zero. */
		cohesionVector.scale(0.0);
		alignmentVector.scale(0.0);
		seperationVector.scale(0.0);

		synchronized (allBoids){
			/* Loop through every Boid in the list. */
			for (DynamicBoid otherBoid : allBoids) {
				
				/* The distance between this. and otherBoid is the
				 * others.position - this.position. */
				distanceApart.equals(otherBoid.getPosition());
				distanceApart.sub(this.getPosition());

				/* If the Boid in the List is not itself and the distanceApart is less
				 * than the radius each Boid can see. */
				if(otherBoid != this && distanceApart.getMagnitude() < perceptionRadius){
					/* This is Cohesion so +1 to the countCohesion and add the position
					 * of the other Boid to the cohesion Vector. */
					countCohesion++;
					cohesionVector.add(otherBoid.getPosition());
				}

				if(otherBoid != this && distanceApart.getMagnitude() < perceptionRadius){
					/* This is Alignment so +1 to the countAlignment and add the velocity
					 * of the other Boid to the alignment Vector. */
					countAlignment++;
					alignmentVector.add(otherBoid.getVelocity());
				}

				if(otherBoid != this && distanceApart.getMagnitude() < seperationRadius) {
					/* Move this. as far as it currently is close to the other Boid. */
					seperationVector.sub(distanceApart);	
				}
			}
		}

		/* If countCohesion is zero then no Boids were near by. If it
		 * is greater than zero then the Cohesion rule has applied and 
		 * needs scaling. */
		if(countCohesion > 0){
			/* The cohesionVector is currently the total of all the
			 * position Vectors of all the nearby Boids. This needs
			 * dividing by countCohesion to get the average position. */
			cohesionVector.scale(1/countCohesion);
		}
		
		/* this.position is then subtracted to get a Vector that points
		 * in the direction the Boid must move to head to the average position. */
		cohesionVector.sub(position);
		
		/* The Vector is then scaled by cohesionConstant to allow 
		 * control over how much of an effect this Vector has. */
		cohesionVector.scale(cohesionConstant);

		/* Separation is also scaled to allow control over effect. */
		seperationVector.scale(seperationConstant);

		/* If countAlignment is zero then no Boids were near by. If it
		 * is greater than zero then the Alignment rule has applied and 
		 * needs scaling. */
		if(countAlignment > 0){
			/* The alignmentVector is currently the total of all the
			 * velocity Vectors of all the nearby Boids. This needs
			 * dividing by countAlignment to get the average velocity. */
			alignmentVector.scale(1/countAlignment);
		}

		/* this.velocity is then subtracted to get a Vector that points
		 * in the direction the Boid must move to be heading
		 * in the to the average velocity. */
		alignmentVector.sub(velocity);
		
		/* The Vector is then scaled by alignmentConstant to allow 
		 * control over how much of an effect this Vector has. */
		alignmentVector.scale(alignmentConstant);
	}
	

	/**
	 * This returns a velocity Vector pushing the Boid
	 * back onto the screen.
	 * 
	 * @param maxX The width of the canvas.
	 * @param maxY The height of the canvas.
	 * @return A velocity Vector.
	 */
	protected Vector boundaryVector(int maxX, int maxY) {
		
		/* The control variables.*/
		int scale = 20;
		double scalingFactor = 0.5;
		
		Vector bounaryVector = new Vector();

		/* If the Boids current position is less than
		 * zero keep adding a velocity Vector of size
		 * scale to its velocity. This will slowly and 
		 * smoothly push it back on screen. */
		if(position.getX() < 0){
			bounaryVector.setX(scale);
		}

		if (position.getX() > maxX){
			bounaryVector.setX(-scale);
		}

		if(position.getY() < 0){
			bounaryVector.setY(scale);
		}

		if (position.getY() > maxY){
			bounaryVector.setY(-scale);
		}

		bounaryVector.scale(scalingFactor);
		return bounaryVector;
	}

	/**
	 * This returns a velocity Vector pushing the Boid
	 * away from the tip of the users mouse. 
	 * 
	 * @param mousePoint A Point object that contains the location of the mouse.
	 * @return A velocity Vector.
	 */
	private Vector avoidMouse(Point mousePoint) {
		Vector distance = new Vector();
		Vector avoidMouseVector = new Vector();

		/* Create a Vector object out of the mousePoint Point object. */
		Vector mousePointVector = new Vector(mousePoint.getX(), mousePoint.getY());

		/* Get the distance between the Boid and the mouse pointer. */
		distance.equals(mousePointVector);
		distance.sub(this.getPosition());

		/* If the mouse is too close to the Boid move with a velocity
		 * in the opposite direction. */
		if(distance.getMagnitude() < avoidMouseConstant) {
			avoidMouseVector.sub(distance);
		}

		return avoidMouseVector;
	}

	/**
	 * This returns a velocity Vector pushing the Boid
	 * around in a circle anti-clockwise. 
	 * 
	 * @param width The width of the canvas.
	 * @param height The height of the canvas.
	 * @return A velocity Vector.
	 */
	private Vector windVector(int width, int height) {
		Vector windVector = new Vector();
		Vector adjustedPositionVector = new Vector();
		
		/* adjustedPositionVector creates a new set of axis with 
		 * the origin in the middle of the canvas. */
		adjustedPositionVector.setX(position.getX() - 0.5*width);
		adjustedPositionVector.setY(position.getY() - 0.5*height);
		
		/* As the Boid moves to the around moves away from the origin 
		 * in the x direction the velocity in the y direction increases 
		 * and vis-versa. */
		windVector.setY(-adjustedPositionVector.getX());
		windVector.setX(adjustedPositionVector.getY());
		
		windVector.scale(windEffectConstant/adjustedPositionVector.getMagnitude());

		return windVector;
	}
	
	/**
	 * This returns a velocity Vector either sucking 
	 * in a Boid into portals(0) or away from portals(1).
	 * This can only handle a list of two Portals.
	 * 
	 * @param portals A list of Portal objects.
	 * @return A velocity Vector.
	 */
	protected Vector portalVector(List<Portal> portals){
		Vector portalVector = new Vector();
		
		/* Initialising and setting the position Vectors for
		 * both Portals. */
		Vector inputPortalPosition = new Vector();
		Vector outputPortalPosition = new Vector();
		
		int inputPortalStrength = 150;
		int inputPortalCentre = 10;
		
		synchronized (portals){
			inputPortalPosition.setX(portals.get(0).getCenterX());
			inputPortalPosition.setY(portals.get(0).getCenterY());
			
			outputPortalPosition.setX(portals.get(1).getCenterX());
			outputPortalPosition.setY(portals.get(1).getCenterY());
		}
		
		/* Calculating distance from input and output portals relative 
		 * to Boids current position. */
		Vector distanceFromInputPortal = new Vector();
		Vector distanceFromOutputPortal = new Vector();
		
		distanceFromInputPortal.equals(inputPortalPosition);
		distanceFromInputPortal.sub(this.getPosition());
		
		distanceFromOutputPortal.equals(outputPortalPosition);
		distanceFromOutputPortal.sub(this.getPosition());
		
		
		/* If the distance away from the input portal is less than inputPortalStrength then create a 
		 * velocity Vector that pulls the Boid towards the centre of the Portal. 
		 * If the Boid is at the centre then set its current position to be 
		 * the centre of the output portal. */
		if(distanceFromInputPortal.getMagnitude() < inputPortalStrength && distanceFromInputPortal.getMagnitude() > inputPortalCentre){
			portalVector.equals(inputPortalPosition);
			portalVector.sub(position);
			portalVector.scale(10.0);
		} else if (distanceFromInputPortal.getMagnitude() < 10){
			this.setPosition(outputPortalPosition);
		}
		
		/* If the distance away from the output portal is less than twice the 
		 * radius of the portal then push the Boid away from the portal.  */
		if(distanceFromOutputPortal.getMagnitude() < portals.get(1).getHeight()){
			portalVector.sub(distanceFromOutputPortal);
		}
		
		return portalVector;
	}
	
	/**
	 * This returns a velocity Vector pushing the Boid
	 * away from nearby predators. 
	 * 
	 * @param predators
	 * @return A velocity Vector.
	 */
    private Vector fleePredator(List<? extends DynamicBoid> predators) {
		Vector fleePredatorVector = new Vector();
		Vector distanceApart = new Vector();
		
		int predatorPerceptionRadius = 80;
		double predatorScalingFactor = 20.0;
		
		synchronized (predators){
			/* For all the predators calculate the distance away from
			 * this Boid. */
			for (DynamicBoid predator : predators) {
				distanceApart.equals(predator.getPosition());
				distanceApart.sub(this.getPosition());
				
				/* If that distance is less than predatorPerceptionRadius then move 
				 * away in the other direction. */
				if(distanceApart.getMagnitude() < predatorPerceptionRadius) {
					fleePredatorVector.sub(distanceApart);
				}
			}
		}
		
		
		fleePredatorVector.scale(predatorScalingFactor);
		
		return fleePredatorVector;
	}
   
    /**
	 * This returns a velocity Vector pushing the Boid
	 * away from Walls.
	 * 
     * @param walls A list of Wall objects.
     * @return A velocity Vector.
     */
    protected Vector avoidWalls(List<Wall> walls) {
	   Vector avoidWallsVector = new Vector();
	   Vector wallPositionVector = new Vector();
	   Vector distanceApart = new Vector();
	   
	   /* A high scaling factor means this has a very powerful
	    * effect and the Boids don't end up inside a Wall. */
	   double wallScalingFactor = 20.0;
		
	   synchronized (walls){
		   /* For all the wall objects in walls, create a 
		    * position Vector and calculate the distance away
		    * from this Boid. */
		   for (Wall wall : walls) {
			   wallPositionVector.setX(wall.getCenterX());
			   wallPositionVector.setY(wall.getCenterY());
			   distanceApart.equals(wallPositionVector);
			   distanceApart.sub(this.getPosition());
				
			   /* If the distance is less than the width of 
			    * the wall then move away in the other direction. */
			   if(distanceApart.getMagnitude() < wall.getWidth()) {
				   avoidWallsVector.sub(distanceApart);
			   }
			}
		}
		
	   avoidWallsVector.scale(wallScalingFactor);
	   
	   return avoidWallsVector;
    }

	public void setAlignment(double alignment) {
		this.alignmentConstant = alignment;
	}
	
	public boolean isAlignmentOn() {
		return alignmentOn;
	}

	public void setAlignmentOn(boolean alignmentOn) {
		this.alignmentOn = alignmentOn;
	}

	public void setCohesion(double cohesion) {
		this.cohesionConstant = cohesion;
	}
	
	public boolean isCohesionOn() {
		return cohesionOn;
	}

	public void setCohesionOn(boolean cohesionOn) {
		this.cohesionOn = cohesionOn;
	}

	public void setSeparation(double seperation) {
		this.seperationConstant = seperation;
	}
	
	public boolean isSeparationOn() {
		return separationOn;
	}
	
	public void setSeparationOn(boolean seperationOn) {
		this.separationOn = seperationOn;
	}

	public void setAvoidMouseConstant(double avoidMouseConstant) {
		this.avoidMouseConstant = avoidMouseConstant;
	}
	
	public boolean isMouseAvoidOn() {
		return mouseAvoidOn;
	}

	public void setMouseAvoidOn(boolean mouseAvoidOn) {
		this.mouseAvoidOn = mouseAvoidOn;
	}

	public void setWindOn() {
		windOn = !windOn;
	}

	public void setMaxSpeedOn(boolean maxSpeedOn) {
		this.maxSpeedOn = maxSpeedOn;
	}
	
	
	
	
}

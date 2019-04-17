package boids;
import java.awt.Point;
import java.util.List;
import drawing.Canvas;
import drawing.Portal;
import geometry.Vector;


public class IntelligentBoid extends DynamicBoid implements IntelligentAgent {
	protected int perceptionRadius = 100;
	protected double seperationRadius = 20;

	protected double alignmentConstant = 0.120; //0.125
	protected double cohesionConstant = 0.01;
	protected double seperationConstant = 1;
	protected double avoidMouseConstant = 100;
	protected double tendToPlaceConstant = 0.01;

	protected Vector cohesionVector = new Vector();
	protected Vector seperationVector = new Vector();
	protected Vector alignmentVector = new Vector();

	protected boolean cohesionOn = true;
	protected boolean seperationOn = true;
	protected boolean alignmentOn = true;
	protected boolean boundryOn = true;
	protected boolean mouseAvoidOn = true;
	protected boolean portalVector = false;


	// Constructor
	public IntelligentBoid(Canvas myCanvas, Vector position, int size) {
		super(myCanvas, position, size);
	}

	// Constructor
	public IntelligentBoid(Canvas myCanvas, double xPosition, double yPosition, int size){
		super(myCanvas, xPosition, yPosition, size);
	}
	
	@Override
	public void calculateVelocity(List<? extends DynamicBoid> friendlyBoids, 
			List<? extends DynamicBoid> otherBoids, List<Portal> portals, int maxX, int maxY, Point mousePoint) {

		cohesionSperationAlignment(friendlyBoids);
		Vector boundaryVector = boundaryVector(maxX, maxY);
		Vector avoidMouseVector = avoidMouse(mousePoint);
		Vector portalVector = new Vector();
		
		if(portals.size() > 0){
			portalVector = portalVector(portals);
		}


		if(cohesionOn) {
			velocity.add(cohesionVector);
		}

		if(seperationOn) {
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
		
		velocity.add(portalVector);
		
		velocity.add(fleePredator(otherBoids));

		if(velocity.getMagnitude() > maxSpeed) {
			velocity.setMagnitude(maxSpeed);
		}

	}

	
	protected void cohesionSperationAlignment(List<? extends DynamicBoid> allBoids){
		double countCohesion = 0;
		double countAlignment = 0;

		Vector distanceApart = new Vector();

		cohesionVector.scale(0.0);
		alignmentVector.scale(0.0);
		seperationVector.scale(0.0);

		synchronized (allBoids){
			for (DynamicBoid otherBoid : allBoids) {
				distanceApart.equals(otherBoid.getPosition());
				distanceApart.sub(this.getPosition()); // included " this. " for readability

				if(otherBoid != this && distanceApart.getMagnitude() < perceptionRadius){
					countCohesion++;
					cohesionVector.add(otherBoid.getPosition());
				}

				if(otherBoid != this && distanceApart.getMagnitude() < perceptionRadius){
					countAlignment++;
					alignmentVector.add(otherBoid.getVelocity());
				}

				if(otherBoid != this){
					if(distanceApart.getMagnitude() < seperationRadius) {
						seperationVector.sub(distanceApart);
					}
				}
			}
		}

		if(countCohesion > 0){
			cohesionVector.scale(1/countCohesion);
		}

		cohesionVector.sub(position);
		cohesionVector.scale(cohesionConstant);

		seperationVector.scale(seperationConstant);


		if(countAlignment > 0){
			alignmentVector.scale(1/countAlignment);
		}

		alignmentVector.sub(velocity);
		alignmentVector.scale(alignmentConstant);
	}
	

	protected Vector boundaryVector(int maxX, int maxY) {
		int scale = 20;
		double scalingFactor = 0.5;
		Vector bounaryVector = new Vector();

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

	private Vector avoidMouse(Point mousePoint) {
		Vector distance = new Vector();
		Vector avoidMouseVector = new Vector();

		Vector mousePointVector = new Vector(mousePoint.getX(), mousePoint.getY());

		distance.equals(mousePointVector);
		distance.sub(this.getPosition()); // included " this. " for readability

		if(distance.getMagnitude() < avoidMouseConstant) {
			avoidMouseVector.sub(distance);
		}


		return avoidMouseVector;
	}

	private Vector tendToPlace(Vector location) {
		Vector attractionVector = new Vector();

		attractionVector.equals(location);
		attractionVector.sub(position);
		attractionVector.scale(tendToPlaceConstant);

		return attractionVector;
	}
	
	protected Vector portalVector(List<Portal> portals){
		Vector portalVector = new Vector();
		
		Vector inputPortalPosition = new Vector();
		Vector outputPortalPosition = new Vector();
		
		synchronized (portals){
			inputPortalPosition.setX(portals.get(0).getCenterX());
			inputPortalPosition.setY(portals.get(0).getCenterY());
			outputPortalPosition.setX(portals.get(1).getCenterX());
			outputPortalPosition.setY(portals.get(1).getCenterY());
		}
		
		Vector distanceFromInputPortal = new Vector();
		Vector distanceFromOutputPortal = new Vector();
		
		distanceFromInputPortal.equals(inputPortalPosition);
		distanceFromInputPortal.sub(this.getPosition()); // included " this. " for readability
		
		distanceFromOutputPortal.equals(outputPortalPosition);
		distanceFromOutputPortal.sub(this.getPosition()); // included " this. " for readability
		
		if(distanceFromInputPortal.getMagnitude() < 150 && distanceFromInputPortal.getMagnitude() > 10){
			portalVector.equals(inputPortalPosition);
			portalVector.sub(position);
			portalVector.scale(10.0);
		} else if (distanceFromInputPortal.getMagnitude() < 10){
			this.setPosition(outputPortalPosition);
		}
		
		if(distanceFromOutputPortal.getMagnitude() < portals.get(1).getHeight()){
			portalVector.sub(distanceFromOutputPortal);
		}
		
		return portalVector;
	}
	
   private Vector fleePredator(List<? extends DynamicBoid> predators) {
		Vector fleePredatorVector = new Vector();
		Vector distanceApart = new Vector();
		
		synchronized (predators){
			for (DynamicBoid predator : predators) {
				distanceApart.equals(predator.getPosition());
				distanceApart.sub(this.getPosition()); // included " this. " for readability
				
				if(distanceApart.getMagnitude() < 80) {
					fleePredatorVector.sub(distanceApart);
				}
			}
		}
		
		fleePredatorVector.scale(20.0);
		
		return fleePredatorVector;
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

	public void setSeperation(double seperation) {
		this.seperationConstant = seperation;
	}
	
	public boolean isSeperationOn() {
		return seperationOn;
	}

	public void setSeperationOn(boolean seperationOn) {
		this.seperationOn = seperationOn;
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





}

package boids;
import java.awt.Point;
import java.util.List;
import drawing.Canvas;
import geometry.Vector;


public class IntelligentBoid extends DynamicBoid {
	private int perceptionRadius = 100; 
	private double seperationRadius = 20;
	
	private double alignmentConstant = 0.120; //0.125
	private double cohesionConstant = 0.01;
	private double seperationConstant = 1;
	private double avoidMouseConstant = 100;
	
	private Vector cohesionVector = new Vector();
	private Vector seperationVector = new Vector();
	private Vector alignmentVector = new Vector();


	// Constructor
	public IntelligentBoid(Canvas myCanvas, Vector position) {
		super(myCanvas, position);
	}
	
	// Constructor
	public IntelligentBoid(Canvas myCanvas, double xPosition, double yPosition){
		super(myCanvas, xPosition, yPosition);
	}
	
	public void calculateVelocity(List<IntelligentBoid> allBoids, int maxX, int maxY, Point mousePoint) {
		
		cohesionSperationAlignment(allBoids);
		Vector boundaryVector = boundaryVector(allBoids, maxX, maxY);
		Vector avoidMouseVector = avoidMouse(mousePoint);
		
		velocity.add(cohesionVector);
		velocity.add(seperationVector);
		velocity.add(alignmentVector);
		velocity.add(boundaryVector);
		velocity.add(avoidMouseVector);
		
		if(velocity.getMagnitude() > maxSpeed) {
			velocity.setMagnitude(maxSpeed);
		}
		/*
		else if (velocity.getMagnitude() < minSpeed){
			velocity.setMagnitude(minSpeed);
		}*/
		
	}
	
	private void cohesionSperationAlignment(List<IntelligentBoid> allBoids){
		double countCohesion = 0;
		double countAlignment = 0;
		
		Vector distanceApart = new Vector();
		
		cohesionVector.scale(0.0);
		alignmentVector.scale(0.0);
		seperationVector.scale(0.0);
		
		synchronized (allBoids){
			for (IntelligentBoid otherBoid : allBoids) {
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
	
	private Vector boundaryVector(List<IntelligentBoid> allBoids, int maxX, int maxY) {
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

	public void setAlignment(double alignment) {
		this.alignmentConstant = alignment;
	}

	public void setCohesion(double cohesion) {
		this.cohesionConstant = cohesion;
	}

	public void setSeperation(double seperation) {
		this.seperationRadius = seperation;
	}
	
	public void setAvoidMouseConstant(double avoidMouseConstant) {
		this.avoidMouseConstant = avoidMouseConstant;
	}

}

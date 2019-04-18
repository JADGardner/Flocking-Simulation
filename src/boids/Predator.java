package boids;

import java.awt.Point;
import java.util.List;

import drawing.Canvas;
import drawing.Portal;
import drawing.Wall;
import geometry.Vector;

public class Predator extends IntelligentBoid implements IntelligentAgent {
	
	public Predator(Canvas myCanvas, Vector position, int size) {
		super(myCanvas, position, size);
	}
	
	public Predator(Canvas myCanvas, double xPosition, double yPosition, int size) {
		super(myCanvas, xPosition, yPosition, size);
	}
	
	@Override
	public void calculateVelocity(List<? extends DynamicBoid> friendlyBoids, 
			List<? extends DynamicBoid> otherBoids, List<Wall> walls, 
			List<Portal> portals, int maxX, int maxY, Point mousePoint) {
	
		cohesionSeparationAlignment(friendlyBoids);
		
		Vector portalVector = new Vector();
		
		if(portals.size() > 0){
			portalVector = portalVector(portals);
		}
		
		velocity.add(cohesionVector);
		velocity.add(seperationVector);
		velocity.add(alignmentVector);
		velocity.add(portalVector);
		velocity.add(boundaryVector(maxX, maxY));
		velocity.add(chaceBoid(otherBoids));
		velocity.add(avoidWalls(walls));
		
		if(velocity.getMagnitude() > maxSpeed+50) {
			velocity.setMagnitude(maxSpeed+50);
		}
	}
	
	private Vector chaceBoid(List<? extends DynamicBoid> boids) {
		Vector chaceBoid = new Vector();
		Vector distanceApart = new Vector();
		double countChaceBoid = 0;
		
		synchronized (boids){
			for (DynamicBoid boid : boids) {
				distanceApart.equals(boid.getPosition());
				distanceApart.sub(this.getPosition()); // included " this. " for readability
				
				if(distanceApart.getMagnitude() < 200){
					chaceBoid.add(boid.getPosition());
					countChaceBoid++;
				}
			}
		}
		
		if(countChaceBoid > 0){
			chaceBoid.scale(1/countChaceBoid);
		}
		
		chaceBoid.sub(position);
			
		return chaceBoid;
	}
}

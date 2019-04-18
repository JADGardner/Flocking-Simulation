/*
 * IntelligentAgent.java				18/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package boids;

import java.awt.Point;
import java.util.List;

import drawing.Portal;
import drawing.Wall;

/**
 * This interface represents an IntelligentAgent it must be able to calculate 
 * a new Velocity based on information within the world.
 * 
 * @author Y3843317
 *
 */
public interface IntelligentAgent {

	/**
	 * 
	 * Should use the following parameters to determine the new
	 * Velocity each frame.
	 * 
	 * @param friendlyBoids A list of friendly Boids.
	 * @param otherBoids A list of other non-friendly Boids.
	 * @param walls A list of Wall objects.
	 * @param portals A list of Portal objects.
	 * @param maxX The width of the Canvas.
	 * @param maxY The height of the Canvas.
	 * @param mousePoint A Point object with the mouse pointer location.
	 */
	public void calculateVelocity(List<? extends DynamicBoid> friendlyBoids, 
			List<? extends DynamicBoid> otherBoids, List<Wall> walls,
			List<Portal> portals, int maxX, int maxY, Point mousePoint);
		
	}


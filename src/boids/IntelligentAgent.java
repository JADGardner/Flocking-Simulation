package boids;

import java.awt.Point;
import java.util.List;

import drawing.Portal;
import drawing.Wall;

public interface IntelligentAgent {

	public void calculateVelocity(List<? extends DynamicBoid> friendlyBoids, 
			List<? extends DynamicBoid> otherBoids, List<Wall> walls,
			List<Portal> portals, int maxX, int maxY, Point mousePoint);
		
	}


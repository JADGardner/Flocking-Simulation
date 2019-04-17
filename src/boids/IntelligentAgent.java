package boids;

import java.awt.Point;
import java.util.List;

import drawing.Portal;

public interface IntelligentAgent {

	public void calculateVelocity(List<? extends DynamicBoid> friendlyBoids, 
			List<? extends DynamicBoid> otherBoids, List<Portal> portals, int maxX, int maxY, Point mousePoint);
		
	}


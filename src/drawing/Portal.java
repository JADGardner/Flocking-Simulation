/*
 * Portal.java				18/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package drawing;

import java.awt.geom.Ellipse2D;

/**
 * This Class represents a Portal Object that is a circle 
 * of radius 50 that can have its x and y position set.
 * 
 * @author Y3843317
 *
 */
public class Portal extends Ellipse2D.Double{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Portal(){
		super();
		height = 100;
		width = 100;
	}
	
	public Portal(int xPos, int yPos){
		super();
		height = 100;
		width = 100;
		x = xPos;
		y = yPos;
	}
	
	public void setXPosition(double xPosition){
		x = xPosition;
	}
	
	public void setYPosition(double yPosition){
		y = yPosition;
	}
	
}

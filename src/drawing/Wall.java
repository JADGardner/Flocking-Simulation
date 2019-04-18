/*
 * Wall.java				18/04/2019
 * Version: 1.0
 * Programmer: Y3843317
 * Company: University of York
 * 
 */

package drawing;

import java.awt.geom.Rectangle2D;

/**
 * This Class represents a Wall Object that is a square 
 * of size 50 that can have its x and y position set.
 * 
 * @author Y3843317
 *
 */
public class Wall extends Rectangle2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor, creates a Rectangle2D.Double of 
	 * width and height 50 with Position 0, 0.
	 */
	public Wall(){
		super();
		height = 50;
		width = 50;
	}
	
	/**
	 * Constructor, creates a Rectangle2D.Double 
	 * perfectly round with radius 50 with Position 
	 * xPos and yPos.
	 * 
	 * @param xPos X position of top left corner.
	 * @param yPos Y position of top left corner.
	 */
	public Wall(int xPos, int yPos){
		super();
		height = 50;
		width = 50;
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

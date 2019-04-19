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
	
	/**
	 * Default constructor, creates a Ellipse2D.Double 
	 * perfectly round with radius 50 with Position 
	 * 0, 0.
	 */
	public Portal(){
		super();
		height = 100;
		width = 100;
	}
	
	/**
	 * Constructor, creates a Ellipse2D.Double 
	 * perfectly round with radius 50 with Position 
	 * xPos and yPos.
	 * 
	 * @param xPos X position of centre.
	 * @param yPos Y position of centre.
	 */
	public Portal(int xPos, int yPos){
		super();
		height = 100;
		width = 100;
		x = xPos - width/2;
		y = yPos - height/2;
	}
	
	public void setXPosition(double xPosition){
		x = xPosition;
	}
	
	public void setYPosition(double yPosition){
		y = yPosition;
	}
	
}

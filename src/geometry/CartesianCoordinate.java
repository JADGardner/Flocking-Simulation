/**
 * CartesianCoordinate.java 				18/04/2019
 * Version: 1.0
 * Programmers: Y3843317
 * Company: University of York
 * 
 * This contains the basic functionality of a
 * standard point on a Cartesian plane.
 * 
 */

package geometry;

/**
 * The CartesianCoordinate has an x and y value
 * that represent a position in a 2D space. 
 * These can be accessed using getters and setters.
 * 
 * @author Y3843317
 *
 */
public class CartesianCoordinate {
	protected double xValue;
	protected double yValue; 
	
	public CartesianCoordinate(double x, double y){
		xValue = x;
		yValue = y;
	}
	
	public double getX() {
		return xValue;
	}

	public double getY() {
		return yValue;
	}
	
	public void setX(double x){
		xValue = x;
	}
	
	public void setY(double y){
		yValue = y;
	}
	
	public String toString(){
		return "(xValue = " + xValue + " yValue = " + yValue + ")"; 		
	}
}


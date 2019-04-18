/**
 * LineSegment.java 				18/04/2019
 * Version: 1.0
 * Programmers: Y3843317
 * Company: University of York
 * 
 */

package geometry;

/**
 * A Vector is an extension of the CartesianCoordiante
 * Class. It provides an easier and simplified interface 
 * for performing many standard vector operations. 
 * 
 * @author Y3843317
 *
 */
public class Vector extends CartesianCoordinate {

	/**
	 * Default constructor creates a Vector zero magnitude.
	 */
	public Vector(){
		super(0, 0);
	}
	
	/**
	 * Constructor that creates a Vector of specific x
	 * and y values.
	 * 
	 * @param xValue X Position.
	 * @param yValue Y Position.
	 */
	public Vector(double xValue, double yValue) {
		super(xValue, yValue);
	}
	
	/* Uses the Pythagorean Theorem to return the 
	 * size of the Vector. */
	public double getMagnitude(){
		return Math.sqrt(xValue*xValue + yValue*yValue);
	}
	
	/* atan2 is used to account for which quadrant 
	 * of the Cartesian plane the vector is in. */
	public double getAngle(){
		return Math.atan2(yValue, xValue);
	}
	
	
	public void add(Vector v){
		xValue = xValue + v.getX();
		yValue = yValue + v.getY();
	}
	
	public void sub(Vector v){
		xValue = xValue - v.getX();
		yValue = yValue - v.getY();
	}
	
	
	public void equals(Vector v) {
		xValue = v.getX();
		yValue = v.getY();
	}
	
	public void scale(Double scalingFactor) {
		xValue = xValue*scalingFactor;
		yValue = yValue*scalingFactor;
	}
	
	/* To set a Vectors magnitude but maintain its 
	 * direction. Divide by its current magnitude 
	 * to make it a unit Vector and then multiply 
	 * by the desired length.*/
	public void setMagnitude(double magnitude) {
		xValue = (xValue/getMagnitude()) * magnitude;
		yValue = (yValue/getMagnitude()) * magnitude;
	}

	@Override
	public String toString() {
		return "Vector [xValue=" + xValue + ", yValue=" + yValue + "]";
	}
	
}

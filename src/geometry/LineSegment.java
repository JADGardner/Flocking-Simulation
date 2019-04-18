/**
 * LineSegment.java 				18/04/2019
 * Version: 1.0
 * Programmers: Y3843317
 * Company: University of York
 * 
 */

package geometry;

/**
 * A LineSegment has two CartesianCoordinates
 * enabling the drawing of a Line between them.
 * The Length of the line, or distance between the
 * two points, is calculated using standard
 * trigonometry. 
 * 
 * @author Y3843317
 *
 */
public class LineSegment {
	private CartesianCoordinate startPoint;
	private CartesianCoordinate endPoint;
	
	/**
	 * Default constructor for the LineSegment, creates a LineSegment from 
	 * two CartesianCoordinates.
	 * 
	 * @param startPoint Start of line.
	 * @param endPoint End of line.
	 */
	public LineSegment(CartesianCoordinate startPoint, CartesianCoordinate endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public CartesianCoordinate getStartPoint() {
		return startPoint;
	}

	public CartesianCoordinate getEndPoint() {
		return endPoint;
	}
	
	public double length(){
		/* Get the x and y distances between the two points and 
		 * use the Pythagorean Theorem to calculate the
		 * length of the total distance. */
		double xLength = startPoint.getX() - endPoint.getX();
		double yLength = startPoint.getY() - endPoint.getY();
		return Math.sqrt(xLength*xLength + yLength*yLength);
	}
	
	public String toString(){
		return "(Coordiante_1 = " + startPoint + 
				"\nCoordinate_2 = " + endPoint + ")"; 		
	}
	
}

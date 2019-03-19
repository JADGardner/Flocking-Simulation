package geometry;

public class LineSegment {
	private CartesianCoordinate startPoint;
	private CartesianCoordinate endPoint;
	
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
		double xLength = startPoint.getX() - endPoint.getX();
		double yLength = startPoint.getY() - endPoint.getY();
		return Math.sqrt(xLength*xLength + yLength*yLength);
	}
	
	public String toString(){
		return "(Coordiante_1 = " + startPoint + 
				"\nCoordinate_2 = " + endPoint + ")"; 		
	}
	
}

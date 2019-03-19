package geometry;

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


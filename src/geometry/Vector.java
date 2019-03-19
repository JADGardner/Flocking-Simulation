package geometry;


public class Vector extends CartesianCoordinate {

	public Vector(){
		super(0, 0);
	}
	
	public Vector(double xValue, double yValue) {
		super(xValue, yValue);
	}
	
	public double getMagnitude(){
		
		return Math.sqrt(xValue*xValue + yValue*yValue);
		
	}
	
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
	
	public void setMagnitude(double magnitude) {
		xValue = (xValue/getMagnitude()) * magnitude;
		yValue = (yValue/getMagnitude()) * magnitude;
	}

	@Override
	public String toString() {
		return "Vector [xValue=" + xValue + ", yValue=" + yValue + "]";
	}
	
}

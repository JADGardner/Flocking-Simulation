package drawing;

import java.awt.geom.Ellipse2D;

public class Portal extends Ellipse2D.Double{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Portal(){
		super();
		height = 100;
		width = 100;
		// TODO Auto-generated constructor stub
	}
	
	public Portal(int xPos, int yPos){
		super();
		height = 100;
		width = 100;
		x = xPos;
		y = yPos;
		// TODO Auto-generated constructor stub
	}
	
	public void setXPosition(double xPosition){
		x = xPosition;
	}
	
	public void setYPosition(double yPosition){
		y = yPosition;
	}
	
}

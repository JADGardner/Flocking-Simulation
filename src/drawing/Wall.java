package drawing;

import java.awt.geom.Rectangle2D;

public class Wall extends Rectangle2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Wall(){
		super();
		height = 10;
		width = 10;
		// TODO Auto-generated constructor stub
	}
	
	public Wall(int xPos, int yPos){
		super();
		height = 10;
		width = 10;
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

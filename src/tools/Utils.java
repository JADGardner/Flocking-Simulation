/**
 * Utils.java 						18/04/2019
 * Version: 1.0
 * Programmers: Electronics Department, Y3843317
 * Company: University of York
 * 
 * This contains static methods used throughout the 
 * program. The pause, randomDouble and randomInt 
 * methods were provided by the University of York.
 * The scaleImageIcon method was written by Y3843317.
 */

package tools;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Utils {
	private static Random randomGenerator = new Random();
	
	public static void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// We are happy with interruptions, so do not report exception
		}
	}
	
	public static double randomDouble() {
		return randomGenerator.nextFloat();
	}

	public static int randomInt(int paramInt) {
		return randomGenerator.nextInt(paramInt);
	}
	
	/**
	 * This method takes an ImageIcon and a size and scales the 
	 * image. It does this by first transforming it to an Image
	 * Class, scaling that using the built in method. Then 
	 * transforming it back to an ImageIcon.
	 * 
	 * @param i The ImageIcon to be resized.
	 * @param size The width and height in pixels of the new size.
	 * @return The same imageIcon now scaled.
	 */
	public static ImageIcon scaleImageIcon(ImageIcon i, int size) {
		Image image = i.getImage(); // Transform to Image
		Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		i = new ImageIcon(newimg);  // Transform back to ImageIcon
		return i;
	}
}

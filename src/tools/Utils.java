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
	
	public static ImageIcon scaleImageIcon(ImageIcon i, int size) {
		Image image = i.getImage(); // transform it 
		Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		i = new ImageIcon(newimg);  // transform it back
		return i;
	}
}

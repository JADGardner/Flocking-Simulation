package tools;
import java.util.Random;

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
}

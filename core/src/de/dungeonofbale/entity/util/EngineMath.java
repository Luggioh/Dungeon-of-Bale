package de.dungeonofbale.entity.util;

import java.util.Random;

/**
 * Einfache Mathematik. Sehr einfache Mathematik.
 *
 */
public class EngineMath {

	private static final Random RANDOM = new Random();
	
	/**
	 * 
	 * Diese Methode schaut ob der wert 'f' zwischen den Werten 'min' und 'max' liegt
	 * 
	 * @param f
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isBetween(float f, float min, float max) {
		return (f >= min) && (f <= max);
	}
	
	/**
	 * 
	 * Diese methode returnt einen Random Integer.
	 * 
	 * @return
	 */
	public static int randomIdGenerator() {
		return RANDOM.nextInt(100000);
	}

}

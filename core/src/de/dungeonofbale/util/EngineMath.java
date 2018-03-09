package de.dungeonofbale.util;

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
	 * @param max
	 * 		Die Maximale Zahl, wie weit der {@code Random} gehen soll.
	 * 
	 * @return
	 */
	public static int randomIdGenerator(int max) {
		return RANDOM.nextInt(max);
	}
	
	/**
	 * Gleiche Methode, wie {@code EngineMath#randomIdGenerator(int)}} nur mit, dem max Wert 100000
	 * @return
	 */
	public static int randomIdGenerator() {
		return randomIdGenerator(100000);
	}
	
	/**
	 * Diese Methode gibt dir die eingegeben Zahl als Binärer Code wieder zurück
	 * @param number
	 * @return
	 */
	public static String getIntAsBinary(int number) {
		StringBuilder binary = new StringBuilder();
		for(int i = 31; i >= 0; i--) {
			binary.append((number & (1 << i)) != 0 ? "1" : "0");
			if(i % 8 == 0 && i != 0) {
				binary.append(" ");
			}
		}
		return binary.toString();
	}

}

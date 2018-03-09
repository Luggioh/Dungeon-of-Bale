package de.dungeonofbale.util;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

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
	 * Hier bekommt man den Mittelpunkt der X Achse
	 * @return
	 */
	public static int getMiddlePointX() {
		return Gdx.graphics.getWidth() / 2;
	}
	
	/**
	 * Hier bekommt man den Mittelpunkt der Y Achse
	 * @return
	 */
	public static int getMiddlePointY() {
		return Gdx.graphics.getHeight() / 2;
	}
	
	/**
	 * Hier bekommt man den Mittelpunkt des Bildschirms als {@code Vector2}
	 * @return
	 */
	public static Vector2 getMiddlePoint() {
		return new Vector2(getMiddlePointX(), getMiddlePointY());
	}
	
}

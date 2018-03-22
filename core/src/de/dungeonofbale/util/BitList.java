package de.dungeonofbale.util;

/**
 * Diese Klasse beinhaltet public static final short Variable, undzwar die Bits f�r die Collision und Interaction
 * 
 * Bits immer in der 2er Potenz (2�, 2�....)
 * @author EncryptDev
 *
 */
public class BitList {
	
	public static final short BIT_GROUND = 1;
	public static final short BIT_PLAYER = 2;
	public static final short BIT_ENEMY = 4;
	public static final short BIT_COLLISION_SHAPE = 8;

}

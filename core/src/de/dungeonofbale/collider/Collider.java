package de.dungeonofbale.collider;

/**
 *
 * Ein einfaches Interface mit dem generischen Typ "T", dass sp�ter angibt welcher Collider es ist.
 * @param <T>
 */
public interface Collider<T> {
	
	void move(float newX, float newY);
	
	boolean isCollided(Collider<T> other);
	
	T getType();
	
	float getValue();
	
}

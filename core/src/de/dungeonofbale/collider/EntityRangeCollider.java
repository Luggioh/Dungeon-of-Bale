package de.dungeonofbale.collider;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Das ist ein Circle Collider.
 * @author EncryptDev
 */
public class EntityRangeCollider implements Collider<Circle> {

	private Circle circle;
	
	/**
	 * Default Contrsuctor für den EntityRangeCollider
	 * @param position
	 * @param radius - default radius = 3
	 */
	public EntityRangeCollider(Vector2 position, float radius) {
		this.circle = new Circle(position, radius * 100);
	}
	
	/**
	 * Die Kreis position wird hier aktuell gehalten.
	 * @param newX
	 * @param newY
	 */
	@Override
	public void move(float newX, float newY) {
		this.circle.x = (int) newX;
		this.circle.y = (int) newY;
	}


	public Circle getCircle() {
		return circle;
	}
	
	/**
	 * Diese Methode, return true wenn der andere {@link Collider} in dem Kreis ist.
	 * @param other
	 * @return
	 */
	@Override
	public boolean isCollided(Collider<Circle> other) {
		return this.circle.contains(other.getType().x, other.getType().y);
	}

	@Override
	public Circle getType() {
		return this.circle;
	}

	@Override
	public float getValue() {
		return this.circle.radius;
	}

}

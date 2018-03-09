package de.dungeonofbale.entity.ai;

import de.dungeonofbale.entity.Entity;
import de.dungeonofbale.util.EngineMath;

/**
 * Eine einfache Klasse, damit das Entity ein anderes Entity verfolgen kann sofern er im Kreis ist
 *
 */
public class EntityAIPathfinding {

	private Entity entity;

	public EntityAIPathfinding(Entity entity) {
		this.entity = entity;
	}

	/**
	 * Diese Methode schaut von welcher Richtung der Spieler kommt, und
	 * jenachdem wird dann die Position des Entites zu dem Spieler geändert
	 * 
	 * @param other
	 * @param speed
	 * @param timeSinzeLastFrame
	 */
	public void pathfinding(Entity other, float speed, float delta) {
		float otherX = other.getPosition().x;
		float otherY = other.getPosition().y;

		float diffX = entity.getPosition().x - otherX;
		float diffY = entity.getPosition().y - otherY;

		boolean changed = false;

		if (EngineMath.isBetween(diffX, -other.getEntityRangeCollider().getValue(), 0)) {

			if (EngineMath.isBetween(diffY, -other.getEntityRangeCollider().getValue(), 0)) {
				other.getPosition().x -= speed * delta;
				other.getPosition().y -= speed * delta;
			} else if (EngineMath.isBetween(diffY, 0, other.getEntityRangeCollider().getValue())) {
				other.getPosition().x -= speed * delta;
				other.getPosition().y += speed * delta;
			}
			changed = true;
		} else if (EngineMath.isBetween(diffX, 0, other.getEntityRangeCollider().getValue())) {

			if (EngineMath.isBetween(diffY, -other.getEntityRangeCollider().getValue(), 0)) {
				other.getPosition().x += speed * delta;
				other.getPosition().y -= speed * delta;
			} else if (EngineMath.isBetween(diffY, 0, other.getEntityRangeCollider().getValue())) {
				other.getPosition().x += speed * delta;
				other.getPosition().y += speed * delta;
			}
			changed = true;
		}
		
		if (changed) {
			other.getRectangle().x = (int) other.getPosition().x;
			other.getRectangle().y = (int) other.getPosition().y;
			other.getEntityRangeCollider().move(other.getPosition().x, other.getPosition().y);
		}

	}

	public Entity getEntity() {
		return entity;
	}

}

package de.dungeonofbale.entity;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.dungeonofbale.entity.ai.MovementController;

/**
 * Diese Klasse ist dafür da um die Entities zu regestrieren, bzw welche zu spawnen
 *
 */
public class EntityRegestry {
	
	private static final List<Entity> ALL_ENTITYS = new LinkedList<Entity>();
	
	private static Player player;
	
	/**
	 * <p>registerEntity regestriert das Entity, sodass es gerendert und geupadet werden kann.</p>
	 * @param position
	 * @param texture
	 * @param rangeColliderRadius
	 * @param entityType
	 * @return
	 */
	public static Entity registerEntity(Vector2 position, Texture texture, float rangeColliderRadius, EntityType entityType) {
		switch(entityType) {
		case ENEMY:
			Enemy enemy = new Enemy(position, texture, rangeColliderRadius);
			ALL_ENTITYS.add(enemy);
			return enemy;
		case PLAYER:
			player = new Player(position, texture, rangeColliderRadius);
			player.setMovementController(new MovementController(player, Keys.W, Keys.A, Keys.S, Keys.D));
			return player;
		}
		return null;
	}
	
	public static void registerEntity(Entity entity) {
		ALL_ENTITYS.add(entity);
	}
	
	/**
	 * Löscht ein Entity aus dem Speicher.
	 * @param entity
	 */
	public static void unregisterEntity(Entity entity) {
		if(ALL_ENTITYS.contains(entity))
			throw new NullPointerException("The entity " + entity.getId() + " isn't regestried");
		ALL_ENTITYS.remove(entity);
		entity.dispose();
	}
	
	public static List<Entity> getAllEntitys() {
		return ALL_ENTITYS;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public enum EntityType {
		
		ENEMY, PLAYER;
		
	}
	
}

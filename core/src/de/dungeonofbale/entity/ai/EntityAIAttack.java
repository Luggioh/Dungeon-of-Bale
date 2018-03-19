package de.dungeonofbale.entity.ai;

import de.dungeonofbale.entity.Entity;
import de.dungeonofbale.entity.EntityRegestry;

/**
 * Eine einfache Klasse, damit der Enemy angreifen kann.
 * @author tobia
 *
 */
public class EntityAIAttack {
	
	private final double attackCooldownInSeconds = 5;
	
	private Entity entity;
	private double attackDamage;
	private double attackTime;
	
	public EntityAIAttack(Entity entity, double attackDamage) {
		this.entity = entity;
		this.attackDamage = attackDamage;
		this.attackTime = System.currentTimeMillis();
	}
	
	public void attack(Entity other) {
		if(System.currentTimeMillis() > attackTime && other.health > 0) {
			other.health -= attackDamage;
			attackTime = System.currentTimeMillis() + (attackCooldownInSeconds * 1000);
			System.out.println(other.health);
			
			if(other.health <= 0) {
				EntityRegestry.unregisterEntity(other);
				return;
			}
		}
	}
	
	
	public Entity getEntity() {
		return entity;
	}

}

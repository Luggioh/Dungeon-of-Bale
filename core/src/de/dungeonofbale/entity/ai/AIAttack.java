package de.dungeonofbale.entity.ai;

import de.dungeonofbale.entity.Entity;

public class AIAttack implements Runnable {

	private Entity entity;
	private Entity other;

	private boolean b;
	private boolean c;
	private boolean d;
	private Thread attackThread;
	private float cooldown;

	public AIAttack(Entity entity, float cooldown) {
		this.entity = entity;
		this.cooldown = cooldown;
		this.b = false;
		this.c = false;
		this.d = false;
		this.attackThread = new Thread(this);
	}

	@Override
	public void run() {
		while (c) {
			other.updateHealth(other.getHealth() - entity.getDamage());
			System.out.println(other.getClass() + " HEALTH AIATTACK: " + other.getHealth());
			try {
				Thread.sleep((long) (cooldown * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void endAttack() {
		this.c = false;
		this.b = false;
		this.other = null;
		System.out.println("END ATTACK");
	}

	public void attack(Entity other) {
		this.other = other;
		this.b = true;
	}

	public void checkAttack() {
		if (b && !c) {
			c = true;
			if(!d) {
				d = true;
				this.attackThread.start();
			}
		}
	}

	public Entity getEntity() {
		return entity;
	}

}

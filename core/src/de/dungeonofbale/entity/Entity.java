package de.dungeonofbale.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

import de.dungeonofbale.world.World;
import de.dungeonofbale.world.WorldContactListener;

/**
 * Die abstracte Entity Klasse, erbt aus {@link Sprite} und implementiert das
 * Interface {@link Definable}. Sprite ist wie man es aus swing kennt, eine
 * einfache Textur die gezeichnet wird. Das Interface Definable gibt an, welche
 * Klassen alles die Methode {@link Definable#define()} haben. In dieser
 * define() Methode, wird der Physic Body des Entities erstellt, und
 * konfiguriert.
 * 
 * @author EncryptDev
 *
 */
public abstract class Entity extends Sprite implements Definable {

	// Der Radius, der Kollision für z.B: Angriffe
	protected final float collisionRad = 20f;

	// Die Textur des Entities
	protected TextureRegion textureRegion;

	// Die Start Position des Entities
	protected Vector2 startPosition;

	// Die World, in der das Entity spawnt.
	protected World world;

	// Der Physic Body.
	protected Body body;

	// Das Leben des Entities
	protected float health;

	protected float damage;

	public Entity(TextureRegion textureRegion, Vector2 startPosition, World world, float health) {
		this.textureRegion = textureRegion;
		this.startPosition = startPosition;
		this.world = world;
		this.health = health;

		define();
		setBounds(0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
		setRegion(textureRegion);
	}

	/**
	 * In dieser Methode, wird das Entity dauerhaft geupadet.
	 * 
	 * @param delta
	 */
	public abstract void update(float delta);

	/**
	 * 
	 * In dieser Methode, wird die Position des Entities aktualisiert. Bzw der
	 * Body bekommt einen "Schub" sozusagen. Es ist ein Impuls, so dass er ein
	 * bisschen gleitet.
	 * 
	 * @param x
	 * @param y
	 */
	public void updatePosition(float x, float y) {
		this.body.applyLinearImpulse(new Vector2(x, y), body.getWorldCenter(), true);
	}

	/**
	 * Diese Methode updatet das Leben des Entities
	 * 
	 * @param newHealth
	 */
	public void updateHealth(float newHealth) {
		this.health = newHealth;
	}

	public Body getBody() {
		return body;
	}

	public float getHealth() {
		return health;
	}

	public float getDamage() {
		return damage;
	}

	/**
	 * Diese Methode gibt den jeweiligen Entity aus den zwei Fixtures zurück.
	 * Man muss nur die EntityType Klasse angeben. Beispiel:
	 * 
	 * Wenn ich 2 Fixtures habe (Siehe
	 * {@link WorldContactListener#beginContact(com.badlogic.gdx.physics.box2d.Contact)})
	 * und die dann übergebe. Muss ich als drittes Argument die Klasse angeben.
	 * Wenn ich ein Player will gebe ich Player.class an, wenn ich ein Enemy
	 * will gebe ich Enemy.class an. Das funktioniert mit jeder Klasse die aus
	 * Entity erbt.
	 * 
	 * @param fixA
	 *            - Fixture 1 (A)
	 * @param fixB
	 *            - Fixture 2 (B)
	 * @param entityType
	 *            - Der EntityType der zurückgegeben werden soll.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getEntityBy2Fixtures(Fixture fixA, Fixture fixB, Class<T> entityType) {
		if (entityType.getSuperclass() == Entity.class) {
			if (fixA.getUserData().getClass() == entityType) {
				return (T) fixA.getUserData();
			} else {
				return (T) fixB.getUserData();
			}
		}
		return null;
	}

}

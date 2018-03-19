package de.dungeonofbale.entity;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import de.dungeonofbale.collider.Collider;
import de.dungeonofbale.collider.EntityRangeCollider;
import de.dungeonofbale.entity.ai.EntityAIAttack;
import de.dungeonofbale.entity.ai.EntityAIPathfinding;
import de.dungeonofbale.entity.ai.MovementController;
import de.dungeonofbale.util.EngineMath;

public abstract class Entity extends Sprite implements Definable {
	
	/* Final Variablen */
	private final double maxHealth = 100;
	private final double attackDamage = 20;
	
	/* Public Variablen*/
	
	public double health;
	
	/* Private Variablen */
	private int id;
	private Vector2 position;
	private TextureRegion texture;
	private Rectangle rectangle;
	private MovementController movementController;
	
	/* EntityAI und EntityCollider */
	private EntityAIPathfinding aiPathfinding;
	private EntityAIAttack aiAttack;
	private Collider<Circle> entityRangeCollider;
	
	public Entity() {
	}
	
	public Entity(Entity entity) {
		this(entity.getPosition(), entity.getTextureRegion(), entity.getEntityRangeCollider().getType().radius);
	}
	
	/**
	 * 
	 * @param position - Die Position wo das Entity gespawnt wird
	 * @param texture - Die Textur die das Entity haben soll
	 */
	public Entity(Vector2 position, TextureRegion texture, float rangeColliderRadius) {
		this.id = EngineMath.randomIdGenerator();
		this.position = position;
		this.texture = texture;
		this.aiPathfinding = new EntityAIPathfinding(this);
		this.aiAttack = new EntityAIAttack(this, attackDamage);
		this.entityRangeCollider = new EntityRangeCollider(position, rangeColliderRadius);
		this.health = maxHealth;
		this.define();
	}
	
	/**
	 * Diese Methode bewegt das Entity.
	 * @param speed
	 * @param timeSinzeLastFrame
	 */
	public void moveEntity(float speed, float timeSinzeLastFrame) {
		this.movementController.move(speed, timeSinzeLastFrame);
		this.rectangle.x = (int) position.x;
		this.rectangle.y = (int) position.y;
		this.entityRangeCollider.move(this.position.x, this.position.y);
	}
	
	/**
	 * Diese Methode ruft die pathfinding Methode der {@link EntityAIPathfinding} Klasse auf.
	 * @param other
	 * @param speed
	 * @param timeSinzeLastFrame
	 */
	public void pathfinding(Entity other, float speed, float timeSinzeLastFrame) {
		this.aiPathfinding.pathfinding(other, speed, timeSinzeLastFrame);
	}
	
	public void attack(Entity other) {
		this.aiAttack.attack(other);
	}
	
	/**
	 * Diese Methode rendert das Entity
	 * @param batch
	 */
	public void renderEntity(SpriteBatch batch) {
		batch.draw(texture, position.x, position.y);
	}
 
	public void setMovementController(MovementController movementController) {
		this.movementController = movementController;
	}

	public void changeTexture(TextureRegion newTexture) {
		this.texture = newTexture;
	}

	/**
	 * Diese Methode greif auf die isInRange Methode von {@link EntityRangeCollider} zu.
	 * @param other
	 * @return
	 */
	public boolean containsInCollideArea(Collider<Circle> other) {
		return this.entityRangeCollider.isCollided(other);
	}

	/**
	 * Diese Methode greif auf die intersects Methode von {@link Rectangle} zu.
	 * @param other
	 * @return
	 */
	public boolean isCollision(Entity other) {
		return this.rectangle.intersects(other.getRectangle());
	}
	
	public void dispose() {
	}
	
	public EntityAIPathfinding getAiPathfinding() {
		return aiPathfinding;
	}
	
	public double getMaxHealth() {
		return maxHealth;
	}
	
	public MovementController getMovementController() {
		return movementController;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public TextureRegion getTextureRegion() {
		return texture;
	}

	public Vector2 getPosition() {
		return position;
	}
	
	public int getId() {
		return id;
	}

	public Collider<Circle> getEntityRangeCollider() {
		return entityRangeCollider;
	}
	
}

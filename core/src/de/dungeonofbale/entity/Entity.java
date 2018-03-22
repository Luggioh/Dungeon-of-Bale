package de.dungeonofbale.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import de.dungeonofbale.world.World;

public abstract class Entity extends Sprite implements Definable {
	
	protected final float collisionRad = 20f;
	protected TextureRegion textureRegion;
	protected Vector2 startPosition;
	protected World world;
	protected Body body;
	
	public Entity(TextureRegion textureRegion, Vector2 startPosition, World world) {
		this.textureRegion = textureRegion;
		this.startPosition = startPosition;
		this.world = world;
		
		define();
		setBounds(0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
		setRegion(textureRegion);
	}
	
	public abstract void update(float delta);
	
	public void updatePosition(float x, float y) {
		this.body.applyLinearImpulse(new Vector2(x, y), body.getWorldCenter(), true);
	}
	
	public Body getBody() {
		return body;
	}
}

package de.dungeonofbale.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import de.dungeonofbale.util.BitList;
import de.dungeonofbale.world.World;

public class Player extends Entity {
	
	private final float speed = 0.1f;
	private final float maxHealth = 100;
	
	public float health;

	public Player(TextureRegion textureRegion, Vector2 startPosition, World world) {
		super(textureRegion, startPosition, world);
		this.health = maxHealth;
	}

	@Override
	public void define() {
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody;
		bdef.position.set(startPosition);
		body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		EdgeShape footshape = new EdgeShape();
		footshape.set(new Vector2(0, -8), new Vector2(6, -8));
		fdef.filter.categoryBits = BitList.BIT_PLAYER;
		fdef.filter.maskBits = BitList.BIT_GROUND | BitList.BIT_ENEMY;
		fdef.shape = footshape;
		
		body.createFixture(fdef).setUserData("player_foot");
		
		EdgeShape headshape = new EdgeShape();
		headshape.set(new Vector2(0, 8), new Vector2(6, 8));
		fdef.shape = headshape;
		body.createFixture(fdef).setUserData("player_head");
		
		CircleShape collisionShape = new CircleShape();
		collisionShape.setRadius(collisionRad);
		fdef.shape = collisionShape;
		fdef.filter.categoryBits = BitList.BIT_COLLISION_SHAPE;
		fdef.filter.maskBits = BitList.BIT_COLLISION_SHAPE;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData(this);
	}

	@Override
	public void update(float delta) {
		handleInput();
		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
	}
	
	public void handleInput() {
		if(Gdx.input.isKeyPressed(Keys.A)) {
			this.body.applyLinearImpulse(new Vector2(-speed, 0), this.body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			this.body.applyLinearImpulse(new Vector2(speed, 0), this.body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Keys.W)) {
			this.body.applyLinearImpulse(new Vector2(0, speed), this.body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			this.body.applyLinearImpulse(new Vector2(0, -speed), this.body.getWorldCenter(), true);
		}
	}


}

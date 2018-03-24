package de.dungeonofbale.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import de.dungeonofbale.entity.ai.AIAttack;
import de.dungeonofbale.util.BitList;
import de.dungeonofbale.world.World;

public class Enemy extends Entity {
	
	private AIAttack aiAttack;
	
	public Enemy(TextureRegion textureRegion, Vector2 startPosition, World world) {
		super(textureRegion, startPosition, world, 100);
		this.damage = 20;
		this.aiAttack = new AIAttack(this, 5);
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
		fdef.filter.categoryBits = BitList.BIT_ENEMY;
		fdef.filter.maskBits = BitList.BIT_GROUND | BitList.BIT_PLAYER | BitList.BIT_ENEMY;
		fdef.shape = footshape;
		
		body.createFixture(fdef).setUserData("enemy_foot");
		
		EdgeShape headshape = new EdgeShape();
		headshape.set(new Vector2(0, 8), new Vector2(6, 8));
		fdef.shape = headshape;
		body.createFixture(fdef).setUserData("enemy_head");
		
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
		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
		this.aiAttack.checkAttack();
	}
	
	public void endAttack() {
		this.aiAttack.endAttack();
	}
	
	public void attack(Entity other) {
		this.aiAttack.attack(other);
	}
	
}

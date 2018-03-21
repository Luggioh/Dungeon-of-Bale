package de.dungeonofbale.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import de.dungeonofbale.util.BitList;
import de.dungeonofbale.world.World;

public class Player extends Sprite implements Definable {
	
	private Vector2 startPosition;
	private Body body;
	private World world;

	public Player(TextureRegion textureRegion, Vector2 startPosition, World world) {
		this.startPosition = startPosition;
		this.world = world;
		
		define();
		setBounds(0, 0, 32, 32);
		setRegion(textureRegion);
	}
	
	public void update() {
		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
		handleInput();
	}

	@Override
	public void define() {
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.StaticBody;
		bdef.position.set(startPosition);
		body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		EdgeShape eshape = new EdgeShape();
		eshape.set(new Vector2(1, 1), new Vector2(8, 1));
		fdef.filter.categoryBits = BitList.BIT_PLAYER;
		fdef.filter.maskBits = BitList.BIT_GROUND;
		fdef.shape = eshape;
		
		body.createFixture(fdef).setUserData(this);
		
	}
	
	public void handleInput() {
		if(Gdx.input.isKeyPressed(Keys.A)) {
			this.body.applyLinearImpulse(new Vector2(-100, 0), this.body.getWorldCenter(), true);
			System.out.println("x_a " + this.body.getPosition().x);
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			this.body.applyLinearImpulse(new Vector2(100, 0), this.body.getWorldCenter(), true);
			System.out.println("x_d " + this.body.getPosition().x);
		}
		if(Gdx.input.isKeyPressed(Keys.W)) {
			this.body.applyLinearImpulse(new Vector2(0, 100), this.body.getWorldCenter(), true);
			System.out.println("y_w " + this.body.getPosition().y);
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			this.body.applyLinearImpulse(new Vector2(0, -100), this.body.getWorldCenter(), true);
			System.out.println("y_s " + this.body.getPosition().y);
		}
	}
	
	@Override
	public Body getBody() {
		return body;
	}



}

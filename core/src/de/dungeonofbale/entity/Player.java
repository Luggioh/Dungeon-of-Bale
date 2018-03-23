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
	
	//Die Gewschindigkeit des Spielers
	private final float speed = 0.1f;
	
	//Die Maximale Gesundheit des Spielers
	private final float maxHealth = 100;
	
	//Leben des Spieler, als public damit man es von überall ändern kann.
	public float health;

	public Player(TextureRegion textureRegion, Vector2 startPosition, World world) {
		super(textureRegion, startPosition, world);
		this.health = maxHealth;
	}

	@Override
	public void define() {
		//BodyDef (BodyDefinition) definiert, den Physic Body. (Eine Box sozusagen für die Fixtures)
		BodyDef bdef = new BodyDef();
		
		/*Hier wird der BodyType gesetzt. 
		 *BodyTypes:
		 *
		 *DynamicBody - Sagt, das man den Body bewegen kann.
		 *StaticBody - Sagt, das der Body nur auf einer Stelle bleibt.
		 **/
		bdef.type = BodyType.DynamicBody;
		
		//Setzt die Positon des Bodys
		bdef.position.set(startPosition);
		
		//Erstellt den Body, über die Welt
		body = world.createBody(bdef);
		
		//FixtureDef (FixtureDefinition) ist nun für die eigentliche Kollision verantwortlich.
		FixtureDef fdef = new FixtureDef();
		
		/*
		 * Es gibt 3 Shapes die man nehmen kann.
		 * EdgeShape - eine Linie
		 * CircleShape - ein Kreis
		 * PolygonShape - ein Polygon
		 * 
		 */
		EdgeShape footshape = new EdgeShape();
		
		/*
		 * Da dies eine EdgeShape ist, wird die Länge + Positon des EdgeShapes gesetzt. 
		 * Erster Paramter für die Linke Ecke, zweiter Paramter für die rechte Ecke.
		 */
		footshape.set(new Vector2(0, -8), new Vector2(6, -8));
		
		/*
		 *Die categoryBits geben an, welchen Bit dem Spieler zugehört. Damit man später schauen kann
		 *wer mit was kollidiert. 
		 */
		fdef.filter.categoryBits = BitList.BIT_PLAYER;
		
		/*
		 *Die Maskbits geben an, mit was der Spieler alles kollidieren kann. Dort können mehrere Bits zusammengefasst werden. 
		 */
		fdef.filter.maskBits = BitList.BIT_GROUND | BitList.BIT_ENEMY;
		
		/*
		 *Nun setzten wir der FixtureDef noch die Shape 
		 */
		fdef.shape = footshape;
		
		/*
		 * Hier wird über den Body, die FixtureDef erstellt. Sprich, die Linie die bei den Füßen ist
		 * damit, er mit den Wänden etc kollidieren kann. 
		 * Und die UserData wird auf "player_foot" gesetzt, damit man später die verschiedenen FixtureDefs auseinander halten kann.
		 */
		body.createFixture(fdef).setUserData("player_foot");
		
		/*
		 *Hier nochmal eine EdgeShape, für den Kopf. Damit er auch richtig kollidiert. 
		 */
		EdgeShape headshape = new EdgeShape();
		headshape.set(new Vector2(0, 8), new Vector2(6, 8));
		fdef.shape = headshape;
		body.createFixture(fdef).setUserData("player_head");
		
		/*
		 *Die Circle Shape, ist einfach ein Kreis, der nachher schaut ob man mit anderen Interagieren kann, oder 
		 *ein Enemy in dem Bereich ist. 
		 */
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

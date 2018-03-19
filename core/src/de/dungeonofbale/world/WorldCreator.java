package de.dungeonofbale.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class WorldCreator {
	
	private World world;
	
	public WorldCreator(World world) {
		this.world = world;
	}
	
	public void generate() {
		TiledMap map = this.world.getMap();
		
		BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
	}
	
	public World getWorld() {
		return world;
	}

}

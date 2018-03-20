package de.dungeonofbale.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
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
        
        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        
	}
	
	public World getWorld() {
		return world;
	}

}

package de.dungeonofbale.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import com.badlogic.gdx.physics.box2d.Body;

public class World {
	
	private SpriteBatch spriteBatch;
	private com.badlogic.gdx.physics.box2d.World gdxWorld;
	private TmxMapLoader tmxMapLoader;
	private TiledMap map;
	private TextureAtlas textureAtlas;
	private WorldCreator worldCreator;
	
	public World(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
		this.gdxWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
		this.tmxMapLoader = new TmxMapLoader();
		this.map = tmxMapLoader.load("/map_objects/world.tmx");
		this.textureAtlas = new TextureAtlas("/map_objects/mapPack.pack");
		
		this.worldCreator = new WorldCreator(this);
		this.worldCreator.generate();
	}
	
	public void render() {
		
	}
	
	public Body createBody(BodyDef bodyDef) {
		return this.gdxWorld.createBody(bodyDef);
	}
	
	public TiledMap getMap() {
		return map;
	}
	
	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
	
	public com.badlogic.gdx.physics.box2d.World getGdxWorld() {
		return gdxWorld;
	}

}

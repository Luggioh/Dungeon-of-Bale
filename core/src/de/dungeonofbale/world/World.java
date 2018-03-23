package de.dungeonofbale.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import de.dungeonofbale.util.TextureManager;

/**
 * Die World Klasse representiert, ein Welt Objekt. 
 * @author EncryptDev
 *
 */
public class World {
	
	//Die Batch um die Tiles zu rendern.
	private SpriteBatch spriteBatch;
	
	//Die GdxWorld, damit wir Bodys etc erstellen können.
	private com.badlogic.gdx.physics.box2d.World gdxWorld;
	
	//Der TmxMapLoader, der die Map für uns läd.
	private TmxMapLoader tmxMapLoader;
	
	//De TiledMap, ist die Map die geladen wird
	private TiledMap map;
	
	//Der TextureManager managed die TextureAtlanten und die Texturen
	private TextureManager textureManager;
	
	//Der WorldCreator erstellt die WorldLayers
	private WorldCreator worldCreator;
	
	//Der OrthogonalTiledMapRenderer rendert die TiledMap 2D
	private OrthogonalTiledMapRenderer renderer;
	
	//Der Box2DDebugRenderer rendert die BoxLinien, um zu schauen ob alles auch funktioniert
	private Box2DDebugRenderer debug;
	
	//Der WorldContactListener reagiert, auf den Kontakt zwischen zwei Fixtures
	private WorldContactListener worldContactListener;
	
	public World(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
		this.gdxWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
		this.textureManager =new TextureManager();
		this.textureManager.loadTextureAtlas("entities", new TextureAtlas("core/assets/entities_gutter.pack"));
		this.tmxMapLoader = new TmxMapLoader();
		this.map = tmxMapLoader.load("core/assets/world.tmx");
		this.renderer = new OrthogonalTiledMapRenderer(map, 1);
		this.worldContactListener = new WorldContactListener();
		this.gdxWorld.setContactListener(this.worldContactListener);
		
		this.worldCreator = new WorldCreator(this);
		this.worldCreator.generate();
		
		this.debug = new Box2DDebugRenderer();
		this.debug.SHAPE_STATIC.set(0, 1, 0, 1);
	}
	
	/**
	 * Updatet die Kamera für den renderer
	 * @param camera
	 */
	public void updateCamera(OrthographicCamera camera) {
		this.renderer.setView(camera);
	}
	
	/**
	 * Rendert die Welt
	 * @param projMatrix
	 */
	public void render(Matrix4 projMatrix) {
		this.debug.render(gdxWorld, projMatrix);
		this.renderer.render();
	}
	
	/**
	 * Erstelle einen Body, über die gdxWorld
	 * @param bodyDef
	 * @return
	 */
	public Body createBody(BodyDef bodyDef) {
		return this.gdxWorld.createBody(bodyDef);
	}
	
	public TiledMap getMap() {
		return map;
	}
	
	public TextureManager getTextureManager() {
		return textureManager;
	}
	
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
	
	public com.badlogic.gdx.physics.box2d.World getGdxWorld() {
		return gdxWorld;
	}

}

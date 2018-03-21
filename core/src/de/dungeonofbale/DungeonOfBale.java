package de.dungeonofbale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import de.dungeonofbale.entity.Entity;
import de.dungeonofbale.entity.EntityRegestry;
import de.dungeonofbale.entity.Player;
import de.dungeonofbale.entity.EntityRegestry.EntityType;
import de.dungeonofbale.screen.GameScreen;
import de.dungeonofbale.screen.MenuScreen;
import de.dungeonofbale.util.DOBAssetManager;
import de.dungeonofbale.world.World;

/**
 * Haupt Klasse. Erbt aus {@link Game} und implementiert {@link InputProcessor}
 * Hier werden die nötigen Sachen erstellt, bzw regestriert.eeeeee
 *
 */
public class DungeonOfBale extends Game implements InputProcessor {

	private static DungeonOfBale instance;

	private TextureAtlas textureAtlas;
	private Player player;
	private SpriteBatch batch;
	private MenuScreen menuScreen;
	private DOBAssetManager dobAssetManager;
	private World world;
	private Box2DDebugRenderer debug;

	/**
	 * Diese Methode wird in der Methode: {@link GameScreen#show()} aufgerufen.
	 */
	public void init() {
		this.batch = new SpriteBatch();
		this.world = new World(batch);
		debug = new Box2DDebugRenderer();
		debug.SHAPE_STATIC.set(1, 0, 0, 1);
		this.textureAtlas = this.world.getTextureAtlas();
		/*
		 * Hier wird der Spieler erstellt
		 */
		this.player = (Player) EntityRegestry.registerEntity(new Vector2(50, 50),
				this.textureAtlas.findRegion("main_charachter"), 3, EntityType.PLAYER);

		/* So erstellt man ein Entity. Dies hier dient nur als Test objekt */

		EntityRegestry.registerEntity(new Vector2(500, 500), this.textureAtlas.findRegion("enemy_1"), 3,
				EntityType.ENEMY);
		EntityRegestry.registerEntity(new Vector2(70, 70), this.textureAtlas.findRegion("enemy_5"), 3,
				EntityType.ENEMY);

		EntityRegestry.registerEntity(new Vector2(500, 500), this.textureAtlas.findRegion("main_charachter"), 3, EntityType.ENEMY);
		EntityRegestry.registerEntity(new Vector2(70, 70), this.textureAtlas.findRegion("main_charachter"), 3, EntityType.ENEMY);
		
		EntityRegestry.registerEntity(new Vector2(500, 500), this.textureAtlas.findRegion("enemy_6"), 3, EntityType.ENEMY);
		EntityRegestry.registerEntity(new Vector2(70, 70), this.textureAtlas.findRegion("enemy_6"), 3, EntityType.ENEMY);
	}

	/**
	 * Diese Methode wird in der Methode: {@link GameScreen#render(float)}
	 * aufgerufen.
	 * 
	 * @param delta
	 */
	public void update(float delta) {
		/* Die color wird über GL20 gesetzt */
		Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/* Der Spieler wird geupdated. */
		this.player.updateCamera();
		this.player.moveEntity(delta);

		this.world.updateCamera(this.player.getCamera());
		
		batch.begin();
		this.debug.render(this.world.getGdxWorld(), this.player.getCamera().combined);
		this.world.render();

		/* Die Camera des Spielers wird hier an dem Spritebatch regestriert */
		batch.setProjectionMatrix(this.player.getCamera().combined);

		/*
		 * Ich gehe alle regestrierten Entites durch und lasse sie rendern bzw
		 * schaue ob sie kollidieren oder den Spieler verfolgen müssen
		 */
		for (Entity all : EntityRegestry.getAllEntitys()) {
			if (this.player.containsInCollideArea(all.getEntityRangeCollider())) {
				this.player.pathfinding(all, 10, delta);
			}
			if (all.containsInCollideArea(player.getEntityRangeCollider())) {
				all.attack(player);
			}
			all.renderEntity(batch);
		}

		/* Hier wird der Spieler gerendert */
		this.player.renderEntity(batch);

		batch.end();
	}

	@Override
	public void create() {
		instance = this;
		// this.dobAssetManager = new DOBAssetManager();
		// this.dobAssetManager.loadImages();
		// this.dobAssetManager.getAssetManager().finishLoading();
		this.menuScreen = new MenuScreen(this, this);
		setScreen(menuScreen);
		Gdx.input.setInputProcessor(menuScreen.getStage());
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		/* Hier werden die Sachen aus dem Speicher genommen */
	}

	public DOBAssetManager getDobAssetManager() {
		return dobAssetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Player getPlayer() {
		return player;
	}

	// =================================I N P U T P R O C E S S O
	// R==================================

	public static DungeonOfBale getInstance() {
		return instance;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}

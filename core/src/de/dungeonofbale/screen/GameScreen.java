package de.dungeonofbale.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.dungeonofbale.DungeonOfBale;
import de.dungeonofbale.entity.Enemy;
import de.dungeonofbale.entity.Player;
import de.dungeonofbale.world.World;

/**
 * Der Screen in dem alles wichtige abläuft. Wo das Spiel gerendert wird
 *
 */
public class GameScreen extends AbstractScreen {

	private SpriteBatch batch;
	private World world;
	private Player player;
	private Enemy testEnemy;
	private Viewport viewport;
	private OrthographicCamera camera;

	public GameScreen() {
		this.batch = new SpriteBatch();
		this.viewport = new FillViewport(DungeonOfBale.WIDTH , DungeonOfBale.HEIGHT);
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false);
		this.world = new World(batch);
		this.player = new Player(this.world.getTextureManager().getTextureAtlas("entities").findRegion("main_charachter"), new Vector2(500, 500), world);
		this.testEnemy = new Enemy(this.world.getTextureManager().getTextureAtlas("entities").findRegion("enemy_3"), new Vector2(550, 500), world);
	}

	public void update(float delta) {
		
		this.world.getGdxWorld().step(1, 6, 2);
		this.player.update(delta);
		this.testEnemy.update(delta);
		
		this.camera.position.x = this.player.getBody().getPosition().x;
		this.camera.position.y = this.player.getBody().getPosition().y;
		
		this.camera.update();
		this.world.updateCamera(camera);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		update(delta);
		
		this.world.render(this.camera.combined);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		this.testEnemy.draw(batch);
		this.player.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void hide() {
	}

}

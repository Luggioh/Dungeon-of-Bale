package de.dungeonofbale.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.dungeonofbale.DungeonOfBale;

/**
 * Der Screen in dem alles wichtige abläuft. Wo das Spiel gerendert wird
 *
 */
public class GameScreen extends AbstractScreen {

	private SpriteBatch batch;

	public GameScreen(Game game, DungeonOfBale dob) {
		super(game, dob);
		this.batch = new SpriteBatch();
		Gdx.input.setInputProcessor(dob);
	}

	@Override
	public void show() {
		dob.init();
	}

	@Override
	public void render(float delta) {
		dob.update(delta);
		
		batch.begin();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		/* Hält den Viewport immer aktuell + camera */
		dob.getPlayer().getViewport().update(width, height);
		dob.getPlayer().getCamera().position.set(dob.getPlayer().getPosition().x, dob.getPlayer().getPosition().y, 0);
	}

	@Override
	public void pause() {
	}

	@Override
	public void hide() {
	}

}

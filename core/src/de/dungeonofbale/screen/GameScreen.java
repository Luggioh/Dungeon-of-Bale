package de.dungeonofbale.screen;

import com.badlogic.gdx.Game;

import de.dungeonofbale.DungeonOfBale;

/**
 * Der Screen in dem alles wichtige abl�uft. Wo das Spiel gerendert wird
 *
 */
public class GameScreen extends AbstractScreen {

	public GameScreen(Game game, DungeonOfBale dob) {
		super(game, dob);
	}

	@Override
	public void show() {
		dob.init();
	}

	@Override
	public void render(float delta) {
		dob.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		/* H�lt den Viewport immer aktuell + camera */
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

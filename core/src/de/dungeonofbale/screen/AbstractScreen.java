package de.dungeonofbale.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import de.dungeonofbale.DungeonOfBale;

public abstract class AbstractScreen implements Screen {
	
	protected Game game;
	protected DungeonOfBale dob;
	
	public AbstractScreen(Game game, DungeonOfBale dob) {
		this.game = game;
		this.dob = dob;
	}
	
	@Override
	public abstract void show();
	
	@Override
	public abstract void render(float delta);
	
	@Override
	public abstract void resize(int width, int height);
	
	@Override
	public abstract void pause();
	
	@Override
	public void resume() {
	}
	
	@Override
	public abstract void hide();
	
	@Override
	public void dispose() {
	}

}

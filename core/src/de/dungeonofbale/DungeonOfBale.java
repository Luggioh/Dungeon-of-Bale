package de.dungeonofbale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import de.dungeonofbale.screen.MenuScreen;

public class DungeonOfBale extends Game {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;
	
	private static DungeonOfBale instance;
	
	private MenuScreen menuScreen;

	@Override
	public void create() {
		instance = this;
		this.menuScreen = new MenuScreen();
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
	}
	
	public static DungeonOfBale getInstance() {
		return instance;
	}

}

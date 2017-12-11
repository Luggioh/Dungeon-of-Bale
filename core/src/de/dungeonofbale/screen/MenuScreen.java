package de.dungeonofbale.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.dungeonofbale.DungeonOfBale;

public class MenuScreen extends AbstractScreen {
	
	private SpriteBatch batch;
	private Sprite sprite;

	public MenuScreen(Game game, DungeonOfBale dob) {
		super(game, dob);
		this.batch = new SpriteBatch();
		this.sprite = new Sprite(dob.getTextures().getTexture("background"));
		this.sprite.setSize(900, 700);
		
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			game.setScreen(new GameScreen(game, dob));
		}
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void hide() {
	}


}

package de.dungeonofbale.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.dungeonofbale.DungeonOfBale;
import de.dungeonofbale.ui.ButtonClickListener;
import de.dungeonofbale.ui.UIHandler;

public class MenuScreen extends AbstractScreen {
	
	private SpriteBatch batch;
	
	/* Der UIHandler, managed die UIElemente
	 * UIElemente sind folgende:
	 * 
	 * (Alles von com.badlogic.gdx.sceees.scene2d.ui)
	 * Button
	 * Label
	 * TextArea
	 * ImageButton
	 * ImageTextButton
	 * TextButton
	 * 
	 * */
	private UIHandler uiHandler;


	
	/**
	 * Der normale Screen Constructor
	 * @param game
	 * @param dob
	 */
	public MenuScreen(Game game, DungeonOfBale dob) {
		super(game, dob);
		this.batch = new SpriteBatch();
		/* Der UIHandler wird erstellt */
		this.uiHandler = new UIHandler(null);
		
		/*Hier wird ein Button hinzugefügt. Gleiches gibt es auch mit TextArea und Label, oder anderen UIElemente*/
		this.uiHandler.createTextButton("Starten", new Vector2(100, 100), 0, new ButtonClickListener() {
			
			@Override
			public void buttonClick(InputEvent paramEvent, float paramX, float paramY) {
				game.setScreen(new GameScreen(game, dob));
			}
		});
		
		/* Hier wird ein Table erstellt, dies ist eine Art Gruppe für alle UIElemente. Dieser Table
		 * wird dann in der gleichen Methode auch noch zu der Stage hinzugefügt.*/
		this.uiHandler.createTable();
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		/* Hier wird die Stage gemalt. Sprich, das was wir oben im Constructor erstellt haben. */
		this.uiHandler.draw(batch, delta);
		
		batch.end();
		
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
	
	public UIHandler getUiHandler() {
		return uiHandler;
	}

	public Stage getStage() {
		return uiHandler.getStage();
	}

}

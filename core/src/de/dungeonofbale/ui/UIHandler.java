package de.dungeonofbale.ui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * 
 * UIHandler handelt, die ganzen UI-Elemente des jeweiligen Screens
 * 
 * @author EncryptDev
 *
 */
public class UIHandler {

	private List<Actor> actors;
	private Stage stage;
	private OrthographicCamera camera;

	// Wenn Skin null ist, wird die Standart Skin Datei genommen
	private Skin skin;

	public UIHandler(Skin skin) {
		this.actors = new LinkedList<>();
		this.stage = new Stage();
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.skin = skin == null ? new Skin(Gdx.files.internal("core/assets/UI_Elements/uiskin.json")) : skin;

		stage.getCamera().position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
	}

	public void draw(SpriteBatch batch, float delta) {
		batch.setProjectionMatrix(stage.getCamera().combined);
		stage.draw();
		stage.act(delta);
	}

	/**
	 * 
	 * Erstelle einen Text-Button
	 * 
	 * @param text
	 *            - Text, des Textbuttons
	 * @param position
	 *            - Die Position des Buttons
	 * @param pad
	 *            - Die Skalierung des Knopfes, wenn man drauf drückt
	 * @param listener
	 *            - Der ClickListener, hier wird {@code ButtonClickListener}
	 *            verwendet.
	 * @return
	 */
	public TextButton createTextButton(String text, Vector2 position, float pad, ClickListener listener) {
		TextButton textButton = new TextButton(text, skin);
		textButton.setPosition(position.x, position.y);
		textButton.pad(pad);
		if (listener != null)
			textButton.addListener(listener);
		actors.add(textButton);
		return textButton;
	}

	/**
	 * Siehe docs von
	 * {@link #createTextButton(String, Vector2, float, ClickListener)}
	 * 
	 * @param text
	 * @param position
	 * @param pad
	 * @return
	 */
	public TextButton createTextButton(String text, Vector2 position, float pad) {
		return createTextButton(text, position, pad, null);
	}

	/**
	 * Siehe docs von
	 * {@link #createImageTextButton(String, Vector2, float, ClickListener)}
	 * 
	 * @param text
	 * @param position
	 * @return
	 */
	public TextButton createTextButton(String text, Vector2 position) {
		return createTextButton(text, position, 0, null);
	}

	/**
	 * 
	 * Erstelle einen Image-Button
	 * 
	 * @param position
	 *            - Position des Image-Buttons
	 * @param pad
	 *            - Die Skalierung, wenn man drauf klickt
	 * @param listener
	 * @return
	 */
	public ImageButton createImageButton(Vector2 position, float pad, ClickListener listener) {
		ImageButton imageButton = new ImageButton(skin);
		imageButton.setPosition(position.x, position.y);
		imageButton.pad(pad);
		if (listener != null)
			imageButton.addListener(listener);
		actors.add(imageButton);
		return imageButton;
	}

	/**
	 * Siehe docs von {@link #createImageButton(Vector2, float, ClickListener)}
	 * 
	 * @param position
	 * @return
	 */
	public ImageButton createImageButton(Vector2 position) {
		return createImageButton(position, 0, null);
	}

	/**
	 * 
	 * Erstelle einen Image-Button mit Text
	 * 
	 * @param text
	 *            - Text, des Textbuttons
	 * @param position
	 *            - Die Position des Buttons
	 * @param pad
	 *            - Die Skalierung des Knopfes, wenn man drauf drückt
	 * @param listener
	 *            - Der ClickListener, hier wird {@code ButtonClickListener}
	 *            verwendet
	 * @return
	 */
	public ImageTextButton createImageTextButton(String text, Vector2 position, float pad, ClickListener listener) {
		ImageTextButton imageTextButton = new ImageTextButton(text, skin);
		imageTextButton.setPosition(position.x, position.y);
		imageTextButton.pad(pad);
		if (listener != null)
			imageTextButton.addListener(listener);
		actors.add(imageTextButton);
		return imageTextButton;
	}

	/**
	 * 
	 * Siehe docs von
	 * {@link #createImageTextButton(String, Vector2, float, ClickListener)}
	 * 
	 * @param text
	 * @param position
	 * @return
	 */
	public ImageTextButton createImageTextButton(String text, Vector2 position) {
		return createImageTextButton(text, position, 0, null);
	}

	/**
	 * 
	 * Erstelle eine Text Feld
	 * 
	 * @param text
	 *            - Der Hint Text. Sprich, das was leicht grau in dem Text-Feld
	 *            angezeigt wird
	 * @param position
	 *            - Die Position
	 * @return
	 */
	public TextArea createTextArea(String text, Vector2 position) {
		TextArea textArea = new TextArea(text, skin);
		textArea.setPosition(position.x, position.y);
		actors.add(textArea);
		return textArea;
	}

	/**
	 * 
	 * Erstelle ein normales Label
	 * 
	 * @param text
	 *            - Der Text des Labels
	 * @param position
	 *            - Die Position des Labels
	 * @return
	 */
	public Label createLabel(String text, Vector2 position) {
		Label label = new Label(text, skin);
		label.setPosition(position.x, position.y);
		actors.add(label);
		return label;
	}

	/**
	 * 
	 * Erstelle hier den Table, sprich. Alle Actors werden in eine "Gruppe"
	 * ({@link Table}) gepackt, und von der {@link Stage} gerendert.
	 * 
	 * @return
	 */
	public Table createTable() {
		Table table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (Actor a : actors) {
			table.add(a);
		}
		stage.addActor(table);
		return table;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public Stage getStage() {
		return stage;
	}

}

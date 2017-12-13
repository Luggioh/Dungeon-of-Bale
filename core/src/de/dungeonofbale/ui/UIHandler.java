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

public class UIHandler {
	
	private List<Actor> actors;
	private Stage stage;
	private OrthographicCamera camera;
	
	public UIHandler() {
		this.actors = new LinkedList<>();
		this.stage = new Stage();
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage.getCamera().position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
	}
	
	public void draw(SpriteBatch batch, float delta) {
		batch.setProjectionMatrix(stage.getCamera().combined);
		stage.draw();
		stage.act(delta);
	}
	
	public TextButton createTextButton(String text, Skin skin, Vector2 position, float originX, float originY, float pad, ClickListener listener) {
		TextButton textButton = new TextButton(text, skin);
		textButton.setPosition(position.x, position.y);
		textButton.setOrigin(originX, originY);
		textButton.pad(pad);
		if(listener != null)
			textButton.addListener(listener);
		actors.add(textButton);
		return textButton;
	}
	
	public TextButton createTextButton(String text, Skin skin, Vector2 position, float originX, float originY, float pad) {
		return createTextButton(text, skin, position, originX, originY, pad, null);
	}
	
	public TextButton createTextButton(String text, Skin skin, Vector2 position, float originX, float originY) {
		return createTextButton(text, skin, position, originX, originY, 0, null);
	}
	
	public TextButton createTextButton(String text, Skin skin, Vector2 position) {
		return createTextButton(text, skin, position, 0, 0, 0, null);
	}
	
	public ImageButton createImageButton(Skin skin, Vector2 position, float pad, float originX, float originY, ClickListener listener) {
		ImageButton imageButton = new ImageButton(skin);
		imageButton.setPosition(position.x, position.y);
		imageButton.setOrigin(originX, originY);
		imageButton.pad(pad);
		if(listener != null)
			imageButton.addListener(listener);
		actors.add(imageButton);
		return imageButton;
	}
	
	public ImageButton createImageButton(Skin skin, Vector2 position, float originX, float originY) {
		return createImageButton(skin, position, originX, originY, 0, null);
	}
	
	public ImageButton createImageButton(Skin skin, Vector2 position) {
		return createImageButton(skin, position, 0, 0, 0, null);
	}
	
	public ImageTextButton createImageTextButton(String text, Skin skin, Vector2 position, float originX, float originY, float pad, ClickListener listener) {
		ImageTextButton imageTextButton = new ImageTextButton(text, skin);
		imageTextButton.setPosition(position.x, position.y);
		imageTextButton.setOrigin(originX, originY);
		imageTextButton.pad(pad);
		imageTextButton.addListener(listener);
		actors.add(imageTextButton);
		return imageTextButton;
	}
	
	public ImageTextButton createImageTextButton(String text, Skin skin, Vector2 position, float originX, float originY) {
		return createImageTextButton(text, skin, position, originX, originY, 0, null);
	}
	
	public ImageTextButton createImageTextButton(String text, Skin skin, Vector2 position) {
		return createImageTextButton(text, skin, position, 0, 0, 0, null);
	}
	
	public TextArea createTextArea(String text, Skin skin, Vector2 position) {
		TextArea textArea = new TextArea(text, skin);
		textArea.setPosition(position.x, position.y);
		actors.add(textArea);
		return textArea;
	}
	
	public Label createLabel(String text, Skin skin, Vector2 position) {
		Label label = new Label(text, skin);
		label.setPosition(position.x, position.y);
		actors.add(label);
		return label;
	}
	
	public Table createTable() {
		Table table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for(Actor a : actors) {
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

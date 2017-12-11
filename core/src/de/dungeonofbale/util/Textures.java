package de.dungeonofbale.util;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

/**
 * Hier werden die Texturen regestriert.
 *
 */
public class Textures {
	
	private HashMap<String, Texture> textures;
	
	public Textures() {
		textures = new HashMap<String, Texture>();
		init();
	}
	
	private void init() {
		addTexture("badlogic", "badlogic.jpg");
		addTexture("player1", "player1.png");
		addTexture("player2", "player2.png");
		addTexture("player3", "player3.png");
		addTexture("background", "WaldBG.jpg");
	}
	
	public void addTexture(String key, String textureName) {
		textures.put(key, new Texture(textureName));
	}
	
	public Texture getTexture(String key) {
		return textures.get(key);
	}
	
	public HashMap<String, Texture> getTextures() {
		return textures;
	}
	
	public void disposeAll() {
		for(String keys : textures.keySet()) {
			textures.get(keys).dispose();
		}
		textures.clear();
	}
	
}

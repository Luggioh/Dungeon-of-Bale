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
		addTexture(TextureDirectory.ASSETS, "badlogic", "badlogic.jpg");
		addTexture(TextureDirectory.ASSETS, "player1", "player1.png");
		addTexture(TextureDirectory.ASSETS, "player2", "player2.png");
		addTexture(TextureDirectory.ASSETS, "player3", "player3.png");
		addTexture(TextureDirectory.ASSETS, "background", "WaldBG.jpg");
	}
	
	public void addTexture(TextureDirectory dir, String key, String textureName) {
		textures.put(key, new Texture(dir.getPath() + textureName));
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
	
	public enum TextureDirectory {
		
		ASSETS("core/assets/"), SPRITES_CHARS("core/assets/Sprites_Chars/"), EINGESETZTE_TILES("core/assets/Eingesetzte_Tiles/");
		
		private String path;
		
		private TextureDirectory(String path) {
			this.path = path;
		}
		
		public String getPath() {
			return path;
		}
		
	}
	
}

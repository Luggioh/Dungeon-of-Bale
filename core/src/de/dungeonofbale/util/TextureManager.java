package de.dungeonofbale.util;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureManager {
	
	private Map<String, TextureAtlas> textureAtlas;
	
	public TextureManager() {
		this.textureAtlas = new HashMap<>();
	}
	
	public void loadTextureAtlas(String saveName, TextureAtlas textureAtlas) {
		this.textureAtlas.put(saveName, textureAtlas);
	}
	
	public void dispose() {
		for(String keys : textureAtlas.keySet()) {
			textureAtlas.get(keys).dispose();
		}
	}
	
	public TextureAtlas getTextureAtlas(String name) {
		return this.textureAtlas.get(name);
	}
	
	public Map<String, TextureAtlas> getTextureAtlasMap() {
		return textureAtlas;
	}

}

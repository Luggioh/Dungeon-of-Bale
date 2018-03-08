package de.dungeonofbale.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class DOBAssetManager {
	
	private AssetManager assetManager;
	
	public DOBAssetManager() {
		this.assetManager = new AssetManager();
	}
	
	/**
	 * Load the images with the {@code AssetManager} from gdx
	 */
	public void loadImages() {
		String path = "core/assets/";

		File dir = new File(path);
		List<File> files = new LinkedList<>();
		
		for(File dirFiles : dir.listFiles()) {
			files.add(dirFiles);
		}
		
		for (File f : files) {
			//Skip the ui element files
			if(f.getName().equalsIgnoreCase("default.png") || f.getName().equalsIgnoreCase("uiskin.png")
					|| f.getName().equalsIgnoreCase("default.fnt") || f.getName().equalsIgnoreCase("uiskin.atlas")
					|| f.getName().equalsIgnoreCase("uiskin.json")) {
				continue;
			}
			this.assetManager.load(path + f.getName(), Texture.class);
		}
		System.out.println("Finish loading from assetmanager");
		
	}
	
	public Texture getTexture(String name) {
		return this.assetManager.get("core/assets/" + name);
	}
	
	public AssetManager getAssetManager() {
		return assetManager;
	}

}

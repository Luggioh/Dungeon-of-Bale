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
	
	public void loadImages() {
		String path = "core/assets/";
		List<File> folders = new LinkedList<>();

		File dir = new File(path);
		File current = null;

		int i = 0;

		List<File> files = new LinkedList<>();

		/*
		 * Die while Schleife geht, den ersten Ordner durch und schaut ob dort
		 * Ordner vorhanden sind. Wenn Ordner enthalten sind, fügt er sie in
		 * eine Liste hinzu. Ansonsten in die Liste "files"
		 */
		while ((!(i >= dir.listFiles().length)) && (current = dir.listFiles()[i]) != null) {
			if (current.isDirectory()) {
				folders.add(current);
			} else {
				files.add(current);
			}
			i++;
		}

		/*
		 * Hier erstelle ich eine Kopie der Liste "folder" aus dem Grund da sie,
		 * in der Forschleife (Bei der Verwendung) nicht weiter genutzt werden
		 * kann.
		 */
		List<File> copy = new LinkedList<>(folders);
		if (!folders.isEmpty()) {
			for (File folder : copy) {
				int k = 0;

				/*
				 * Die gleiche While schleife wie oben, mit einer veränderung:
				 * Die Files aus den Unter Ordnern werdedn direkt hinzugefügt.
				 */
				while ((!(k >= folder.listFiles().length)) && (current = folder.listFiles()[k]) != null) {
					if (current.isDirectory()) {
						for (File f : current.listFiles()) {
							files.add(f);
						}
					} else {
						files.add(current);
					}
					k++;
				}
			}
		}

		for (File f : files) {
			if(f.getName().equalsIgnoreCase("default.png") || f.getName().equalsIgnoreCase("uiskin.png")
					|| f.getName().equalsIgnoreCase("default.fnt") || f.getName().equalsIgnoreCase("uiskin.atlas")
					|| f.getName().equalsIgnoreCase("uiskin.json")) {
				continue;
			}
			this.assetManager.load(path + f.getName(), Texture.class);
		}
		System.out.println("Finishing loading from assetmanager");
		
	}
	
	public Texture getTexture(String name) {
		return this.assetManager.get("core/assets/" + name);
	}
	
	public AssetManager getAssetManager() {
		return assetManager;
	}

}

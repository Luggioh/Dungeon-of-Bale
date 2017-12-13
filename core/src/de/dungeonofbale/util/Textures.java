package de.dungeonofbale.util;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;

/**
 * Hier werden die Texturen regestriert.
 *
 */
public class Textures {

	private HashMap<String, Texture> textures;

	public Textures() {
		textures = new HashMap<String, Texture>();
		registerTextures();
	}

	/**
	 * Diese Methode läd alle vorhandenen Texturen. WENN neue texturen dazu
	 * kommen, bitte keinen neuen Ordner anlegen. Bitte sprecht es kurz mit mir
	 * (EncryptDev) ab, wenn ihr einen neuen Ordner anlegt. (AUSER: Wenn ihr IM
	 * assets bzw im "Sprites_Chars" oder "Eingesetzte_Tiles" Ordner einen neuen anlegt, dann geht es)
	 * 
	 * Der Key der Texturen ist jeweils der texturen namen ohne die endung. (Ohne .png bzw .jpg oder .gif)
	 */
	private void registerTextures() {

		System.out.println("[DungeonOfBale] Loading textures...");

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
			textures.put(f.getName().substring(0, f.getName().length() - 4), new Texture(f.getPath()));
		}

		System.out.println("[DungeonOfBale] Textures loaded");

	}

	public Texture getTexture(String key) {
		return textures.get(key);
	}

	public HashMap<String, Texture> getTextures() {
		return textures;
	}

	public void disposeAll() {
		for (String keys : textures.keySet()) {
			textures.get(keys).dispose();
		}
		textures.clear();
	}

	public enum TextureDirectory {

		ASSETS("core/assets/"), SPRITES_CHARS("core/assets/Sprites_Chars/"), EINGESETZTE_TILES(
				"core/assets/Eingesetzte_Tiles/");

		private String path;

		private TextureDirectory(String path) {
			this.path = path;
		}

		public String getPath() {
			return path;
		}

	}

}

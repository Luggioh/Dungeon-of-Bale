package de.dungeonofbale.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity {

	public Enemy(Vector2 position, TextureRegion texture, float rangeColliderRadius) {
		super(position, texture, rangeColliderRadius);
	}

	@Override
	public void define() {
		
	}

}

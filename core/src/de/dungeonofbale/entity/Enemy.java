package de.dungeonofbale.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity {

	public Enemy(Vector2 position, Texture texture, float rangeColliderRadius) {
		super(position, texture, rangeColliderRadius);
	}

	@Override
	public void define() {
		
	}

}

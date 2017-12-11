package de.dungeonofbale.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Player extends Entity {

	public static final float SPEED = 300;

	private OrthographicCamera camera;
	private Viewport viewport;

	public Player(Vector2 position, Texture texture, float rangeColliderRadius) {
		super(position, texture, rangeColliderRadius);
		this.camera = new OrthographicCamera();
		this.viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		this.viewport.apply();
		this.camera.position.set(position.x, position.y, 0);
	}
	
	public Viewport getViewport() {
		return viewport;
	}

	public void moveEntity(float timeSinzeLastFrame) {
		super.moveEntity(SPEED, timeSinzeLastFrame);
	}

	public void updateCamera() {
		this.camera.update();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

}

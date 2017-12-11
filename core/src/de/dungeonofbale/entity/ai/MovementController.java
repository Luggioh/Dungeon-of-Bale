package de.dungeonofbale.entity.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.dungeonofbale.entity.Entity;
import de.dungeonofbale.entity.Player;

/**
 * Der MovementController für den Spieler, bzw um etwas zu bewegen.
 * @author tobia
 *
 */
public class MovementController {

	private boolean keyUp;
	private boolean keyLeft;
	private boolean keyDown;
	private boolean keyRight;

	private int keyUpVal;
	private int keyLeftVal;
	private int keyDownVal;
	private int keyRightVal;

	private Entity toControll;

	/**
	 * 
	 * Normaler Konstructor. Keys können hier gefunden werden: {@link Keys}
	 * 
	 * @param toControll
	 * @param keyUpVal
	 * @param keyLeftVal
	 * @param keyDownVal
	 * @param keyRightVal
	 */
	public MovementController(Entity toControll, int keyUpVal, int keyLeftVal, int keyDownVal, int keyRightVal) {
		this.keyUp = false;
		this.keyLeft = false;
		this.keyDown = false;
		this.keyRight = false;
		this.keyUpVal = keyUpVal;
		this.keyLeftVal = keyLeftVal;
		this.keyDownVal = keyDownVal;
		this.keyRightVal = keyRightVal;
		this.toControll = toControll;
	}

	/**
	 * Die Methode, checkt ob der Knopf gedrückt wurde und bewegt den Spieler dann.
	 * 
	 * @param speed
	 * @param timeSinzeLastFrame
	 */
	public void move(float speed, float timeSinzeLastFrame) {
		this.keyUp = Gdx.input.isKeyPressed(keyUpVal);
		this.keyLeft = Gdx.input.isKeyPressed(keyLeftVal);
		this.keyDown = Gdx.input.isKeyPressed(keyDownVal);
		this.keyRight = Gdx.input.isKeyPressed(keyRightVal);

		if (keyUp) {
			this.toControll.getPosition().y += speed * timeSinzeLastFrame;
			((Player) toControll).getCamera().position.y = toControll.getPosition().y;
		} else if (keyLeft) {
			this.toControll.getPosition().x -= speed * timeSinzeLastFrame;
			((Player) toControll).getCamera().position.x = toControll.getPosition().x;
		} else if (keyDown) {
			this.toControll.getPosition().y -= speed * timeSinzeLastFrame;
			((Player) toControll).getCamera().position.y = toControll.getPosition().y;
		} else if (keyRight) {
			this.toControll.getPosition().x += speed * timeSinzeLastFrame;
			((Player) toControll).getCamera().position.x = toControll.getPosition().x;
		}
	}

	public boolean isKeyLeft() {
		return keyLeft;
	}

	public boolean isKeyDown() {
		return keyDown;
	}

	public int getKeyLeftVal() {
		return keyLeftVal;
	}

	public int getKeyDownVal() {
		return keyDownVal;
	}

}

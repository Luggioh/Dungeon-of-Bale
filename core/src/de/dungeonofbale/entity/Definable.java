package de.dungeonofbale.entity;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Diese Klasse dient dazu, dass jede Klasse die diese Klasse implementiert, die Methode define() hat. 
 * In dieser Methode wird der Physic Body des Sprites [Spieler, Entity what ever] erstellt.
 * @author EncryptDev
 *
 */
public interface Definable {
	
	void define();
	
	Body getBody();
	
}

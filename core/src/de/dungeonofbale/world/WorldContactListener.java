package de.dungeonofbale.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import de.dungeonofbale.entity.Enemy;
import de.dungeonofbale.entity.Entity;
import de.dungeonofbale.entity.Player;
import de.dungeonofbale.util.BitList;

/**
 * In dem WorldContactListener wird nun geschaut, ob die Shapes, bzw Fixtures nun zusammen kollidieren oder überschneiden.
 * Die Methoden Namen sagen jeweils wann welche aufgerufen wird :)
 * @author EncryptDev
 *
 */
public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA(); //fixA ist die erste Fixture
        Fixture fixB = contact.getFixtureB(); //fixB ist die zweite Fixture

        //Hier werden die categoryBits zusammengefügt. Und anhand dessen geschaut was kollidiert.
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        
        switch(cDef) {
        /*Hier werden zweimal die BIT_COLLISION_SHAPE zusammengepackt. Und wenn sie zusammen cDef ergeben, dann kollidieren
          zwei Circle Shapes. Siehe: Player/Enemy Klasse.*/
        case BitList.BIT_COLLISION_SHAPE | BitList.BIT_COLLISION_SHAPE:
        	//Hier wird der Player und der Enemy geholt. Die getEntityBy2Fixtures Methode schaut, welche Fixture was ist.
        	Player player = Entity.getEntityBy2Fixtures(fixA, fixB, Player.class);
    		Enemy enemy = Entity.getEntityBy2Fixtures(fixA, fixB, Enemy.class);
    		player.health -= enemy.getDamage();
    		System.out.println("PLAYER HEALTH: " + player.health);
        }
	}
	
	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
	
}

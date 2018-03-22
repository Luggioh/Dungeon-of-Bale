package de.dungeonofbale.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import de.dungeonofbale.entity.Enemy;
import de.dungeonofbale.entity.Player;
import de.dungeonofbale.util.BitList;

public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch(cDef) {
        case BitList.BIT_COLLISION_SHAPE | BitList.BIT_COLLISION_SHAPE:
        	if(fixA.getUserData() instanceof Player && fixB.getUserData() instanceof Enemy) {
        		((Player) fixA.getUserData()).health -= ((Enemy) fixB.getUserData()).getDamage();
        	} else if(fixB.getUserData() instanceof Player && fixA.getUserData() instanceof Enemy){
        		((Player) fixB.getUserData()).health -= ((Enemy) fixA.getUserData()).getDamage();
        	}
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
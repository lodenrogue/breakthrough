package com.littlebandit.breakthrough.gameutilities;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;

public class GameContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
            
		/*
		 * Create these two String objects to check if they are null.
		 * Some bodies may not have been assigned user data so they will
		 * return null and throw an exception.
		 */
		String idA = (String) contact.getFixtureA().getBody().getUserData();
		String idB = (String) contact.getFixtureB().getBody().getUserData();

		if (idA != null && idB != null) {
			boolean playerBlock = idA.contains("ball") && idB.contains("block");
			boolean blockPlayer = idA.contains("block") && idB.contains("ball");

			if (playerBlock) {
				Entity e = GameManager.getEntityArrayList().getEntityById(idB);
				GameManager.getEntityArrayList().removeEntity(e);
				GameInfo.addScore(100);
				e.dispose();

			}
			else if (blockPlayer) {
				Entity e = GameManager.getEntityArrayList().getEntityById(idA);
				GameManager.getEntityArrayList().removeEntity(e);
				GameInfo.addScore(100);
				e.dispose();
			}
		}

	}

	@Override
	public void endContact(Contact contact) {

		// String idA = (String)
		// contact.getFixtureA().getBody().getUserData();
		// String idB = (String)
		// contact.getFixtureB().getBody().getUserData();
		//
		// if (idA != null && idB != null) {
		// boolean playerBlock = idA.contains("ball") &&
		// idB.contains("block");
		// boolean blockPlayer = idA.contains("block") &&
		// idB.contains("ball");
		//
		// if (playerBlock) {
		// Entity e =
		// GameManager.getEntityArrayList().getEntityById(idB);
		// GameManager.getEntityArrayList().removeEntity(e);
		// e.dispose();
		//
		// }
		// else if (blockPlayer) {
		// Entity e =
		// GameManager.getEntityArrayList().getEntityById(idA);
		// GameManager.getEntityArrayList().removeEntity(e);
		// e.dispose();
		//
		// }
		// }

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}

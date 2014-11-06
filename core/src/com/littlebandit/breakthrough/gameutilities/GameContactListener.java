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
		 * Create these two String objects and check if they are null.
		 * Some bodies may not have been assigned user data so they will
		 * return null and throw an exception.
		 */
		String idA = (String) contact.getFixtureA().getBody().getUserData();
		String idB = (String) contact.getFixtureB().getBody().getUserData();

		handleCollision("ball", idA, idB, contact, true);
		handleCollision("paddle", idA, idB, contact, true);
		handleCollision("bounds", idA, idB, contact, true);
		handleBlockCollision(idA, idB, contact, true);

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

	private void handleCollision(String id, String idA, String idB, Contact contact, boolean isColliding) {
		boolean collidingA = idA.contains(id);
		boolean collidingB = idB.contains(id);

		if (collidingA) {
			Entity entityA = GameManager.getEntityArrayList().getEntityById(idA);
			if (entityA != null) {
				entityA.setIsColliding(true);
			}
		}
		else if (collidingB) {
			Entity entityB = GameManager.getEntityArrayList().getEntityById(idB);
			if (entityB != null) {
				entityB.setIsColliding(true);
			}
		}
	}

	private void handleBlockCollision(String idA, String idB, Contact contact, boolean isColliding) {
		if (idA != null && idB != null) {
			boolean ballBlock = idA.contains("ball") && idB.contains("block");
			boolean blockBall = idA.contains("block") && idB.contains("ball");

			if (ballBlock) {
				GameInfo.addScore(100);
				Entity entityB = GameManager.getEntityArrayList().getEntityById(idB);
				if (entityB != null) {
					entityB.setIsColliding(isColliding);
				}

			}
			else if (blockBall) {
				GameInfo.addScore(100);
				Entity entityA = GameManager.getEntityArrayList().getEntityById(idA);
				if (entityA != null) {
					entityA.setIsColliding(isColliding);
				}
			}
		}
	}
}
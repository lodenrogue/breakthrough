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

		handleBallCollision(idA, idB, contact, true);
		handleBlockCollision(idA, idB, contact, true);
		handlePaddleCollision(idA, idB, contact, true);

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

	private void handleBallCollision(String idA, String idB, Contact contact, boolean isColliding) {
		if (idA != null && idB != null) {
			boolean ballCollidingA = idA.contains("ball");
			boolean ballCollidingB = idB.contains("ball");

			if (ballCollidingA) {
				Entity entityA = GameManager.getEntityArrayList().getEntityById(idA);
				if (entityA != null) {
					entityA.setIsColliding(isColliding);
				}

			}
			else if (ballCollidingB) {
				Entity entityB = GameManager.getEntityArrayList().getEntityById(idB);
				if (entityB != null) {
					entityB.setIsColliding(isColliding);
				}
			}
		}
	}

	private void handlePaddleCollision(String idA, String idB, Contact contact, boolean isColliding) {
		if (idA != null && idB != null) {
			boolean ballPaddle = idA.contains("ball") && idB.contains("paddle");
			boolean paddleBall = idA.contains("paddle") && idB.contains("ball");

			if (ballPaddle) {
				Entity entityB = GameManager.getEntityArrayList().getEntityById(idB);
				if (entityB != null) {
					entityB.setIsColliding(isColliding);
				}

			}
			else if (paddleBall) {
				Entity entityA = GameManager.getEntityArrayList().getEntityById(idA);
				if (entityA != null) {
					entityA.setIsColliding(isColliding);
				}
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
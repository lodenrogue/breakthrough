package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;

/**
 * Update component implementation. Holds the values for maximum and minimum
 * velocity of the Ball entity. Checks if the Ball entity's body has a linear
 * velocity exceeding the maximum velocity and resets it's linear velocity
 * accordingly. Also checks if the body has a linear velocity lower than the
 * minimum velocity and resets it's accordingly.
 * 
 * @author Miguel Hernandez
 *
 */

public class BallVelocity implements UpdateComponent {
	public static float maxVelocity = 20f;
	public static float minVelocity = 10f;
	private boolean ballAndPaddleCollided = false;

	@Override
	public void update(Entity entity) {
		if (GameInfo.isLevelReadyToStart() && BallStartLevel.start) {
			Entity paddle = GameManager.getEntityArrayList().getEntityById("paddle");

			/*
			 * Check to see if the ball and paddle have collided. We
			 * need to know this in order to handle the x velocity.
			 */
			if (entity.isColliding() && paddle.isColliding()) {
				ballAndPaddleCollided = true;
			}

			if (ballAndPaddleCollided && entity.isColliding() && !paddle.isColliding()) {
				ballAndPaddleCollided = false;
			}

			float xVelocity = entity.getBody().getLinearVelocity().x;
			float yVelocity = entity.getBody().getLinearVelocity().y;

			if (Math.abs(xVelocity) > maxVelocity) {
				if (xVelocity < 0) {
					if (!ballAndPaddleCollided) {
						entity.getBody().setLinearVelocity(-maxVelocity, yVelocity);
					}
				}
				else {
					if (!ballAndPaddleCollided) {
						entity.getBody().setLinearVelocity(maxVelocity, yVelocity);
					}
				}
			}

			if (Math.abs(yVelocity) > maxVelocity) {
				if (yVelocity < 0) {
					entity.getBody().setLinearVelocity(xVelocity, -maxVelocity);
				}
				else {
					entity.getBody().setLinearVelocity(xVelocity, maxVelocity);
				}
			}

			if (Math.abs(xVelocity) < minVelocity) {
				if (xVelocity < 0) {
					entity.getBody().setLinearVelocity(-minVelocity, yVelocity);
				}
				else if (xVelocity > 0) {
					entity.getBody().setLinearVelocity(minVelocity, yVelocity);
				}
			}

			if (Math.abs(yVelocity) < minVelocity) {
				if (yVelocity < 0) {
					entity.getBody().setLinearVelocity(xVelocity, -minVelocity);
				}
				else if (yVelocity > 0) {
					entity.getBody().setLinearVelocity(xVelocity, minVelocity);
				}
			}

		}
		else {
			entity.getBody().setLinearVelocity(0, 0);
		}
	}
}
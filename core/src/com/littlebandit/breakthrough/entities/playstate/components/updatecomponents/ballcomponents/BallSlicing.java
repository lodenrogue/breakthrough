package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents;

import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.paddlecomponents.movement.PaddleMovement;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;

/**
 * UpdateComponent implementation for ball entity. Adds a slicing/curve effect
 * when the ball hits the paddle if they're both going in opposite directions
 * 
 * @author Miguel Hernandez
 *
 */

public class BallSlicing implements UpdateComponent {
	private float ppm = Breakthrough.PIXELS_PER_METER;
	private float sliceEffect = 1.0f;

	private boolean doSliceLeft = false;
	private boolean doSliceRight = false;

	@Override
	public void update(Entity entity) {
		Entity paddle = GameManager.getEntityArrayList().getEntityById("paddle");

		float currentXVelocity = entity.getBody().getLinearVelocity().x;

		float x = entity.getBody().getPosition().x;
		float y = entity.getBody().getPosition().y;

		float paddleWidth = paddle.getSprite().getWidth() / ppm;
		float paddleLeftX = paddle.getBody().getPosition().x - paddleWidth / 2;
		float paddleRightX = paddle.getBody().getPosition().x + paddleWidth / 2;

		// If the ball has not been reset handle slice effect
		if (!BallOutOfBounds.reset) {

			// if the paddle and ball are colliding and the paddle
			// is going at full speed.
			boolean paddleAndBallColliding = paddle.isColliding() && entity.isColliding();
			boolean paddleGoingFullSpeed = Math.abs(paddle.getBody().getLinearVelocity().x) >= PaddleMovement.velocity;

			if (paddleAndBallColliding && paddleGoingFullSpeed) {

				/*
				 * if the ball x is greater than the paddle left
				 * and less than the paddle right then do our
				 * slice effect
				 */

				boolean xGreaterThanLeft = x > paddleLeftX + paddleWidth / 10;
				boolean xLessThanRight = x < paddleRightX - paddleWidth / 10;

				if (xGreaterThanLeft && xLessThanRight) {

					// if paddle is not too close to the
					// left and right bounds
					boolean paddleNotLeftBounds = paddleLeftX > Breakthrough.VIRTUAL_WIDTH / 10 / ppm;
					boolean paddleNotRightBounds = paddleRightX < (Breakthrough.VIRTUAL_WIDTH - (Breakthrough.VIRTUAL_WIDTH / 10)) / ppm;

					if (paddleNotLeftBounds && paddleNotRightBounds) {

						/*
						 * Check which direction the
						 * ball and paddle are going and
						 * handle slicing accordingly.
						 */
						if (currentXVelocity > 0 && paddle.getBody().getLinearVelocity().x < 0) {
							doSliceRight = true;
							doSliceLeft = false;
						}
						else if (currentXVelocity < 0 && paddle.getBody().getLinearVelocity().x > 0) {
							doSliceLeft = true;
							doSliceRight = false;
						}
					}
				}
			}

			// if they're not colliding, reset our slice
			// effect variables
			else if (!paddle.isColliding() && entity.isColliding()) {
				doSliceLeft = false;
				doSliceRight = false;
			}

			// Do the actual slicing effect
			if (doSliceLeft) {
				entity.getBody().applyLinearImpulse(-sliceEffect, 0, x, y, true);
			}
			else if (doSliceRight) {
				entity.getBody().applyLinearImpulse(sliceEffect, 0, x, y, true);
			}
		}
	}
}
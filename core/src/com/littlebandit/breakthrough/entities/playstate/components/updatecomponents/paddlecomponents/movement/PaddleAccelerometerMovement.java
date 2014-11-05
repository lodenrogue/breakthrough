package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.paddlecomponents.movement;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents.BallVelocity;

/**
 * Update component implementation for paddle entity mobile accelerometer
 * movement.
 * 
 * @author Miguel Hernandez
 *
 */
public class PaddleAccelerometerMovement implements UpdateComponent {
	private boolean canMoveRight = true;
	private boolean canMoveLeft = true;

	private float ppm = Breakthrough.PIXELS_PER_METER;
	private float velocity = BallVelocity.maxVelocity * 4f;

	@Override
	public void update(Entity entity) {
		/*
		 * Check if the paddle is at the edge of the screen left and
		 * right bounds. Reset the position appropriately.
		 */

		float leftX = entity.getPosition().getX();
		float rightX = leftX + entity.getSprite().getWidth();
		float bodyY = entity.getBody().getPosition().y;
		float bodyAngle = entity.getBody().getAngle();

		if (leftX <= 2) {
			float x = ((entity.getSprite().getWidth() / 2) + 2) / ppm;
			entity.getBody().setTransform(x, bodyY, bodyAngle);
			canMoveLeft = false;

		}
		else if (rightX >= Breakthrough.VIRTUAL_WIDTH) {
			float x = (Breakthrough.VIRTUAL_WIDTH - entity.getSprite().getWidth() / 2) / ppm;
			entity.getBody().setTransform(x, bodyY, bodyAngle);
			canMoveRight = false;
		}
		else {
			canMoveLeft = true;
			canMoveRight = true;
		}

		/*
		 * Check accelerometer y value and set linear velocity
		 * accordingly.
		 */

		float newVelocity = velocity * Math.abs(Gdx.input.getAccelerometerY() / 10f);
		newVelocity = newVelocity > velocity ? velocity : newVelocity;

		if (Gdx.input.getAccelerometerY() < 0 && canMoveLeft) {
			entity.getBody().setLinearVelocity(-newVelocity, 0);
		}
		else if (Gdx.input.getAccelerometerY() > 0 && canMoveRight) {
			entity.getBody().setLinearVelocity(newVelocity, 0);
		}
		else {
			entity.getBody().setLinearVelocity(0, 0);
		}
	}
}

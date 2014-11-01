package com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

public class PaddleKeyMovement implements UpdateComponent {
	private boolean canMoveRight = true;
	private boolean canMoveLeft = true;

	private float ppm = Breakthrough.PIXELS_PER_METER;

	@Override
	public void update(Entity entity) {
		/*
		 * Check if the paddle is at the edge of the screen left and
		 * right bounds. Reset the position accordingly.
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

		// Get input keys and handle movement based on the keys pressed.

		boolean keyRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean keyLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean moveRight = keyRight && !keyLeft;
		boolean moveLeft = keyLeft && !keyRight;

		if (moveRight && canMoveRight) {
			entity.getBody().setLinearVelocity(PaddleMovement.velocity, 0);
		}
		else if (moveLeft && canMoveLeft) {
			entity.getBody().setLinearVelocity(-PaddleMovement.velocity, 0);
		}
		else {
			entity.getBody().setLinearVelocity(0, 0);
		}
	}
}

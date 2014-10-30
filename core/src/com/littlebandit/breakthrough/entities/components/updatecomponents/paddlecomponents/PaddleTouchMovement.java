package com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents.BallVelocity;
import com.littlebandit.breakthrough.gameutilities.GameManager;

/**
 * Update component implementation. Handles paddle movement based on touch
 * input.
 * 
 * @author Miguel Hernandez
 *
 */

public class PaddleTouchMovement implements UpdateComponent {
	private boolean canMoveRight = true;
	private boolean canMoveLeft = true;

	private float ppm = Breakthrough.PIXELS_PER_METER;
	private float velocity = BallVelocity.maxVelocity * 0.8f;

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

		/*
		 * Get touch position and handle movement based on the x
		 * position of the input.
		 */

		Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		GameManager.getCamera().unproject(touchPos);

		float cameraX = GameManager.getCamera().position.x;

		if (touchPos.x > cameraX && canMoveRight) {
			entity.getBody().setLinearVelocity(velocity, 0);
		}
		else if (touchPos.x < cameraX && canMoveLeft) {
			entity.getBody().setLinearVelocity(-velocity, 0);
		}
		else {
			entity.getBody().setLinearVelocity(0, 0);
		}
	}
}
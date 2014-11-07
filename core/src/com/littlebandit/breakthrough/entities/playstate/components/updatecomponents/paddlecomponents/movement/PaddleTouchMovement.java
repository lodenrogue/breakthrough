package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.paddlecomponents.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.InputHandler;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.CircStrategy;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.SimpleEaseStrategy;

/**
 * Update component implementation. Handles paddle movement based on touch
 * input.
 * 
 * @author Miguel Hernandez
 *
 */

public class PaddleTouchMovement extends InputHandler implements UpdateComponent {
	private boolean canMoveRight = true;
	private boolean canMoveLeft = true;

	private float cVelocity = 0;
	private float currentTime = 0;
	private float beginValue;
	private float endTime = 0.5f;
	private float ppm = Breakthrough.PIXELS_PER_METER;
	private Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

	private SimpleEaseStrategy moveEase = new CircStrategy();

	@Override
	public void update(Entity entity) {
		Gdx.input.setInputProcessor(this);
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

		float cameraX = GameManager.getCamera().position.x;

		// Handle movement based on x position of input.

		if (touchPos.x >= cameraX && canMoveRight && Gdx.input.isTouched()) {
			entity.getBody().setLinearVelocity(PaddleMovement.velocity, 0);
			currentTime = 0;
			beginValue = PaddleMovement.velocity;
		}
		else if (touchPos.x < cameraX && canMoveLeft && Gdx.input.isTouched()) {
			entity.getBody().setLinearVelocity(-PaddleMovement.velocity, 0);
			currentTime = 0;
			beginValue = -PaddleMovement.velocity;
		}
		else {
			// ease into zero velocity
			if (currentTime < endTime) {
				cVelocity = moveEase.ease(currentTime, beginValue, 0, endTime, EaseDirection.EASE_OUT);
				currentTime += Gdx.graphics.getDeltaTime();
			}
			entity.getBody().setLinearVelocity(cVelocity, 0);
		}

	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchPos = new Vector3(Gdx.input.getX(pointer), Gdx.input.getY(pointer), 0);
		GameManager.getCamera().unproject(touchPos);
		return true;
	}
}
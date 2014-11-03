package com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents.movement;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents.BallVelocity;
import com.littlebandit.breakthrough.gameutilities.GameInfo;

/**
 * Update component implementation for paddle movement.
 * 
 * @author Miguel Hernandez
 *
 */

public class PaddleMovement implements UpdateComponent {
	public static float velocity = BallVelocity.maxVelocity * 1.1f;
	private UpdateComponent movement;
	private UpdateComponent touchMovement = new PaddleTouchMovement();
	private UpdateComponent keyMovement = new PaddleKeyMovement();

	@Override
	public void update(Entity entity) {
		if (GameInfo.isLevelReadyToStart()) {
			if (Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS)) {
				movement = touchMovement;
			}
			else {
				movement = keyMovement;
			}
			movement.update(entity);
		}
	}
}
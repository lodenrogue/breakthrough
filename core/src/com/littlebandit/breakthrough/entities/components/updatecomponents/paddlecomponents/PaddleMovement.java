package com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
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
	public static float velocity = BallVelocity.maxVelocity;
	private UpdateComponent movement;
	private UpdateComponent touchMovement = new PaddleTouchMovement();
	private UpdateComponent keyMovement = new PaddleKeyMovement();
	private UpdateComponent accelerometerMovement = new PaddleAccelerometerMovement();

	@Override
	public void update(Entity entity) {
		if (GameInfo.isLevelReadyToStart()) {
			if (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)) {
				movement = accelerometerMovement;
			}
			else if (Gdx.input.isTouched(0)) {
				movement = touchMovement;
			}
			else {
				movement = keyMovement;
			}

			movement.update(entity);
		}

	}

}

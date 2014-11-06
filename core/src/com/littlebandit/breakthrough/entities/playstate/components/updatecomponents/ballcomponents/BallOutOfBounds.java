package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents;

import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.GameInfo;

/**
 * Update component implementation. Checks to see if the ball is out of bounds
 * and handles resetting it's position.
 * 
 * @author Miguel Hernandez
 *
 */

public class BallOutOfBounds implements UpdateComponent {
	public static boolean reset = false;

	/**
	 * Pixels per meter
	 */
	private float ppm = Breakthrough.PIXELS_PER_METER;

	@Override
	public void update(Entity entity) {
		reset = false;

		/**
		 * Check to see if we are out of bounds!
		 */
		if (entity.getPosition().getY() < -20) {
			reset = true;
			GameInfo.subtractPlayerLives(1);

			float x = Breakthrough.VIRTUAL_WIDTH / 2 / ppm;
			float y = 200 / ppm;

			entity.getBody().setTransform(x, y, entity.getBody().getAngle());
			entity.getBody().setLinearVelocity(BallVelocity.minVelocity, BallVelocity.maxVelocity);
		}
	}
}
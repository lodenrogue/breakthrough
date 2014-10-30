package com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents;

import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

/**
 * Update component implementation. Checks to see if the ball is out of bounds
 * and handles resetting it's position.
 * 
 * @author Miguel Hernandez
 *
 */

public class BallBounds implements UpdateComponent {
	private float ppm = Breakthrough.PIXELS_PER_METER;

	@Override
	public void update(Entity entity) {
		if (entity.getPosition().getY() < -20) {
			float x = Breakthrough.VIRTUAL_WIDTH / 2 / ppm;
			float y = 200 / ppm;

			entity.getBody().setTransform(x, y, entity.getBody().getAngle());
			entity.getBody().setLinearVelocity(BallVelocity.minVelocity, BallVelocity.maxVelocity);
		}
	}
}
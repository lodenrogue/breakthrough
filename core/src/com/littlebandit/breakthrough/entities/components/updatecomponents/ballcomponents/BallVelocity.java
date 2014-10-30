package com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

public class BallVelocity implements UpdateComponent {
	public static float maxVelocity = 30f;
	public static float minVelocity = 8f;

	@Override
	public void update(Entity entity) {
		float xVelocity = entity.getBody().getLinearVelocity().x;
		float yVelocity = entity.getBody().getLinearVelocity().y;

		if (Math.abs(xVelocity) > maxVelocity) {
			if (xVelocity < 0) {
				entity.getBody().setLinearVelocity(-maxVelocity, yVelocity);
			}
			else {
				entity.getBody().setLinearVelocity(maxVelocity, yVelocity);
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
	}
}
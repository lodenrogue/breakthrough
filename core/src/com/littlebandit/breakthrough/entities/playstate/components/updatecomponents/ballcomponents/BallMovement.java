package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

/**
 * Update component implementation for ball entity movement. Houses movement
 * logic.
 * 
 * @author Miguel Hernandez
 *
 */
public class BallMovement implements UpdateComponent {
	private UpdateComponent slicing = new BallSlicing();
	private UpdateComponent velocity = new BallVelocity();
	private UpdateComponent bounds = new BallOutOfBounds();
	private UpdateComponent trail = new BallTrail();

	@Override
	public void update(Entity entity) {
		slicing.update(entity);
		velocity.update(entity);
		bounds.update(entity);
		trail.update(entity);
	}
}

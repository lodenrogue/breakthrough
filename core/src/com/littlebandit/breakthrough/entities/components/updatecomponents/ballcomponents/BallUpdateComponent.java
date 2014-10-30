package com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.PositionUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

/**
 * Update component implementation for the Ball entity.
 * 
 * @author Miguel Hernandez
 *
 */

public class BallUpdateComponent implements UpdateComponent {
	private UpdateComponent position = new PositionUpdateComponent();
	private UpdateComponent bounds = new BallBounds();
	private UpdateComponent velocity = new BallVelocity();

	@Override
	public void update(Entity entity) {
		position.update(entity);
		bounds.update(entity);
		velocity.update(entity);
	}
}
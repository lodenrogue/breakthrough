package com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.DebugUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.PositionUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents.movement.PaddleMovement;

/**
 * Update component implementation for the Paddle Entity.
 * 
 * @author Miguel Hernandez
 *
 */

public class PaddleUpdateComponent implements UpdateComponent {
	private UpdateComponent position = new PositionUpdateComponent();
	private UpdateComponent movement = new PaddleMovement();
	private UpdateComponent collision = new PaddleCollision();
	private UpdateComponent debug = new DebugUpdateComponent();

	@Override
	public void update(Entity entity) {
		position.update(entity);
		movement.update(entity);
		collision.update(entity);
		debug.update(entity);

	}

}

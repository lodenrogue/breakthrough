package com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.PositionUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

public class PaddleUpdateComponent implements UpdateComponent {
	private UpdateComponent position = new PositionUpdateComponent();
	private UpdateComponent movement = new PaddleMovement();

	@Override
	public void update(Entity entity) {
		position.update(entity);
		movement.update(entity);

	}

}

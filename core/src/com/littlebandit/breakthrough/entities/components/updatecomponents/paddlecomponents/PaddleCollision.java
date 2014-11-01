package com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

public class PaddleCollision implements UpdateComponent {
	private boolean doCollisionAnimation;

	@Override
	public void update(Entity entity) {
		if (entity.isColliding()) {
			doCollisionAnimation = true;
			entity.setIsColliding(false);
		}
		
		if (doCollisionAnimation) {

		}

	}

}

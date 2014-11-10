package com.littlebandit.breakthrough.entities.components.updatecomponents.boundscomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

public class BoundsUpdateComponent implements UpdateComponent {

	@Override
	public void update(Entity entity) {
		if (entity.isColliding()) {
			entity.setIsColliding(false);
		}

	}

}

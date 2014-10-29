package com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.PositionUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

public class BallUpdateComponent implements UpdateComponent {
	private UpdateComponent position = new PositionUpdateComponent();
	
	@Override
	public void update(Entity entity) {
		position.update(entity);

	}

}

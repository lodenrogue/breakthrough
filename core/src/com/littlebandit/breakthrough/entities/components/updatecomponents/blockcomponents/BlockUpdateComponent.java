package com.littlebandit.breakthrough.entities.components.updatecomponents.blockcomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.DebugUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.PositionUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

public class BlockUpdateComponent implements UpdateComponent {
	private UpdateComponent position = new PositionUpdateComponent();
	private UpdateComponent debug = new DebugUpdateComponent();
	private UpdateComponent ease = new BlockEase();
	private UpdateComponent collision = new BlockCollision();

	@Override
	public void update(Entity entity) {
		position.update(entity);
		ease.update(entity);
		collision.update(entity);
		debug.update(entity);

	}

}

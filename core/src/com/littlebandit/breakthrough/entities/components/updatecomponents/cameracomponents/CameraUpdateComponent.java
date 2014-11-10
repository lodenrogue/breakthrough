package com.littlebandit.breakthrough.entities.components.updatecomponents.cameracomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

public class CameraUpdateComponent implements UpdateComponent {
	private UpdateComponent shake = new Shake();

	@Override
	public void update(Entity entity) {
		shake.update(entity);

	}

}

package com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

public class PaddleMovement implements UpdateComponent {
	private UpdateComponent touchMovement = new PaddleTouchMovement();
	private UpdateComponent keyMovement = new PaddleKeyMovement();

	@Override
	public void update(Entity entity) {
		if (Gdx.input.isTouched()) {
			touchMovement.update(entity);
		}
		else {
			keyMovement.update(entity);
		}

	}

}

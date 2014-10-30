package com.littlebandit.breakthrough.entities.components.updatecomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents.BallVelocity;

public class DebugUpdateComponent implements UpdateComponent {

	@Override
	public void update(Entity entity) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			entity.getBody().setTransform(entity.getResetPosition().getX(), entity.getResetPosition().getY(), 0);

			if (!entity.getId().equalsIgnoreCase("ball")) {
				entity.getBody().setLinearVelocity(0, 0);
			}
			else {
				entity.getBody().setLinearVelocity(BallVelocity.minVelocity, BallVelocity.maxVelocity);
			}

			entity.getBody().setAngularVelocity(0);
		}
	}

}

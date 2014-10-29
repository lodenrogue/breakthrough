package com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.GameManager;

public class PaddleTouchMovement implements UpdateComponent {
	private boolean canMoveRight = true;
	private boolean canMoveLeft = true;
	private float ppm = Breakthrough.PIXELS_PER_METER;

	@Override
	public void update(Entity entity) {
		float leftX = entity.getPosition().getX();
		float rightX = leftX + entity.getSprite().getWidth();
		float bodyY = entity.getBody().getPosition().y;
		float bodyAngle = entity.getBody().getAngle();

		if (leftX <= 2) {
			float x = ((entity.getSprite().getWidth() / 2) + 2) / ppm;
			entity.getBody().setTransform(x, bodyY, bodyAngle);
			canMoveLeft = false;

		}
		else if (rightX >= Breakthrough.VIRTUAL_WIDTH) {
			float x = (Breakthrough.VIRTUAL_WIDTH - entity.getSprite().getWidth() / 2) / ppm;
			entity.getBody().setTransform(x, bodyY, bodyAngle);
			canMoveRight = false;
		}
		else {
			canMoveLeft = true;
			canMoveRight = true;
		}

		Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		GameManager.getCamera().unproject(touchPos);

		float cameraX = GameManager.getCamera().position.x;

		if (touchPos.x > cameraX && canMoveRight) {
			entity.getBody().setLinearVelocity(30, 0);
		}
		else if (touchPos.x < cameraX && canMoveLeft) {
			entity.getBody().setLinearVelocity(-30, 0);
		}
		else {
			entity.getBody().setLinearVelocity(0, 0);
		}

	}

}

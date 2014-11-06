package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.cameracomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents.BallVelocity;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;

public class Shake implements UpdateComponent {
	private Vector2[] shakePoints;
	private boolean startShake = false;

	private float interval;
	private float currentTime;
	private float baseX = GameManager.getCamera().position.x;
	private float baseY = GameManager.getCamera().position.y;

	private int pointCount = 0;

	@Override
	public void update(Entity entity) {
		Entity ball = GameManager.getEntityArrayList().getEntityById("ball");
		float ballXVelocity = ball.getBody().getLinearVelocity().x;

		if (ball.isColliding() && Math.abs(ballXVelocity) > BallVelocity.maxVelocity) {
			interval = 0.05f;
			currentTime = interval;
			shakePoints = generatePoints(5);
			pointCount = 0;
			startShake = true;
		}

		if (startShake) {
			currentTime += Gdx.graphics.getDeltaTime();

			if (currentTime >= interval && pointCount < shakePoints.length - 1) {
				GameManager.getCamera().position.set(baseX + shakePoints[pointCount].x, baseY + shakePoints[pointCount].y, 0);
				pointCount++;
				currentTime = 0;
			}

			if (currentTime >= interval && pointCount == shakePoints.length - 1) {
				GameManager.getCamera().position.set(baseX, baseY, 0);
				startShake = false;
			}
		}
	}

	private Vector2[] generatePoints(int points) {
		Vector2[] newPoints = new Vector2[points];

		for (int i = 0; i < newPoints.length; i++) {
			// Used to generate a random selection of
			// positive or negative values
			int sideX = MathUtils.random(1);
			int sideY = MathUtils.random(1);

			float x = (newPoints.length + 2) - i;
			float y = (newPoints.length + 2) - i;

			if (sideX == 1) {
				x = -x;
			}
			if (sideY == 1) {
				y = -y;
			}

			newPoints[i] = new Vector2(x, y);
		}

		return newPoints;
	}
}
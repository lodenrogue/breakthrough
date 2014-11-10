package com.littlebandit.breakthrough.entities.components.updatecomponents.tweens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.SimpleEaseStrategy;

/**
 * Performs Tween on entity body. Dividing by pixels per meter value is
 * recommended.
 * 
 * @author Miguel Hernandez
 *
 */
public class BodyPositionTween extends Tween {
	private float beginValueX, beginValueY, endValueX, endValueY;

	public BodyPositionTween(float startTime, Vector2 beginPosition, Vector2 endPosition, float endTime, EaseDirection direction, SimpleEaseStrategy strategy) {
		super(startTime, beginPosition, endPosition, endTime, direction, strategy);
		beginValueX = beginPosition.x;
		beginValueY = beginPosition.y;
		endValueX = endPosition.x;
		endValueY = endPosition.y;
	}

	@Override
	public void update(Entity entity) {
		if (start) {
			doTween = true;
			currentTime = startTime;
			entity.getBody().setTransform(beginPosition.x, beginPosition.y, entity.getBody().getAngle());
			start = false;
		}
		if (doTween) {
			if (currentTime < endTime) {
				float newX = easeStrategy.ease(currentTime, beginValueX, endValueX, endTime, direction);
				float newY = easeStrategy.ease(currentTime, beginValueY, endValueY, endTime, direction);

				entity.getBody().setTransform(newX, newY, entity.getBody().getAngle());
				currentTime += Gdx.graphics.getDeltaTime();
			}
			else {
				entity.getBody().setTransform(endPosition, entity.getBody().getAngle());
				doTween = false;
			}
		}
	}
}
package com.littlebandit.breakthrough.entities.components.updatecomponents.tweens;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.SimpleEaseStrategy;

/**
 * Performs tween on entity sprite alpha value.
 * 
 * @author Miguel Hernandez
 *
 */
public class AlphaTween extends Tween {

	public AlphaTween(float startTime, float beginValue, float endValue, float endTime, EaseDirection direction, SimpleEaseStrategy strategy) {
		super(startTime, beginValue, endValue, endTime, direction, strategy);
	}

	@Override
	public void update(Entity entity) {
		if (start) {
			doTween = true;
			currentTime = startTime;
			entity.getSprite().setAlpha(beginValue);
			start = false;
		}
		if (doTween) {
			if (currentTime < endTime) {
				float alpha = easeStrategy.ease(currentTime, beginValue, endValue, endTime, direction);
				entity.getSprite().setAlpha(alpha);
				currentTime += Gdx.graphics.getDeltaTime();
			}
			else {
				entity.getSprite().setAlpha(endValue);
				doTween = false;
			}
		}
	}
}
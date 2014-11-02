package com.littlebandit.breakthrough.entities.components.updatecomponents.tweens;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.SimpleEaseStrategy;

public class ScaleTween extends Tween {

	public ScaleTween(float startTime, float beginValue, float endValue, float endTime, EaseDirection direction, SimpleEaseStrategy strategy) {
		super(startTime, beginValue, endValue, endTime, direction, strategy);
	}

	@Override
	public void update(Entity entity) {
		if (start) {
			doTween = true;
			currentTime = startTime;
			entity.getSprite().setScale(beginValue);
			start = false;
		}
		if (doTween) {
			if (currentTime < endTime) {
				float scale = easeStrategy.ease(currentTime, beginValue, endValue, endTime, direction);
				entity.getSprite().setScale(scale);
				currentTime += Gdx.graphics.getDeltaTime();
			}
			if (currentTime >= endTime) {
				entity.getSprite().setScale(endValue);
				doTween = false;
			}
		}
	}

}

package com.littlebandit.breakthrough.entities.components.updatecomponents.tweens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.entityutilities.Animateable;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.SimpleEaseStrategy;

public class AnimationScaleTween extends Tween {
	private Array<Sprite> sprites;

	public AnimationScaleTween(float startTime, float beginValue, float endValue, float endTime, EaseDirection direction, SimpleEaseStrategy strategy) {
		super(startTime, beginValue, endValue, endTime, direction, strategy);
	}

	@Override
	public void update(Entity entity) {
		Animateable anim = (Animateable) entity;
		sprites = anim.getAnimation();

		if (start) {
			doTween = true;
			currentTime = startTime;
			entity.getSprite().setScale(beginValue);
			start = false;
		}
		if (doTween) {
			if (currentTime < endTime) {
				float scale = easeStrategy.ease(currentTime, beginValue, endValue, endTime, direction);
				for (Sprite s : sprites) {
					s.setScale(scale);
				}
				currentTime += Gdx.graphics.getDeltaTime();
			}
			if (currentTime >= endTime) {
				for (Sprite s : sprites) {
					s.setScale(endValue);
					doTween = false;
				}
			}
		}

	}

}

package com.littlebandit.breakthrough.entities.components.updatecomponents.blockcomponents;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.ScaleTween;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.Tween;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.BackStrategy;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;

public class BlockCollision implements UpdateComponent {
	private float time = 0;
	private boolean startCollision = false;
	private float endTime = (float) (Math.random() * 0.3f) + 0.3f;
	private Tween scaleTween = new ScaleTween(0, 1, 0, endTime, EaseDirection.EASE_IN, new BackStrategy());

	@Override
	public void update(Entity entity) {
		if (entity.isColliding()) {
			scaleTween.start();
			entity.setIsColliding(false);
			startCollision = true;
		}
		scaleTween.update(entity);

		if (startCollision) {
			time += Gdx.graphics.getDeltaTime();
			if (time >= endTime) {
				entity.dispose();
			}
		}

	}

}
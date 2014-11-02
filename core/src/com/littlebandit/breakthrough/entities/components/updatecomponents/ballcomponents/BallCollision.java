package com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.ScaleTween;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.Tween;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.ElasticStrategy;

public class BallCollision implements UpdateComponent {
	private Tween scaleTween = new ScaleTween(0, 2.4f, 1, 1.5f, EaseDirection.EASE_OUT, new ElasticStrategy());

	@Override
	public void update(Entity entity) {
		if (entity.isColliding()) {
			scaleTween.start();
			entity.setIsColliding(false);
		}
		scaleTween.update(entity);
	}
}
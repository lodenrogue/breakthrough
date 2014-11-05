package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.paddlecomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.ScaleTween;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.Tween;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.ElasticStrategy;

/**
 * Update component implementation for paddle entity collision logic.
 * 
 * @author Miguel Hernandez
 *
 */
public class PaddleCollision implements UpdateComponent {
	private Tween scaleTween = new ScaleTween(0, 1.5f, 1, 1.5f, EaseDirection.EASE_OUT, new ElasticStrategy());

	@Override
	public void update(Entity entity) {
		// if has collided start the tween.
		if (entity.isColliding()) {
			scaleTween.start();
			entity.setIsColliding(false);
		}

		// update the tween.
		scaleTween.update(entity);
	}
}
package com.littlebandit.breakthrough.entities.components.updatecomponents.blockcomponents;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.ScaleTween;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.Tween;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.BackStrategy;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;

/**
 * Update component implementation for block entity collision logic.
 * 
 * @author Miguel Hernandez
 *
 */
public class BlockDestruction implements UpdateComponent {
	private boolean startCollision = false;
	private float time = 0;
	private float endTime = (float) (Math.random() * 0.3f) + 0.3f;
	private Tween scaleTween = new ScaleTween(0, 1, 0, endTime, EaseDirection.EASE_IN, new BackStrategy());

	@Override
	public void update(Entity entity) {
		// start the collision process
		if (!startCollision) {
			scaleTween.start();
			entity.setIsColliding(false);
			startCollision = true;
		}

		// update the tween
		scaleTween.update(entity);

		// if collision tween has started update the time and dispose of
		// the entity when the timer has reached it's target.
		if (startCollision) {
			time += Gdx.graphics.getDeltaTime();
			if (time >= endTime) {
				entity.dispose();
			}
		}

	}

}
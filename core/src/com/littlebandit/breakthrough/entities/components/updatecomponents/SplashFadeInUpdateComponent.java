package com.littlebandit.breakthrough.entities.components.updatecomponents;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.AlphaTween;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.Tween;
import com.littlebandit.breakthrough.entities.entityutilities.Link;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.QuadStrategy;

/**
 * Update component implementation for a fade in effect.
 * 
 * @author Miguel Hernandez
 *
 */
public class SplashFadeInUpdateComponent implements UpdateComponent {
	private boolean startFadeInTween = false;

	private float fadeTime = 0.7f;
	private float currentTime = 0;
	private Tween alphaFadeTween = new AlphaTween(0, 0, 1, fadeTime, EaseDirection.EASE_IN, new QuadStrategy());

	@Override
	public void update(Entity entity) {

		// if this link is ready to start
		Link link = (Link) entity;
		if (link.isReadyToStart()) {

			// start the fade in tween
			if (!startFadeInTween) {
				startFadeInTween = true;
				alphaFadeTween.start();
			}

			// update the tween
			if (startFadeInTween) {
				alphaFadeTween.update(entity);
			}

			// increment the time
			currentTime += Gdx.graphics.getDeltaTime();

			// if we've reached our target time then start the next
			// link
			if (currentTime >= fadeTime + 2.0f) {
				link.startNextLink();
			}
		}
	}
}
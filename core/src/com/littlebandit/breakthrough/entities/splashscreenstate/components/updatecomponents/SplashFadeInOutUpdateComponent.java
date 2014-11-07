package com.littlebandit.breakthrough.entities.splashscreenstate.components.updatecomponents;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.AlphaTween;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.Tween;
import com.littlebandit.breakthrough.gameutilities.Link;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.QuadStrategy;

/**
 * Update component implementation for fade in and fade out effect.
 * 
 * @author Miguel Hernandez
 *
 */
public class SplashFadeInOutUpdateComponent implements UpdateComponent {
	private boolean startFadeInTween = false;
	private boolean startFadeOutTween = false;

	private float fadeTime = 0.7f;
	private float currentTime = 0;
	private Tween alphaFadeTween = new AlphaTween(0, 0, 1, fadeTime, EaseDirection.EASE_IN, new QuadStrategy());

	@Override
	public void update(Entity entity) {

		// Check if this link is ready to start
		Link link = (Link) entity;
		if (link.isReadyToStart()) {

			// start the fade in tween
			if (!startFadeInTween) {
				startFadeInTween = true;
				alphaFadeTween.start();
			}

			// increment the time
			currentTime += Gdx.graphics.getDeltaTime();

			// if we have reached our target time then start the
			// fade out tween and reset the time.
			if (currentTime >= fadeTime + 2.0f && !startFadeOutTween) {
				startFadeOutTween = true;
				alphaFadeTween = new AlphaTween(0, 1, 0, fadeTime, EaseDirection.EASE_IN, new QuadStrategy());
				alphaFadeTween.start();
				currentTime = 0f;
			}

			// update the tween
			if (startFadeInTween || startFadeOutTween) {
				alphaFadeTween.update(entity);
			}

			// if we've reached our target time and we're doing the
			// fade out tween then start the next link
			if (currentTime >= fadeTime && startFadeInTween && startFadeOutTween) {
				link.startNext(GameManager.getNextLink());
			}
		}

	}
}

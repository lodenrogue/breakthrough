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

public class SplashFadeInUpdateComponent implements UpdateComponent {
	private boolean startFadeInTween = false;

	private float fadeTime = 0.7f;
	private float currentTime = 0;
	private Tween alphaFadeTween = new AlphaTween(0, 0, 1, fadeTime, EaseDirection.EASE_IN, new QuadStrategy());

	@Override
	public void update(Entity entity) {
		Link link = (Link) entity;
		if (link.isReadyToStart()) {
			if (!startFadeInTween) {
				startFadeInTween = true;
				alphaFadeTween.start();
			}

			if (startFadeInTween) {
				alphaFadeTween.update(entity);
			}

			currentTime += Gdx.graphics.getDeltaTime();

			if (currentTime >= fadeTime + 2.0f) {
				link.startNext(GameManager.getNextLink());
			}
		}
	}
}
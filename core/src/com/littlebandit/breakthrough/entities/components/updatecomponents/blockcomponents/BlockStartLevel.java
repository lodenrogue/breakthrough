package com.littlebandit.breakthrough.entities.components.updatecomponents.blockcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.BodyPositionTween;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.ScaleTween;
import com.littlebandit.breakthrough.entities.components.updatecomponents.tweens.Tween;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.CircStrategy;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.ElasticStrategy;

public class BlockStartLevel implements UpdateComponent {
	private float ppm = Breakthrough.PIXELS_PER_METER;
	private boolean startTween = false;
	private float time = 0;
	private boolean readyToStart = false;
	private float endValue = 1;
	private float endTime = (float) (Math.random() * 1f) + 0.5f;
	private Vector2 beginPosition;
	private Vector2 endPosition;

	private Tween scaleTween = new ScaleTween(time, 0, endValue, endTime, EaseDirection.EASE_IN, new CircStrategy());
	private Tween positionTween;

	@Override
	public void update(Entity entity) {
		if (!startTween) {
			startTween = true;

			float beginX = entity.getBody().getPosition().x;
			float beginY = entity.getBody().getPosition().y + ((((float) Math.random() * 50f)) + 50f) / ppm;

			beginPosition = new Vector2(beginX, beginY);
			endPosition = new Vector2(entity.getBody().getPosition().x, entity.getBody().getPosition().y);

			positionTween = new BodyPositionTween(time, beginPosition, endPosition, endTime, EaseDirection.EASE_IN, new ElasticStrategy());

			positionTween.start();
			scaleTween.start();
		}
		if (startTween && !readyToStart) {
			scaleTween.update(entity);
			positionTween.update(entity);

			time += Gdx.graphics.getDeltaTime();

			if (time >= 1.5f && !readyToStart) {
				readyToStart = true;
				entity.getSprite().setScale(endValue);
				entity.getBody().setTransform(endPosition, entity.getBody().getAngle());
				GameInfo.setIsLevelReadyToStart(true);
			}
		}

	}
}

package com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseStrategy;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.ElasticStrategy;

public class BallCollision implements UpdateComponent {
	private boolean doCollisionAnimation = false;
	private boolean doGetSmall = false;

	private float scaleTime = 0f;
	private float scaleBeginValue;
	private float scaleEndValue;
	private float scaleEndTime;

	private EaseStrategy scaleStrategy = new ElasticStrategy();

	@Override
	public void update(Entity entity) {
		if (entity.isColliding()) {
			doCollisionAnimation = true;
			doGetSmall = true;
			scaleTime = 0f;
			entity.getSprite().setScale(1.5f);
			entity.setIsColliding(false);
		}

		if (doCollisionAnimation) {
			if (doGetSmall) {
				if (scaleTime == 0f) {
					scaleEndTime = (float) (Math.random() * 0.5f) + 0.5f;
					scaleBeginValue = entity.getSprite().getScaleX();
					scaleEndValue = 1;
				}
				if (scaleTime < scaleEndTime) {
					float scale = scaleStrategy.easeOut(scaleTime, scaleBeginValue, scaleEndValue, scaleEndTime);
					entity.getSprite().setScale(scale);
					scaleTime += Gdx.graphics.getDeltaTime();
				}
				if (scaleTime >= scaleEndTime) {
					doGetSmall = false;
					scaleTime = 0f;
				}
			}
		}
	}
}
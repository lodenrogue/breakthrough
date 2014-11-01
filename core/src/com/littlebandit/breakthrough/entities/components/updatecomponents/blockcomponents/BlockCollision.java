package com.littlebandit.breakthrough.entities.components.updatecomponents.blockcomponents;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.BackStrategy;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseStrategy;

public class BlockCollision implements UpdateComponent {
	private boolean doDestroyAnimation = false;

	private float scaleTime = 0;
	private float scaleBeginValue = 1;
	private float scaleEndValue = 0;
	private float scaleEndTime = 0;

	private EaseStrategy scaleStrategy = new BackStrategy();

	@Override
	public void update(Entity entity) {
		if (entity.isColliding()) {
			doDestroyAnimation = true;
			entity.setIsColliding(false);
		}

		if (doDestroyAnimation) {
			if (scaleEndTime == 0f) {
				scaleEndTime = (float) (Math.random() * 0.3f) + 0.5f;
			}
			if (scaleTime < scaleEndTime) {
				float scale = scaleStrategy.easeIn(scaleTime, scaleBeginValue, scaleEndValue, scaleEndTime);
				entity.getSprite().setScale(scale);
				scaleTime += Gdx.graphics.getDeltaTime();
			}

			if (scaleTime >= scaleEndTime) {
				entity.dispose();
			}

		}

	}
}

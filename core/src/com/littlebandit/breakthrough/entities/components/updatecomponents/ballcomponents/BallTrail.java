package com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.managers.ParticleManager;

/**
 * Update component implementation for ball entity trail effect.
 * 
 * @author Miguel Hernandez
 *
 */
public class BallTrail implements UpdateComponent {
	ParticleEffect trail;

	@Override
	public void update(Entity entity) {
		Sprite sprite = entity.getSprite();

		trail = ParticleManager.getParticleEffect("trail");
		trail.setPosition(entity.getPosition().getX() + sprite.getWidth() / 2, entity.getPosition().getY() + sprite.getHeight() / 2);
		trail.update(Gdx.graphics.getDeltaTime());

	}

}

package com.littlebandit.breakthrough.entities.playstate.components.rendercomponents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.rendercomponents.RenderComponent;
import com.littlebandit.breakthrough.gameutilities.managers.ParticleManager;

/**
 * Render component implementation for ball entity.
 * 
 * @author Miguel Hernandez
 *
 */
public class BallRenderComponent implements RenderComponent {

	@Override
	public void render(Entity entity, SpriteBatch batch) {
		entity.getSprite().draw(batch);
		ParticleManager.getParticleEffect("trail").draw(batch);

	}

}

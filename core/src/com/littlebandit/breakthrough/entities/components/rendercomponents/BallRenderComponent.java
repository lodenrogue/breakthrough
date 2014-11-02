package com.littlebandit.breakthrough.entities.components.rendercomponents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.gameutilities.managers.ParticleManager;

public class BallRenderComponent implements RenderComponent {

	@Override
	public void render(Entity entity, SpriteBatch batch) {
		ParticleManager.getParticleEffect("trail").draw(batch);
		entity.getSprite().draw(batch);

	}

}

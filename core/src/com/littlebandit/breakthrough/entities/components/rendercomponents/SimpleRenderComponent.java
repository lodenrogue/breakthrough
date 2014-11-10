package com.littlebandit.breakthrough.entities.components.rendercomponents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.entities.Entity;

/**
 * Simple implementation of render component interface.
 * 
 * @author Miguel Hernandez
 *
 */

public class SimpleRenderComponent implements RenderComponent {

	@Override
	public void render(Entity entity, SpriteBatch batch) {
		entity.getSprite().draw(batch);
	}
}

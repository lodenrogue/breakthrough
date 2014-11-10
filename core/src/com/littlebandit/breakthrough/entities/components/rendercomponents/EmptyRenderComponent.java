package com.littlebandit.breakthrough.entities.components.rendercomponents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.entities.Entity;

/**
 * Empty RenderComponent implementation for entities not needing render
 * capabilities.
 * 
 * @author Miguel Hernandez
 *
 */
public class EmptyRenderComponent implements RenderComponent {

	@Override
	public void render(Entity entity, SpriteBatch batch) {

	}
}

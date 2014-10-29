package com.littlebandit.breakthrough.entities.components.rendercomponents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.entities.Entity;

/**
 * Render component interface.
 * 
 * @author Miguel Hernandez
 *
 */

public interface RenderComponent {

	public void render(Entity entity, SpriteBatch batch);
}

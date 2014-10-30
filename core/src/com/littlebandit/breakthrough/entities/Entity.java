package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.littlebandit.breakthrough.entities.components.Position;
import com.littlebandit.breakthrough.entities.components.rendercomponents.RenderComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;

/**
 * Interface representing a game entity.
 * 
 * @author Miguel Hernandez
 *
 */

public interface Entity {

	public void setUpdateComponent(UpdateComponent updateComponent);

	public void update();

	public void setRenderComponent(RenderComponent renderComponent);

	public void render(SpriteBatch batch);

	public String getId();

	public void setId(String id);

	public void setPosition(float x, float y);

	public Position getPosition();

	public void setSprite(Sprite sprite);

	public Sprite getSprite();
	
	public void setBody(Body body);
	
	public Body getBody();
	
	public void dispose();
	
	public Position getResetPosition();

}

package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.littlebandit.breakthrough.entities.components.Position;
import com.littlebandit.breakthrough.entities.components.rendercomponents.RenderComponent;
import com.littlebandit.breakthrough.entities.components.rendercomponents.SimpleRenderComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.PositionUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.managers.WorldManager;

/**
 * Simple entity interface implementation. This class needs to be inherited.
 * Handles simple set up and render/update calls.
 * 
 * @author Miguel Hernandez
 *
 */

public abstract class SimpleEntity implements Entity {
	protected String id;
	protected Position position;
	protected Position resetPostion;
	protected Sprite sprite;
	protected Body body;
	protected RenderComponent renderComponent;
	protected UpdateComponent updateComponent;

	public SimpleEntity(String id, Sprite sprite, float x, float y) {
		this.id = id;
		position = new Position();
		resetPostion = new Position();
		setPosition(x, y);
		setUpdateComponent(new PositionUpdateComponent());
		setRenderComponent(new SimpleRenderComponent());
		setSprite(sprite);
	}

	@Override
	public void setRenderComponent(RenderComponent renderComponent) {
		this.renderComponent = renderComponent;
	}

	@Override
	public void render(SpriteBatch batch) {
		renderComponent.render(this, batch);
	}

	@Override
	public void setUpdateComponent(UpdateComponent updateComponent) {
		this.updateComponent = updateComponent;
	}

	@Override
	public void update() {
		updateComponent.update(this);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setPosition(float x, float y) {
		position.setPosition(x, y);
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public Position getResetPosition() {
		return resetPostion;
	}

	@Override
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void setBody(Body body) {
		this.body = body;
		resetPostion.setPosition(body.getPosition().x, body.getPosition().y);
	}

	@Override
	public Body getBody() {
		return body;
	}

	@Override
	public final void dispose() {
		WorldManager.addBodyToBeDestroyed(body);
		disposeAll();
	}

	public abstract void disposeAll();

}
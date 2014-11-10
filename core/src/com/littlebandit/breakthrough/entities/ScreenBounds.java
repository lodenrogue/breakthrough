package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.components.rendercomponents.EmptyRenderComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.boundscomponents.BoundsUpdateComponent;

/**
 * Screen bounds entity.
 * 
 * @author Miguel Hernandez
 *
 */
public class ScreenBounds extends SimpleEntity {

	public ScreenBounds(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		setRenderComponent(new EmptyRenderComponent());
		setUpdateComponent(new BoundsUpdateComponent());
	}

	@Override
	public void disposeAll() {
		// TODO Auto-generated method stub

	}

}

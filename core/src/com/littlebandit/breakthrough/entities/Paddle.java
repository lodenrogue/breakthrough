package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents.PaddleUpdateComponent;

/**
 * Paddle entity.
 * 
 * @author Miguel Hernandez
 *
 */
public class Paddle extends SimpleEntity {

	public Paddle(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		setUpdateComponent(new PaddleUpdateComponent());
	}

	@Override
	public void disposeAll() {
		// TODO Auto-generated method stub

	}

}

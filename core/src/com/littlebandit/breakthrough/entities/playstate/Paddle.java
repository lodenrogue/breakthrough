package com.littlebandit.breakthrough.entities.playstate;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.SimpleEntity;
import com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.paddlecomponents.PaddleUpdateComponent;

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

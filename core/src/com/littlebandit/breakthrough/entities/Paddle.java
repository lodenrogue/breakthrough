package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents.PaddleUpdateComponent;

public class Paddle extends SimpleEntity {

	public Paddle(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		setUpdateComponent(new PaddleUpdateComponent());
	}

}

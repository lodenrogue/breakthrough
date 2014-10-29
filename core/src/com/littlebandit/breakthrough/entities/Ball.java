package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents.BallUpdateComponent;

public class Ball extends SimpleEntity {

	public Ball(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		setUpdateComponent(new BallUpdateComponent());
	}

	@Override
	public void disposeAll() {
		// TODO Auto-generated method stub
		
	}

}

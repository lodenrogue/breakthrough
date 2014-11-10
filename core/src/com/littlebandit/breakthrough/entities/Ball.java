package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.components.rendercomponents.BallRenderComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents.BallUpdateComponent;

/**
 * Ball Entity.
 * 
 * @author Miguel Hernandez
 *
 */

public class Ball extends SimpleEntity {
	private float startX, startY;

	public Ball(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		startX = x;
		startY = y;
		setUpdateComponent(new BallUpdateComponent());
		setRenderComponent(new BallRenderComponent());
	}

	@Override
	public void disposeAll() {
		// TODO Auto-generated method stub

	}
	
	public float getStartX(){
		return startX;
	}
	
	public float getStartY(){
		return startY;
	}
	

}

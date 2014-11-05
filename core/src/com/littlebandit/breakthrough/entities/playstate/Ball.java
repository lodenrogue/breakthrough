package com.littlebandit.breakthrough.entities.playstate;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.SimpleEntity;
import com.littlebandit.breakthrough.entities.playstate.components.rendercomponents.BallRenderComponent;
import com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents.BallUpdateComponent;

/**
 * Ball Entity.
 * 
 * @author Miguel Hernandez
 *
 */

public class Ball extends SimpleEntity {

	public Ball(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		setUpdateComponent(new BallUpdateComponent());
		setRenderComponent(new BallRenderComponent());
	}

	@Override
	public void disposeAll() {
		// TODO Auto-generated method stub

	}

}

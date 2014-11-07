package com.littlebandit.breakthrough.entities.playstate;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.SimpleEntity;
import com.littlebandit.breakthrough.entities.components.rendercomponents.EmptyRenderComponent;
import com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.cameracomponents.CameraUpdateComponent;

/**
 * Camera entity.
 * 
 * @author Miguel Hernandez
 *
 */
public class Camera extends SimpleEntity {

	public Camera(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		setRenderComponent(new EmptyRenderComponent());
		setUpdateComponent(new CameraUpdateComponent());
	}

	@Override
	public void disposeAll() {
		// TODO Auto-generated method stub

	}

}

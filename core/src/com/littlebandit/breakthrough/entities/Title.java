package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.components.updatecomponents.SplashFadeInUpdateComponent;
import com.littlebandit.breakthrough.entities.entityutilities.Link;

/**
 * Title entity. Implements the link interface.
 * 
 * @author Miguel Hernandez
 *
 */
public class Title extends SimpleEntity implements Link {
	private boolean start = false;

	public Title(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		setUpdateComponent(new SplashFadeInUpdateComponent());
	}

	@Override
	public void disposeAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		start = true;

	}

	@Override
	public void startNext(Link link) {
		start = false;
		if (link != null) {
			link.start();
		}

	}

	@Override
	public boolean isReadyToStart() {
		return start;
	}

}

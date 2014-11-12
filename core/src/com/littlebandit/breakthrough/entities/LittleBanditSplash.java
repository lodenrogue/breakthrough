package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.components.updatecomponents.SplashFadeInOutUpdateComponent;
import com.littlebandit.breakthrough.entities.entityutilities.Link;

/**
 * LittleBandit splash screen entity. Implements the link interface.
 * 
 * @author Miguel Hernandez
 *
 */
public class LittleBanditSplash extends SimpleEntity implements Link {
	private boolean start = false;

	public LittleBanditSplash(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		setUpdateComponent(new SplashFadeInOutUpdateComponent());
	}

	@Override
	public void start() {
		start = true;

	}

	@Override
	public boolean isReadyToStart() {
		return start;
	}

	@Override
	public void startNext(Link link) {
		start = false;
		if (link != null) {
			link.start();
		}
	}

	@Override
	public void disposeAll() {
		// TODO Auto-generated method stub

	}

}
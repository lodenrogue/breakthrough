package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class BaseEntity extends SimpleEntity {

	public BaseEntity(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
	}

	@Override
	public void disposeAll() {
	}
}

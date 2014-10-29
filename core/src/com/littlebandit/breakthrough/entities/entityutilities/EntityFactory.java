package com.littlebandit.breakthrough.entities.entityutilities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.TestEntity;

public class EntityFactory {

	private EntityFactory() {

	}

	public static Entity createTestEntity(String id, Sprite sprite, float x, float y) {
		Entity e = new TestEntity(id, sprite, x, y);
		return e;
	}

}

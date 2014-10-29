package com.littlebandit.breakthrough.entities.entityutilities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.Paddle;
import com.littlebandit.breakthrough.entities.TestEntity;
import com.littlebandit.breakthrough.gameutilities.GameManager;

public class EntityFactory {
	private static float ppm = Breakthrough.PIXELS_PER_METER;

	private EntityFactory() {

	}

	public static Entity createTestEntity(String id, Sprite sprite, float x, float y) {
		Entity e = new TestEntity(id, sprite, x, y);
		return e;
	}

	public static Entity createPaddle(String id, Sprite sprite, float x, float y) {
		Entity e = new Paddle(id, sprite, x, y);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / ppm, sprite.getHeight() / 2 / ppm);

		Body body = GameManager.getWorld().createBody(createKinematicBody());
		body.createFixture(shape, 1.0f);
		body.setTransform(x / ppm, y / ppm, 0);
		e.setBody(body);

		shape.dispose();
		return e;
	}

	private static BodyDef createKinematicBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;

		return bodyDef;
	}

	// private static FixtureDef createFixtureDef(Shape shape, float
	// density, float friction, float restitution) {
	// FixtureDef fixtureDef = new FixtureDef();
	// fixtureDef.shape = shape;
	// fixtureDef.density = density;
	// fixtureDef.friction = friction;
	// fixtureDef.restitution = restitution;
	//
	// return fixtureDef;
	// }

}

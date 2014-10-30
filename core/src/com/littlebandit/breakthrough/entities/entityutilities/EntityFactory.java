package com.littlebandit.breakthrough.entities.entityutilities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Ball;
import com.littlebandit.breakthrough.entities.Block;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.Paddle;
import com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents.BallVelocity;
import com.littlebandit.breakthrough.gameutilities.GameManager;

/**
 * Entity factory. Used to create instances of game entities.
 * 
 * @author Miguel Hernandez
 *
 */

public class EntityFactory {
	private static float ppm = Breakthrough.PIXELS_PER_METER;

	private EntityFactory() {

	}

	public static Entity createBall(String id, Sprite sprite, float x, float y) {
		Entity e = new Ball(id, sprite, x, y);

		CircleShape shape = new CircleShape();
		shape.setRadius(sprite.getWidth() / 2 / ppm);

		Body body = GameManager.getWorld().createBody(createDynamicBody());
		body.createFixture(createFixtureDef(shape, 1.0f, 0.0f, 1.0f));
		body.setTransform(x / ppm, y / ppm, 0);
		body.setLinearVelocity(BallVelocity.minVelocity, BallVelocity.maxVelocity);
		e.setBody(body);

		shape.dispose();
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

	public static Entity createBlock(String id, Sprite sprite, float x, float y) {
		Entity e = new Block(id, sprite, x, y);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / ppm, sprite.getHeight() / 2 / ppm);
		
		Body body = GameManager.getWorld().createBody(createDynamicBody());
		body.createFixture(createFixtureDef(shape, 1.0f, 0.0f, 1.0f));
		body.setAngularDamping(1f);
		body.setLinearDamping(0.3f);
		body.setTransform(x / ppm, y / ppm, 0);
		e.setBody(body);
		
		shape.dispose();
		return e;
	}

	public static void createScreenBounds() {
		EdgeShape es = new EdgeShape();

		float rightX = Breakthrough.VIRTUAL_WIDTH / ppm;
		float leftX = 2 / ppm;
		float topY = (Breakthrough.VIRTUAL_HEIGHT - 1) / ppm;
		float bottomY = 0;

		Body body = GameManager.getWorld().createBody(createStaticBody());

		// ---Right Side --- //
		es.set(rightX, topY, rightX, bottomY);
		body.createFixture(es, 1.0f);

		// ---Left Side--- //
		es.set(leftX, topY, leftX, bottomY);
		body.createFixture(es, 1.0f);

		// ---Top Side--- //
		es.set(leftX, topY, rightX, topY);
		body.createFixture(es, 1.0f);

		es.dispose();

	}

	private static BodyDef createStaticBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;

		return bodyDef;
	}

	private static BodyDef createKinematicBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;

		return bodyDef;
	}

	private static BodyDef createDynamicBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;

		return bodyDef;
	}

	private static FixtureDef createFixtureDef(Shape shape, float density, float friction, float restitution) {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density;
		fixtureDef.friction = friction;
		fixtureDef.restitution = restitution;

		return fixtureDef;
	}
}

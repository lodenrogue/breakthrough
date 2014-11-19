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
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Ball;
import com.littlebandit.breakthrough.entities.BaseEntity;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.LinkEntity;
import com.littlebandit.breakthrough.entities.Paddle;
import com.littlebandit.breakthrough.entities.components.rendercomponents.AnimationRenderComponent;
import com.littlebandit.breakthrough.entities.components.rendercomponents.EmptyRenderComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.SplashFadeInOutUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.SplashFadeInUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents.BallVelocity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.blockcomponents.BlockUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.boundscomponents.BoundsUpdateComponent;
import com.littlebandit.breakthrough.entities.components.updatecomponents.paddlecomponents.PaddleUpdateComponent;
import com.littlebandit.breakthrough.gameutilities.managers.WorldManager;

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

		Body body = WorldManager.getWorld().createBody(createDynamicBody());
		body.createFixture(createFixtureDef(shape, 1.0f, 0.0f, 1.0f));
		body.setTransform(x / ppm, y / ppm, 0);
		body.setLinearVelocity(BallVelocity.minVelocity, BallVelocity.maxVelocity);
		body.setUserData(id);
		e.setBody(body);

		shape.dispose();
		return e;
	}

	public static Entity createPaddle(String id, Sprite sprite, Array<Sprite> animation, float frameDuration, float x, float y) {
		Entity e = new Paddle(id, sprite, animation, frameDuration, x, y);
		e.setUpdateComponent(new PaddleUpdateComponent());
		e.setRenderComponent(new AnimationRenderComponent());

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / ppm, sprite.getHeight() / 2 / ppm);

		Body body = WorldManager.getWorld().createBody(createKinematicBody());
		body.createFixture(shape, 1.0f);
		body.setTransform(x / ppm, y / ppm, 0);
		body.setUserData(id);
		e.setBody(body);

		shape.dispose();
		return e;
	}

	public static Entity createBlock(String id, Sprite sprite, float x, float y) {
		Entity e = new BaseEntity(id, sprite, x, y);
		e.setUpdateComponent(new BlockUpdateComponent());

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / ppm, sprite.getHeight() / 2 / ppm);

		Body body = WorldManager.getWorld().createBody(createStaticBody());
		body.createFixture(shape, 1.0f);
		body.setTransform(x / ppm, y / ppm, 0);
		body.setUserData(id);
		e.setBody(body);

		shape.dispose();
		return e;
	}

	public static Entity createScreenBounds(String id, Sprite sprite, float x, float y) {
		Entity e = new BaseEntity(id, sprite, x, y);
		e.setRenderComponent(new EmptyRenderComponent());
		e.setUpdateComponent(new BoundsUpdateComponent());

		EdgeShape es = new EdgeShape();

		float rightX = Breakthrough.VIRTUAL_WIDTH / ppm;
		float leftX = 2 / ppm;
		float topY = (Breakthrough.VIRTUAL_HEIGHT - 1) / ppm;
		float bottomY = 0;

		Body body = WorldManager.getWorld().createBody(createStaticBody());

		// ---Right Side --- //
		es.set(rightX, topY, rightX, bottomY);
		body.createFixture(es, 1.0f);

		// ---Left Side--- //
		es.set(leftX, topY, leftX, bottomY);
		body.createFixture(es, 1.0f);

		// ---Top Side--- //
		es.set(leftX, topY, rightX, topY);
		body.createFixture(es, 1.0f);

		body.setUserData(id);
		body.setTransform(x / ppm, y / ppm, 0);
		e.setBody(body);

		es.dispose();

		return e;

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

	public static LinkEntity createTitle(String id, Sprite sprite, float x, float y) {
		LinkEntity e = new LinkEntity(id, sprite, x, y);
		e.setUpdateComponent(new SplashFadeInUpdateComponent());
		return e;
	}

	public static LinkEntity createLittleBanditSplash(String id, Sprite sprite, float x, float y) {
		LinkEntity e = new LinkEntity(id, sprite, x, y);
		e.setUpdateComponent(new SplashFadeInOutUpdateComponent());
		return e;
	}
}

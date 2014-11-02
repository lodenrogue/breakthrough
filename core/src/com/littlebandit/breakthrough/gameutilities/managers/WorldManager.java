package com.littlebandit.breakthrough.gameutilities.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class WorldManager {
	private static Array<Body> destroyBodyQueue = new Array<Body>();
	private static World world;

	private WorldManager() {

	}

	public static World createWorld(Vector2 gravity, boolean doSleep) {
		world = new World(gravity, doSleep);
		return world;
	}

	public static World getWorld() {
		return world;
	}

	/**
	 * Destroys all bodies in the world.
	 */
	public static void destroyAllBodies() {
		// First have to remove the bodies in the queue so we don't get
		// a cross reference error.
		destroyBodiesInQueue();

		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		for (Body b : bodies) {
			world.destroyBody(b);
		}
	}

	public static void addBodyToBeDestroyed(Body body) {
		destroyBodyQueue.add(body);
	}

	/**
	 * Handles world step method and destroying bodies in queue.
	 */
	public static void updateWorld() {
		world.step(Gdx.graphics.getDeltaTime(), 6, 3);
		destroyBodiesInQueue();
	}

	/**
	 * Iterates through all bodies in destroyBodyQueue destroys them from
	 * the world.
	 */
	private static void destroyBodiesInQueue() {
		if (destroyBodyQueue.size > 0) {
			for (Body b : destroyBodyQueue) {
				world.destroyBody(b);
			}
			destroyBodyQueue.clear();
		}

	}
}

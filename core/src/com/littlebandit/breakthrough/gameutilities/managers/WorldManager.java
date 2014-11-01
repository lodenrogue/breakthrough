package com.littlebandit.breakthrough.gameutilities.managers;

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

	public static void addBodyToBeDestroyed(Body body) {
		destroyBodyQueue.add(body);
	}

	public static void updateWorld() {
		world.step(1 / 60f, 6, 3);
		destroyBodiesInQueue();
	}

	private static void destroyBodiesInQueue() {
		if (destroyBodyQueue.size > 0) {
			for (Body b : destroyBodyQueue) {
				world.destroyBody(b);
			}
			destroyBodyQueue.clear();
		}
		
	}
}

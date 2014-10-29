package com.littlebandit.breakthrough.gameutilities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.littlebandit.breakthrough.states.GameStateManager;

public class GameManager {
	private static World world;
	private static GameStateManager gsm;
	private static OrthographicCamera camera;

	private GameManager() {

	}

	public static void setWorld(World world) {
		GameManager.world = world;
	}

	public static World getWorld() {
		return world;
	}

	public static void setGameStateManager(GameStateManager gsm) {
		GameManager.gsm = gsm;
	}

	public static GameStateManager getGameStateManager() {
		return gsm;
	}

	public static void setCamera(OrthographicCamera camera) {
		GameManager.camera = camera;
	}

	public static OrthographicCamera getCamera() {
		return camera;
	}

}

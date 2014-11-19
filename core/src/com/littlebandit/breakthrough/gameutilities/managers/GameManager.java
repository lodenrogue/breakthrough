package com.littlebandit.breakthrough.gameutilities.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;

/**
 * Game manager utility class. Holds game objects.
 * 
 * @author Miguel Hernandez
 *
 */

public class GameManager {
	private static GameStateManager gsm;
	private static OrthographicCamera camera;
	private static EntityArrayList entities;

	private GameManager() {

	}

	/**
	 * Sets the EntityArrayList.
	 * 
	 * @param entities
	 */
	public static void setEntityArrayList(EntityArrayList entities) {
		GameManager.entities = entities;
	}

	/**
	 * 
	 * @return Returns the EntityArrayList
	 */
	public static EntityArrayList getEntityArrayList() {
		return entities;
	}

	/**
	 * Sets the GameStateManager.
	 * 
	 * @param gsm GameStateManager.
	 */
	public static void setGameStateManager(GameStateManager gsm) {
		GameManager.gsm = gsm;
	}

	/**
	 * 
	 * @return Returns the GameStateManager.
	 */
	public static GameStateManager getGameStateManager() {
		return gsm;
	}

	/**
	 * Sets the main game camera.
	 * 
	 * @param camera Main game camera.
	 */
	public static void setCamera(OrthographicCamera camera) {
		GameManager.camera = camera;
	}

	/**
	 * 
	 * @return Returns the main game camera.
	 */
	public static OrthographicCamera getCamera() {
		return camera;
	}
}
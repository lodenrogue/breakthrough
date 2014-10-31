package com.littlebandit.breakthrough.gameutilities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;
import com.littlebandit.breakthrough.states.GameStateManager;

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
	
	public static void setEntityArrayList(EntityArrayList entities){
		GameManager.entities = entities;
	}
	
	public static EntityArrayList getEntityArrayList(){
		return entities;
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

package com.littlebandit.breakthrough.gameutilities.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;
import com.littlebandit.breakthrough.gameutilities.Link;

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
	private static Array<Link> links;
	private static int currentLink = 0;

	private GameManager() {

	}

	public static void setLinksArray(Array<Link> links) {
		GameManager.links = links;
	}

	public static void addLink(Link link) {
		links.add(link);
	}

	public static Link getLink() {
		return links.get(currentLink);
	}

	public static Link getNextLink() {
		currentLink++;
		if (currentLink < links.size) {
			return links.get(currentLink);
		}
		return null;
	}

	public static Link getPreviousLink() {
		currentLink--;
		if (currentLink >= 0) {
			return links.get(currentLink);
		}
		return null;
	}

	public static Array<Link> getLinks() {
		return links;
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
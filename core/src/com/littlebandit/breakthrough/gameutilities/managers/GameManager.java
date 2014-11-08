package com.littlebandit.breakthrough.gameutilities.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;
import com.littlebandit.breakthrough.entities.entityutilities.Link;

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

	/**
	 * Sets the Link objects array.
	 * 
	 * @param links
	 */
	public static void setLinksArray(Array<Link> links) {
		GameManager.links = links;
	}

	/**
	 * Adds a link to the array.
	 * 
	 * @param link
	 */
	public static void addLink(Link link) {
		links.add(link);
	}

	/**
	 * Returns the current link.
	 * 
	 * @return
	 */
	public static Link getLink() {
		return links.get(currentLink);
	}

	/**
	 * Returns the next link or null if there is none.
	 * 
	 * @return
	 */
	public static Link getNextLink() {
		if (currentLink + 1 < links.size) {
			currentLink++;
			return links.get(currentLink);
		}
		return null;
	}

	/**
	 * Returns the previous link or null if there is none.
	 * 
	 * @return
	 */
	public static Link getPreviousLink() {
		if (currentLink - 1 >= 0) {
			currentLink--;
			return links.get(currentLink);
		}
		return null;
	}

	/**
	 * Returns the links array.
	 * 
	 * @return
	 */
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
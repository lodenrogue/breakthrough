package com.littlebandit.breakthrough.gameutilities.map;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.entityutilities.EntityFactory;
import com.littlebandit.breakthrough.gameutilities.managers.TextureManager;

public class MapBuilder {
	private static Array<String> lines;

	private MapBuilder(String fileName) {
	}

	/**
	 * Builds a level based on a given fileName. Creates and adds entities
	 * to the supplied Array.
	 * 
	 * @param fileName Name of the file containing map data.
	 * @param entities Entity Array where entities will be added when map is
	 *                built.
	 * @param leftMostX Left most X position for map.
	 * @param topMostY Top most Y position for map.
	 * @param xSpacing Amount of space between entity origins in the X axis.
	 *                (Usually, at least, the width of the entity.)
	 * @param ySpacing Amount of space between entity origins in the Y axis.
	 *                (Usually, at least, the height of the entity.)
	 */
	public static void buildLevelMap(String fileName, Array<Entity> entities, float leftMostX, float topMostY, float xSpacing, float ySpacing) {
		lines = getLines(fileName);
		createEntities(lines, entities, leftMostX, topMostY, xSpacing, ySpacing);
	}

	/**
	 * Creates and adds maps entities to the Entity Array.
	 * 
	 * @param lines Array holding the map data lines.
	 * @param entities Entity Array where newly created entities will be
	 *                added.
	 * @param leftMostX Left most X position for map.
	 * @param topMostY Top most Y position for map.
	 * @param xSpacing Amount of space between entity origins in the X axis.
	 *                (Usually, at least, the width of the entity.)
	 * @param ySpacing Amount of space between entity origins in the Y axis.
	 *                (Usually, at least, the height of the entity.)
	 */
	private static void createEntities(Array<String> lines, Array<Entity> entities, float leftMostX, float topMostY, float xSpacing, float ySpacing) {
		float x = leftMostX;
		float y = topMostY;
		int idNumber = 0;

		for (String s : lines) {
			x = leftMostX;
			char[] chars = s.toCharArray();

			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == 'B') {
					Sprite sprite = new Sprite(TextureManager.getTexture("block"));
					entities.add(EntityFactory.createBlock("block" + idNumber, sprite, x, y));
					idNumber++;
				}
				if (chars[i] == 'x' || chars[i] == 'B') {
					x += xSpacing;
				}
			}
			y -= ySpacing;
		}
	}

	/**
	 * Reads a file and returns map data lines in the form of a String
	 * Array.
	 * 
	 * @param fileName The name of the file containing the map data.
	 * @return
	 */
	private static Array<String> getLines(String fileName) {
		Array<String> array = new Array<String>();

		try {
			BufferedReader in = new BufferedReader(Gdx.files.internal(fileName).reader());
			String line = "";

			while ((line = in.readLine()) != null) {
				array.add(line);
			}

			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return array;
	}
}
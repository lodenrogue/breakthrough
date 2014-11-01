package com.littlebandit.breakthrough.gameutilities.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.entityutilities.EntityFactory;
import com.littlebandit.breakthrough.gameutilities.managers.TextureManager;

public class MapBuilder {
	private static String path = "";
	private static Array<String> lines;

	private MapBuilder(String fileName) {
		this.getClass().getClassLoader().getResource("").getPath();
		path = this.getClass().getClassLoader().getResource("").getPath();
		path = path.substring(0, path.indexOf("desktop"));
		path += "android/assets/" + fileName;
	}

	private String getPath() {
		return path;
	}

	public static void buildLevelMap(String fileName, Array<Entity> entities, float startingX, float startingY, float xIncrement, float yIncrement) {
		MapBuilder mapBuilder = new MapBuilder(fileName);
		lines = getLines(mapBuilder.getPath());
		createEntities(lines, entities, startingX, startingY, xIncrement, yIncrement);
	}

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

	private static Array<String> getLines(String pathToFile) {
		Array<String> array = new Array<String>();

		try {
			BufferedReader in = new BufferedReader(new FileReader(pathToFile));
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

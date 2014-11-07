package com.littlebandit.breakthrough.gameutilities.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Manager for texture resources.
 * 
 * @author Miguel Hernandez
 *
 */
public class TextureManager {
	private static HashMap<String, Texture> textures;

	private TextureManager() {

	}

	/**
	 * Initializes this manager. Adds the textures to the texture hash map.
	 */
	public static void initialize() {
		textures = new HashMap<String, Texture>();

		textures.put("background1", new Texture(Gdx.files.internal("Background-1.png")));
		textures.put("background2", new Texture(Gdx.files.internal("Background-2.png")));
		textures.put("background3", new Texture(Gdx.files.internal("Background-3.png")));
		textures.put("background4", new Texture(Gdx.files.internal("Background-4.png")));

		textures.put("title", new Texture(Gdx.files.internal("title.png")));
		textures.put("littlebanditlogo", new Texture(Gdx.files.internal("LittleBanditLogo.png")));

		textures.put("ball", new Texture(Gdx.files.internal("newBall.png")));
		textures.put("paddle", new Texture(Gdx.files.internal("newPaddle.png")));
		textures.put("block", new Texture(Gdx.files.internal("block.png")));
	}

	/**
	 * Returns a texture given a key.
	 * 
	 * @param key Key referencing a texture.
	 * @return Returns a texture matching the given key.
	 */
	public static Texture getTexture(String key) {
		return textures.get(key);
	}

	/**
	 * Disposes all texture resources in the internal hash map.
	 */
	public static void dispose() {
		for (String key : textures.keySet()) {
			textures.get(key).dispose();
		}
	}
}
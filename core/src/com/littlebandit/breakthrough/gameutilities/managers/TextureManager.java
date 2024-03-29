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
		textures.put("title", new Texture(Gdx.files.internal("title.png")));
		textures.put("littlebanditlogo", new Texture(Gdx.files.internal("LittleBanditLogo.png")));

		textures.put("ball", new Texture(Gdx.files.internal("Ball00.png")));

		textures.put("paddle00", new Texture(Gdx.files.internal("Paddle00.png")));
		textures.put("paddle01", new Texture(Gdx.files.internal("Paddle01.png")));

		textures.put("block00", new Texture(Gdx.files.internal("Block00.png")));
		textures.put("block01", new Texture(Gdx.files.internal("Block01.png")));
		textures.put("block02", new Texture(Gdx.files.internal("Block02.png")));
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
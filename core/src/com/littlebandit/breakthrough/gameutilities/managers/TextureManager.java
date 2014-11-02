package com.littlebandit.breakthrough.gameutilities.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
	private static HashMap<String, Texture> textures;

	private TextureManager() {

	}

	public static void initialize() {
		textures = new HashMap<String, Texture>();
		textures.put("ball", new Texture(Gdx.files.internal("ball.png")));
		textures.put("paddle", new Texture(Gdx.files.internal("paddle.png")));
		textures.put("block", new Texture(Gdx.files.internal("block.png")));
	}

	public static Texture getTexture(String key) {
		return textures.get(key);
	}

	public static void dispose() {
		for (String key : textures.keySet()) {
			textures.get(key).dispose();
		}
	}

}

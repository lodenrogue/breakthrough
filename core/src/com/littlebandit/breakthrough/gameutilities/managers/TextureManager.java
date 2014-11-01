package com.littlebandit.breakthrough.gameutilities.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
	private static Texture ball, paddle, block;
	private static HashMap<String, Texture> textures;

	private TextureManager() {

	}

	public static void initialize() {
		textures = new HashMap<String, Texture>();

		ball = new Texture(Gdx.files.internal("ball.png"));
		paddle = new Texture(Gdx.files.internal("paddle.png"));
		block = new Texture(Gdx.files.internal("block.png"));

		textures.put("ball", ball);
		textures.put("paddle", paddle);
		textures.put("block", block);

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

package com.littlebandit.breakthrough.gameutilities.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Manager for audio files.
 * 
 * @author Miguel Hernandez
 *
 */

public class AudioManager {
	private static HashMap<String, Music> music;

	private AudioManager() {

	}

	/**
	 * Initialize manager. Creates hash map and adds audio files.
	 */
	public static void initialize() {
		music = new HashMap<String, Music>();
		music.put("dizzy", Gdx.audio.newMusic(Gdx.files.internal("01 A Night Of Dizzy Spells.mp3")));
		playMusic("dizzy", true, 0.1f);

	}

	/**
	 * Plays a music object matching the given key.
	 * 
	 * @param key Key referencing the music object.
	 * @param looping If the music object should loop during play back.
	 * @param volume Play back volume.
	 */
	public static void playMusic(String key, boolean looping, float volume) {
		music.get(key).setLooping(looping);
		music.get(key).setVolume(volume);
		music.get(key).play();
	}

	/**
	 * Gets a music object matching a given key.
	 * 
	 * @param key String key referencing music object.
	 * @return Music object.
	 */
	public static Music getMusic(String key) {
		return music.get(key);
	}

	/**
	 * Disposes all audio assets.
	 */
	public static void dispose() {
		for (String key : music.keySet()) {
			music.get(key).dispose();
		}
	}
}
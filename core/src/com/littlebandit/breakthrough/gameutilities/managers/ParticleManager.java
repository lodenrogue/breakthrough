package com.littlebandit.breakthrough.gameutilities.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

/**
 * Manager for particle effects.
 * 
 * @author Miguel Hernandez
 *
 */
public class ParticleManager {
	private static HashMap<String, ParticleEffect> particleEffects;
	private static boolean renderTrail = false;

	private ParticleManager() {

	}

	/**
	 * Initializes this manager. Creates the particleEffects hashmap and
	 * adds the individual particle effects to it.
	 */
	public static void initialize() {
		particleEffects = new HashMap<String, ParticleEffect>();

		ParticleEffect trail = new ParticleEffect();
		trail.load(Gdx.files.internal("trail.pe"), Gdx.files.internal(""));

		particleEffects.put("trail", trail);
	}

	/**
	 * Resets all particle effects.
	 */
	public static void reset() {
		for (String key : particleEffects.keySet()) {
			particleEffects.get(key).getEmitters().get(0).setContinuous(false);
			particleEffects.get(key).reset();

		}

		renderTrail = false;
	}

	/**
	 * Adds a particle effects to the internal hashmap given a key and a
	 * ParticleEffect object.
	 * 
	 * @param key String key for the particle effect.
	 * @param particleEffect ParticleEffect object being added to the map.
	 */
	public static void addParticleEffect(String key, ParticleEffect particleEffect) {
		particleEffects.put(key, particleEffect);
	}

	/**
	 * Returns a particle effect matching a given key.
	 * 
	 * @param key String key for the requested particle effect.
	 * @return returns the particle effect matching the key.
	 */
	public static ParticleEffect getParticleEffect(String key) {
		return particleEffects.get(key);
	}

	public static void startParticleEffect(String key) {
		particleEffects.get(key).start();
		if(key.equals("trail")){
			renderTrail = true;
		}
	}

	/**
	 * Disposes a particle effect matching a given key.
	 * 
	 * @param key String key referencing the particle effect that should be
	 *                disposed.
	 */
	public static void disposeParticleEffect(String key) {
		ParticleEffect p = particleEffects.get(key);
		particleEffects.remove(key);
		p.dispose();
	}

	/**
	 * Disposes and removes all particle effects from this manager.F
	 */
	public static void dispose() {
		for (String key : particleEffects.keySet()) {
			particleEffects.get(key).dispose();
			particleEffects.remove(key);
		}
	}

	public static boolean doRenderTrail() {
		return renderTrail;
	}
}
package com.littlebandit.breakthrough.gameutilities.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class ParticleManager {
	private static HashMap<String, ParticleEffect> particleEffects;

	private ParticleManager() {

	}

	public static void initialize() {
		particleEffects = new HashMap<String, ParticleEffect>();

		ParticleEffect trail = new ParticleEffect();
		trail.load(Gdx.files.internal("trail.pe"), Gdx.files.internal(""));

		particleEffects.put("trail", trail);
	}

	public static void reset() {
		for (String key : particleEffects.keySet()) {
			particleEffects.get(key).getEmitters().get(0).setContinuous(false);
			particleEffects.get(key).reset();
			
			
		}
	}

	public static void addParticleEffect(String key, ParticleEffect particle) {
		particleEffects.put(key, particle);
	}

	public static ParticleEffect getParticleEffect(String key) {
		return particleEffects.get(key);
	}

	public static void disposeParticleEffect(String key) {
		ParticleEffect p = particleEffects.get(key);
		particleEffects.remove(key);
		p.dispose();
	}

	public static void dispose() {
		for (String key : particleEffects.keySet()) {
			particleEffects.get(key).dispose();
			particleEffects.remove(key);
		}
	}

}

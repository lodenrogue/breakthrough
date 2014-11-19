package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.entities.entityutilities.Animateable;

/**
 * Paddle entity.
 * 
 * @author Miguel Hernandez
 *
 */
public class Paddle extends SimpleEntity implements Animateable {
	Array<Sprite> animation;
	float frameDuration;

	public Paddle(String id, Sprite sprite, Array<Sprite> animation, float frameDuration, float x, float y) {
		super(id, sprite, x, y);
		this.animation = animation;
		this.frameDuration = frameDuration;
	}

	@Override
	public void disposeAll() {
	}

	public void setAnimation(Array<Sprite> animaton, float frameDuration) {
		setAnimation(animaton);
		setAnimationDuration(frameDuration);
	}

	@Override
	public void setAnimation(Array<Sprite> animation) {
		this.animation = animation;

	}

	@Override
	public Array<Sprite> getAnimation() {
		return animation;
	}

	@Override
	public void setAnimationDuration(float frameDuration) {
		this.frameDuration = frameDuration;

	}

	@Override
	public float getAnimationDuration() {
		return frameDuration;
	}
}
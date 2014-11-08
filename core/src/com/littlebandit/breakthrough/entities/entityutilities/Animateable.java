package com.littlebandit.breakthrough.entities.entityutilities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

/**
 * Interface for object that are animateable.
 * 
 * @author Miguel Hernandez
 *
 */
public interface Animateable {

	public void setAnimation(Array<Sprite> animation);

	public Array<Sprite> getAnimation();

	public void setAnimationDuration(float frameDuration);

	public float getAnimationDuration();

}

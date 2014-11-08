package com.littlebandit.breakthrough.entities.components.rendercomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.entityutilities.Animateable;

/**
 * Render component implementation handling animations.
 * 
 * @author Miguel Hernandez
 *
 */
public class AnimationRenderComponent implements RenderComponent {
	private float stateTime = 0f;
	private int currentFrame = 0;
	private Array<Sprite> sprites;

	@Override
	public void render(Entity entity, SpriteBatch batch) {
		Animateable anim = (Animateable) entity;
		// get the sprites in animation
		sprites = anim.getAnimation();

		// get the current sprite and the time between frames
		Sprite currentSprite = sprites.get(currentFrame);
		float frameDuration = anim.getAnimationDuration();

		// update the position of the current sprite
		currentSprite.setPosition(entity.getPosition().getX(), entity.getPosition().getY());

		// set the current sprite as the entity sprite and draw it
		entity.setSprite(currentSprite);
		entity.getSprite().draw(batch);

		// increase the state time
		stateTime += Gdx.graphics.getDeltaTime();

		// if the state time is greater than or equal to the frame
		// duration increase the frame and reset the state time
		if (stateTime >= frameDuration) {
			stateTime = 0;
			currentFrame++;

			// if the current frame index is higher than the number
			// of frames, reset the current frame index
			if (currentFrame >= sprites.size) {
				currentFrame = 0;
			}
		}
	}
}
package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.entities.playstate.Ball;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.managers.ParticleManager;

/**
 * Update component implementation. Checks to see if the ball is out of bounds
 * and handles resetting it's position.
 * 
 * @author Miguel Hernandez
 *
 */

public class BallOutOfBounds implements UpdateComponent {
	private static boolean reset = false;

	/**
	 * Pixels per meter
	 */
	private float ppm = Breakthrough.PIXELS_PER_METER;

	@Override
	public void update(Entity entity) {

		/**
		 * Check to see if we are out of bounds then reset the ball
		 * position and velocity
		 */
		if (entity.getPosition().getY() < -20) {
			GameInfo.subtractPlayerLives(1);

			Ball ball = (Ball) entity;
			entity.getBody().setTransform(ball.getStartX() / ppm, ball.getStartY() / ppm, entity.getBody().getAngle());
			entity.getBody().setLinearVelocity(0, 0);

			ParticleManager.reset();
			reset = true;
		}

		/**
		 * If space is pressed or touched then restart the emiters and
		 * start moving the ball
		 */
		if (reset) {

			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
				ParticleManager.getParticleEffect("trail").getEmitters().get(0).setContinuous(true);
				ParticleManager.startParticleEffect("trail");

				entity.getBody().setLinearVelocity(BallVelocity.minVelocity, BallVelocity.maxVelocity);
				reset = false;
			}
		}
	}

	public static boolean isReset() {
		return reset;
	}

	public static void setReset(boolean reset) {
		BallOutOfBounds.reset = reset;
	}
}
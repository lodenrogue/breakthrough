package com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.managers.ParticleManager;

/**
 * Update component implementation for ball entity special start level logic.
 * 
 * @author Miguel Hernandez
 *
 */
public class BallStartLevel implements UpdateComponent {
	public static boolean start = false;

	@Override
	public void update(Entity entity) {
		if ((Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) && !start && GameInfo.isLevelReadyToStart()) {
			start = true;
			ParticleManager.getParticleEffect("trail").getEmitters().get(0).setContinuous(true);
			ParticleManager.getParticleEffect("trail").start();
			entity.getBody().setLinearVelocity(BallVelocity.minVelocity, BallVelocity.maxVelocity);
		}
		
	}
}
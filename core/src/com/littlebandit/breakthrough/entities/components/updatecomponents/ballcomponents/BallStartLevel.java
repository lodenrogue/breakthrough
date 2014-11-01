package com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.GameInfo;

public class BallStartLevel implements UpdateComponent {
	public static boolean start = false;

	@Override
	public void update(Entity entity) {
		if ((Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) && !start && GameInfo.isLevelReadyToStart()) {
			start = true;
			entity.getBody().setLinearVelocity(BallVelocity.minVelocity, BallVelocity.maxVelocity);
		}

	}

}

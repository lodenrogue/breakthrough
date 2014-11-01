package com.littlebandit.breakthrough.entities.components.updatecomponents.blockcomponents;

import com.badlogic.gdx.Gdx;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.CircStrategy;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseStrategy;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.ElasticStrategy;

public class BlockEaseComponent implements UpdateComponent {
	private float ppm = Breakthrough.PIXELS_PER_METER;
	private float scaleTime = 0;
	private float scaleBeginValue = 0;
	private float scaleEndValue = 1;
	private float scaleEndTime = 0;
	// //////////////////////////
	private float dropTime = 0;
	private float dropBeginValue = 0;
	private float dropEndValue = 0;
	private float dropEndTime = 0;
	private float endY = 0;
	// //////////////////////////
	private EaseStrategy scaleStrategy = new CircStrategy();
	private EaseStrategy dropStrategy = new ElasticStrategy();
	
	//scale Bounce ease in
	//scale Circ ease in
	//drop Elastic ease in

	@Override
	public void update(Entity entity) {
		if (scaleEndTime == 0f) {
			scaleEndTime = (float) (Math.random() * 1f) + 0.5f;
			dropEndTime = scaleEndTime;
			dropBeginValue = entity.getBody().getPosition().y + ((((float) Math.random() * 50f)) + 50f) / ppm;
			endY = entity.getBody().getPosition().y;
			dropEndValue = endY;
			entity.getBody().setTransform(entity.getBody().getPosition().x, dropBeginValue, 0);

		}
		if (scaleTime < scaleEndTime) {
			float scale = scaleStrategy.easeIn(scaleTime, scaleBeginValue, scaleEndValue, scaleEndTime);
			entity.getSprite().setScale(scale);
			scaleTime += Gdx.graphics.getDeltaTime();
		}
		else if (scaleTime >= scaleEndTime) {
			entity.getSprite().setScale(1f);
			GameInfo.setIsLevelReadyToStart(true);
		}

		// ///////////////////////////////
		if (dropTime < dropEndTime) {
			float y = dropStrategy.easeIn(dropTime, dropBeginValue, dropEndValue, dropEndTime);
			entity.getBody().setTransform(entity.getBody().getPosition().x, y, 0);
			dropTime += Gdx.graphics.getDeltaTime();
		}
		else if (dropTime >= dropEndTime) {
			entity.getBody().setTransform(entity.getBody().getPosition().x, endY, 0);
		}

	}
}

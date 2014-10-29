package com.littlebandit.breakthrough.entities.components.updatecomponents;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;

public class PositionUpdateComponent implements UpdateComponent {
	private Body body;
	private float ppm = Breakthrough.PIXELS_PER_METER;

	@Override
	public void update(Entity entity) {
		Sprite sprite = entity.getSprite();
		body = entity.getBody();

		float bodyX = body.getPosition().x * ppm;
		float bodyY = body.getPosition().y * ppm;
		float x = bodyX - sprite.getWidth() / 2;
		float y = bodyY - sprite.getHeight() / 2;

		entity.getPosition().setX(x);
		entity.getPosition().setY(y);
		sprite.setPosition(x, y);

	}

}

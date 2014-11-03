package com.littlebandit.breakthrough.entities.components.updatecomponents.tweens;

import com.badlogic.gdx.math.Vector2;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.EaseDirection;
import com.littlebandit.breakthrough.gameutilities.math.easestrategies.SimpleEaseStrategy;

/**
 * Tween class for implementing easing strategies given a direction and other
 * parameters.
 * 
 * @author Miguel Hernandez
 *
 */
public abstract class Tween implements UpdateComponent {
	protected boolean start = false;
	protected boolean doTween = false;
	protected float startTime;
	protected float currentTime;
	protected float beginValue;
	protected Vector2 beginPosition;
	protected Vector2 endPosition;
	protected float endValue;
	protected float endTime;
	protected EaseDirection direction;
	protected SimpleEaseStrategy easeStrategy;

	public Tween(float startTime, float beginValue, float endValue, float endTime, EaseDirection direction, SimpleEaseStrategy strategy) {
		this.startTime = startTime;
		this.currentTime = startTime;
		this.beginValue = beginValue;
		this.endValue = endValue;
		this.endTime = endTime;
		this.direction = direction;
		easeStrategy = strategy;
	}

	public Tween(float startTime, Vector2 beginPosition, Vector2 endPosition, float endTime, EaseDirection direction, SimpleEaseStrategy strategy) {
		this.startTime = startTime;
		this.currentTime = startTime;
		this.beginPosition = beginPosition;
		this.endPosition = endPosition;
		this.endTime = endTime;
		this.direction = direction;
		easeStrategy = strategy;
	}

	public void start() {
		start = true;
	}

}

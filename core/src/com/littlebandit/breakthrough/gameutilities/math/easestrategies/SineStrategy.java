package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public class SineStrategy extends SimpleEaseStrategy {

	@Override
	public float easeIn(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return -change * (float) Math.cos(startTime / endTime * (Math.PI / 2)) + change + beginValue;
	}

	@Override
	public float easeOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return change * (float) Math.sin(startTime / endTime * (Math.PI / 2)) + beginValue;
	}

	@Override
	public float easeInOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return -change / 2 * ((float) Math.cos(Math.PI * startTime / endTime) - 1) + beginValue;
	}

}

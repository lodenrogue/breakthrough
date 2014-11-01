package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public class LinearStrategy implements EaseStrategy {

	@Override
	public float easeIn(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return change * startTime / endTime + beginValue;
	}

	@Override
	public float easeOut(float startTime, float beginValue, float endValue, float endTime) {
		return easeIn(startTime, beginValue, endValue, endTime);
	}

	@Override
	public float easeInOut(float startTime, float beginValue, float endValue, float endTime) {
		return easeIn(startTime, beginValue, endValue, endTime);
	}

}

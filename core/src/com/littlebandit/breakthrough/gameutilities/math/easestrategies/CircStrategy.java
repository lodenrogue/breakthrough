package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public class CircStrategy implements EaseStrategy {

	@Override
	public float easeIn(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return -change * ((float) Math.sqrt(1 - (startTime /= endTime) * startTime) - 1) + beginValue;
	}

	@Override
	public float easeOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return change * (float) Math.sqrt(1 - (startTime = startTime / endTime - 1) * startTime) + beginValue;
	}

	@Override
	public float easeInOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		
		if ((startTime /= endTime / 2) < 1) {
			return -change / 2 * ((float) Math.sqrt(1 - startTime * startTime) - 1) + beginValue;
		}
		else {
			return change / 2 * ((float) Math.sqrt(1 - (startTime -= 2) * startTime) + 1) + beginValue;
		}
	}

}

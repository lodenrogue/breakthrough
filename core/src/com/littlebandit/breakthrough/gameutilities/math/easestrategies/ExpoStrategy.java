package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public class ExpoStrategy implements EaseStrategy {

	@Override
	public float easeIn(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return (startTime == 0) ? beginValue : change * (float) Math.pow(2, 10 * (startTime / endTime - 1)) + beginValue;
	}

	@Override
	public float easeOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return (startTime == endTime) ? beginValue + change : change * (-(float) Math.pow(2, -10 * startTime / endTime) + 1) + beginValue;
	}

	@Override
	public float easeInOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;

		if (startTime == 0) {
			return beginValue;
		}
		if (startTime == endTime) {
			return beginValue + change;
		}
		if ((startTime /= endTime / 2) < 1) {
			return change / 2 * (float) Math.pow(2, 10 * (startTime - 1)) + beginValue;
		}

		return change / 2 * (-(float) Math.pow(2, -10 * --startTime) + 2) + beginValue;
	}

}

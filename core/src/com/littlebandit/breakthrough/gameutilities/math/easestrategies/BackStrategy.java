package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public class BackStrategy extends SimpleEaseStrategy {
	float s = 1.70158f;

	@Override
	public float easeIn(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return change * (startTime /= endTime) * startTime * ((s + 1) * startTime - s) + beginValue;
	}

	@Override
	public float easeOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return change * ((startTime = startTime / endTime - 1) * startTime * ((s + 1) * startTime + s) + 1) + beginValue;
	}

	@Override
	public float easeInOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;

		if ((startTime /= endTime / 2) < 1) {
			return change / 2 * (startTime * startTime * (((s *= (1.525f)) + 1) * startTime - s)) + beginValue;
		}
		else {
			return change / 2 * ((startTime -= 2) * startTime * (((s *= (1.525f)) + 1) * startTime + s) + 2) + beginValue;
		}
	}

}

package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public class BounceStrategy implements EaseStrategy {

	@Override
	public float easeIn(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return change - easeOut(endTime - startTime, 0, endValue, endTime) + beginValue;
	}

	@Override
	public float easeOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;

		if ((startTime /= endTime) < (1 / 2.75f)) {
			return change * (7.5625f * startTime * startTime) + beginValue;
		}
		else if (startTime < (2 / 2.75f)) {
			return change * (7.5625f * (startTime -= (1.5f / 2.75f)) * startTime + .75f) + beginValue;
		}
		else if (startTime < (2.5 / 2.75)) {
			return change * (7.5625f * (startTime -= (2.25f / 2.75f)) * startTime + .9375f) + beginValue;
		}
		else {
			return change * (7.5625f * (startTime -= (2.625f / 2.75f)) * startTime + .984375f) + beginValue;
		}
	}

	@Override
	public float easeInOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;

		if (startTime < endTime / 2) {
			return easeIn(startTime * 2, 0, endValue, endTime) * .5f + beginValue;
		}
		else {
			return easeOut(startTime * 2 - endTime, 0, endValue, endTime) * .5f + change * .5f + beginValue;
		}
	}

}

package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public class QuartStrategy extends SimpleEaseStrategy {

	@Override
	public float easeIn(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return change * (startTime /= endTime) * startTime * startTime * startTime + beginValue;
	}

	@Override
	public float easeOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		return -change * ((startTime = startTime / endTime - 1) * startTime * startTime * startTime - 1) + beginValue;
	}

	@Override
	public float easeInOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;
		
		if ((startTime /= endTime / 2) < 1) {
			return change / 2 * startTime * startTime * startTime * startTime + beginValue;
		}
		else {
			return -change / 2 * ((startTime -= 2) * startTime * startTime * startTime - 2) + beginValue;
		}
	}

}

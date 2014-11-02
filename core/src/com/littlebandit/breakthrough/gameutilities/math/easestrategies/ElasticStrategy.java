package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public class ElasticStrategy extends SimpleEaseStrategy {

	@Override
	public float easeIn(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;

		if (startTime == 0) {
			return beginValue;
		}
		if ((startTime /= endTime) == 1) {
			return beginValue + change;
		}

		float p = endTime * .3f;
		float a = change;
		float s = p / 4;

		return -(a * (float) Math.pow(2, 10 * (startTime -= 1)) * (float) Math.sin((startTime * endTime - s) * (2 * (float) Math.PI) / p)) + beginValue;
	}

	@Override
	public float easeOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;

		if (startTime == 0) {
			return beginValue;
		}
		if ((startTime /= endTime) == 1) {
			return beginValue + change;
		}

		float p = endTime * .3f;
		float a = change;
		float s = p / 4;

		return (a * (float) Math.pow(2, -10 * startTime) * (float) Math.sin((startTime * endTime - s) * (2 * (float) Math.PI) / p) + change + beginValue);
	}

	@Override
	public float easeInOut(float startTime, float beginValue, float endValue, float endTime) {
		float change = endValue - beginValue;

		if (startTime == 0) {
			return beginValue;
		}
		if ((startTime /= endTime / 2) == 2) {
			return beginValue + change;
		}

		float p = endTime * (.3f * 1.5f);
		float a = change;
		float s = p / 4;
		if (startTime < 1) {
			return -.5f * (a * (float) Math.pow(2, 10 * (startTime -= 1)) * (float) Math.sin((startTime * endTime - s) * (2 * (float) Math.PI) / p)) + beginValue;
		}
		else {
			return a * (float) Math.pow(2, -10 * (startTime -= 1)) * (float) Math.sin((startTime * endTime - s) * (2 * (float) Math.PI) / p) * .5f + change + beginValue;
		}
	}

}

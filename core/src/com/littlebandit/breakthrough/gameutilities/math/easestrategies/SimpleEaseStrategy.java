package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public abstract class SimpleEaseStrategy implements EaseStrategy {

	@Override
	public float ease(float startTime, float beginValue, float endValue, float endTime, EaseDirection direction) {
		switch (direction) {
		case EASE_IN:
			return easeIn(startTime, beginValue, endValue, endTime);
		case EASE_OUT:
			return easeOut(startTime, beginValue, endValue, endTime);
		case EASE_IN_OUT:
			return easeInOut(startTime, beginValue, endValue, endTime);
		default:
			return 0;
		}
	}
}

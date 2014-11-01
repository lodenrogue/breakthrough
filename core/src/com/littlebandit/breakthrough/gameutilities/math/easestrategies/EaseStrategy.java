package com.littlebandit.breakthrough.gameutilities.math.easestrategies;

public interface EaseStrategy {

	public float easeIn(float startTime, float beginValue, float endValue, float endTime);

	public float easeOut(float startTime, float beginValue, float endValue, float endTime);

	public float easeInOut(float startTime, float beginValue, float endValue, float endTime);

}

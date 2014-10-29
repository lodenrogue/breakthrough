package com.littlebandit.breakthrough.entities.components;

/**
 * Position class. Contains x and y location with methods to set and get points.
 * 
 * @author Miguel Hernandez
 *
 */

public class Position {

	private float x, y;

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

}

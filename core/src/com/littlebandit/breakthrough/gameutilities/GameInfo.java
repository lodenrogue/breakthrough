package com.littlebandit.breakthrough.gameutilities;

import com.littlebandit.breakthrough.entities.components.updatecomponents.ballcomponents.BallStartLevel;

/**
 * Game information utility class. Holds game variables that are commonly used
 * throughout the game
 * 
 * @author Miguel Hernandez
 *
 */
public class GameInfo {
	private static boolean isLevelReadyToStart = false;

	/**
	 * The game score
	 */
	private static int score = 0;

	/**
	 * The number of lives the player has
	 */
	private static int playerLives = 3;

	/**
	 * Create a new game info object
	 */
	private GameInfo() {

	}

	public static void setIsLevelReadyToStart(boolean start) {
		isLevelReadyToStart = start;
		if (!start) {
			BallStartLevel.start = false;
		}
	}

	public static boolean isLevelReadyToStart() {
		return isLevelReadyToStart;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameInfo.score = score;
	}

	public static void addScore(int number) {
		score += number;
	}

	public static int getPlayerLives() {
		return playerLives;
	}

	public static void setPlayerLives(int playerLives) {
		GameInfo.playerLives = playerLives;
	}

	public static void addPlayerLives(int number) {
		playerLives += number;
	}

	public static void subtractPlayerLives(int number) {
		playerLives -= number;
	}

}

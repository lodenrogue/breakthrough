package com.littlebandit.breakthrough.gameutilities;

import com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.ballcomponents.BallStartLevel;

/**
 * Game information utility class. Holds game variables that are commonly used
 * throughout the game
 * 
 * @author Miguel Hernandez
 *
 */
public class GameInfo {
	private static boolean isLevelReadyToStart = false;
	private static int level = 1;
	private static long score = 0;
	private static int playerLives = 3;

	/**
	 * Create a new game info object
	 */
	private GameInfo() {

	}

	public static void setLevel(int level) {
		GameInfo.level = level;
	}

	public static int getLevel() {
		return level;
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

	public static long getScore() {
		return score;
	}

	public static void setScore(long score) {
		GameInfo.score = score;
	}

	public static void addScore(long number) {
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

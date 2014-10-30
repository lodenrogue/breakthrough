package com.littlebandit.breakthrough.gameutilities;

/**
 * Game information utility class. Holds game variables.
 * 
 * @author Miguel Hernandez
 *
 */

public class GameInfo {
	private static int score = 0;
	private static int playerLives = 3;

	private GameInfo() {

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

}

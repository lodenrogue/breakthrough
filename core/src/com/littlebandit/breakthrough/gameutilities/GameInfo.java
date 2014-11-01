package com.littlebandit.breakthrough.gameutilities;

/**
 * Game information utility class. Holds game variables that are commonly used 
 * throughout the game
 * 
 * @author Miguel Hernandez
 *
 */
public class GameInfo {
    
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

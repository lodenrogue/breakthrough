package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;

/**
 * Level Complete!
 */
public class LevelCompleteState extends State {
	private BitmapFont font;
	private OrthographicCamera camera;
        
        private float deltaTime = 0.0f;
        private int elapsedStates = 0;
        private float timeSinceLastState = 0.0f;
        private float totalTime = 0.0f;
        private final float stateTime = 0.42f;
        
        private long levelScore;
        private long totalScore;

        private String levelScoreStr;
        private String totalScoreStr;

        private char [] levelScoreChars;
        private char [] totalScoreChars;
        
        private int maxSize;

	public LevelCompleteState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void create() {
            createCamera();
            font = new BitmapFont();

            levelScore = GameInfo.getLevelScore();
            totalScore = GameInfo.getScore();

            levelScoreStr = String.format("%06d", levelScore);
            totalScoreStr = String.format("%06d", totalScore);

            levelScoreChars = levelScoreStr.toCharArray();
            totalScoreChars = totalScoreStr.toCharArray();
            
            maxSize = Math.max(levelScoreStr.length(), totalScoreStr.length());
	}

	@Override
	public void update() {
                
                deltaTime = Gdx.graphics.getDeltaTime();
                timeSinceLastState += deltaTime;
                totalTime += deltaTime;
                if (timeSinceLastState > stateTime)
                {
                    elapsedStates++;
                    timeSinceLastState = 0.0f;
                }
            
		camera.update();

		// Wait for the player to press any key to start the next level
		if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.isTouched()) 
                {
                    if (totalTime < 1.0)
                    {
                        // Ignore inputs for one second to prevent accidental level start
                        /* do nothing! */
                    }
                    else if (elapsedStates < maxSize)
                    {
                        // The first time we hit a key, finish up the counting effect
                        elapsedStates = maxSize;
                        totalTime = 0.0f;
                    }
                    else
                    {
                        // Off to the next level!
                        GameInfo.setLevelScore(0);
			gsm.popAndPush(new PlayState(gsm, GameInfo.getLevel()));
			GameInfo.setIsLevelReadyToStart(false);
                    }
  
		}  // end if input
	}

	@Override
	public void render(SpriteBatch batch) {
		// Set our camera
		batch.setProjectionMatrix(camera.combined);

                levelScore = GameInfo.getLevelScore();
                totalScore = GameInfo.getScore();
                levelScoreStr = String.format("%06d", levelScore);
                totalScoreStr = String.format("%06d", totalScore);
                levelScoreChars = levelScoreStr.toCharArray();
                totalScoreChars = totalScoreStr.toCharArray();
                
                if (elapsedStates < maxSize)
                {
                    for (int i = 0; i < maxSize - elapsedStates; i++)
                    {
                        int randomInt = MathUtils.random(0, 9);
                        String randomStr = "" + randomInt;
                        levelScoreChars[i] = randomStr.charAt(0);
                        
                        randomInt = MathUtils.random(0, 9);
                        randomStr = "" + randomInt;
                        totalScoreChars[i] = randomStr.charAt(0);
                    }
                    
                    levelScoreStr = new String(levelScoreChars);
                    totalScoreStr = new String(totalScoreChars);
                    
                }
                
		// Render the players score
		font.draw(batch, "Level Complete!", Breakthrough.VIRTUAL_WIDTH / 3, Breakthrough.VIRTUAL_HEIGHT - 40);
		font.draw(batch, "Level SCORE: " + levelScoreStr, Breakthrough.VIRTUAL_WIDTH / 3, Breakthrough.VIRTUAL_HEIGHT - 60);
                font.draw(batch, "Total SCORE: " + totalScoreStr, Breakthrough.VIRTUAL_WIDTH / 3, Breakthrough.VIRTUAL_HEIGHT - 80);
                
                if (elapsedStates >= maxSize)
                    font.draw(batch, "Press Any Key to Continue...", Breakthrough.VIRTUAL_WIDTH / 3, Breakthrough.VIRTUAL_HEIGHT - 100);
                
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
		Breakthrough.viewport.setCamera(camera);
		GameManager.setCamera(camera);
	}
}
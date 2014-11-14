package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;

/**
 * Game Over!
 */
public class GameOverState extends State {
	private BitmapFont font;
	private OrthographicCamera camera;

	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void create() {
		createCamera();
		font = new BitmapFont();
	}

	@Override
	public void update() {
		camera.update();

		// Wait for the player to press any key to reset the game
		if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.isTouched()) {
                        GameInfo.resetGameInfo();
			gsm.popAndPush(new MenuState(gsm));
			GameInfo.setIsLevelReadyToStart(false);
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// Set our camera
		batch.setProjectionMatrix(camera.combined);

		// Render the players score
		font.draw(batch, "GAME OVER, Press Any Key or Touch to try again! ", Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 20);
		font.draw(batch, "FINAL SCORE: " + GameInfo.getScore(), Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 40);
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
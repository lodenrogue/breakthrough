/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;

/**
 *
 * @author bcompter
 */
public class MenuState extends State {
	private BitmapFont font;
	private OrthographicCamera camera;

	public MenuState(GameStateManager gsm) {
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

		// Wait for the player to press any key to start the game
		if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.isTouched()) {
			gsm.popAndPush(new PlayState(gsm));
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(camera.combined);

		// Render the players score
		font.draw(batch, "Main Menu, Press Any Key or Touch to Start ", Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 20);

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

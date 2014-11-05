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
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;

/**
 *
 * @author bcompter
 */
public class MenuState extends State {
	private BitmapFont font;
	private OrthographicCamera camera;
	private boolean readyToTouch = false;

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void create() {
		createCamera();
		createFont();

	}

	@Override
	public void update() {
		camera.update();

		if (!Gdx.input.isTouched()) {
			readyToTouch = true;
		}
		// Wait for the player to press any key to start the game
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY) || (Gdx.input.isTouched() && readyToTouch)) {
			gsm.popAndPush(new PlayState(gsm));
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(camera.combined);

		float xOffset = 60;
		float yOffset = 50;
		// Render start message
		font.draw(batch, "Press any key ", 160 + xOffset, Breakthrough.VIRTUAL_HEIGHT - 200f);
		font.draw(batch, "or touch to start", 160, Breakthrough.VIRTUAL_HEIGHT - (200 + yOffset));

	}

	@Override
	public void dispose() {
		font.dispose();
	}

	private void createFont() {
		// custom font set to white
		font = new BitmapFont(Gdx.files.internal("pix.fnt"));
		font.setColor(1, 1, 1, 1);
	}

	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
		Breakthrough.viewport.setCamera(camera);
		GameManager.setCamera(camera);
	}
}
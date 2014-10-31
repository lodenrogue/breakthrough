package com.littlebandit.breakthrough;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.littlebandit.breakthrough.gameutilities.GameContactListener;
import com.littlebandit.breakthrough.gameutilities.TextureManager;
import com.littlebandit.breakthrough.gameutilities.WorldManager;
import com.littlebandit.breakthrough.states.GameStateManager;
import com.littlebandit.breakthrough.states.PlayState;

public class Breakthrough extends ApplicationAdapter {
	public static final int VIRTUAL_WIDTH = 800;
	public static final int VIRTUAL_HEIGHT = 600;
	public static final float PIXELS_PER_METER = 15f;
	public static final String TITLE = "Breakthrough";
	public static FitViewport viewport;

	public enum ApplicationState {
		Running, Paused, Resumed
	}

	@SuppressWarnings("unused")
	private ApplicationState appState = ApplicationState.Running;
	private SpriteBatch batch;
	private GameStateManager gsm;

	@Override
	public void create() {
		batch = new SpriteBatch();
		viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);

		WorldManager.createWorld(new Vector2(0, 0), true);
		WorldManager.getWorld().setContactListener(new GameContactListener());
		TextureManager.initialize();

		gsm = new GameStateManager();
		gsm.popAndPush(new PlayState(gsm));
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		gsm.render(batch);
		batch.end();

	}

	@Override
	public void pause() {
		appState = ApplicationState.Paused;
		Gdx.app.log("pause", "On pause");

		if (Gdx.app.getType().equals(ApplicationType.Android)) {
			Gdx.app.exit();
		}
		// TODO Save application state when pause is invoked and resume when
		// resume is invoked
	}

	@Override
	public void resume() {
		appState = ApplicationState.Resumed;
	}

	@Override
	public void dispose() {
		gsm.dispose();
		TextureManager.dispose();
	}
}
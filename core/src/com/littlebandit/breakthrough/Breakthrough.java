package com.littlebandit.breakthrough;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.littlebandit.breakthrough.gameutilities.GameManager;
import com.littlebandit.breakthrough.states.GameStateManager;
import com.littlebandit.breakthrough.states.PlayState;

public class Breakthrough extends Game {
	public static final int VIRTUAL_WIDTH = 800;
	public static final int VIRTUAL_HEIGHT = 600;
	public static final float PIXELS_PER_METER = 15f;
	public static final String TITLE = "Breakthrough";
	public static FitViewport viewport;

    public enum State{ //to set the game state
        Running, Paused, Resumed
    }

    private State state = State.Running;

    private World world;
	private SpriteBatch batch;
	private GameStateManager gsm;

	@Override
	public void create() {
		batch = new SpriteBatch();
		viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);

		world = new World(new Vector2(0, 0), true);
		GameManager.setWorld(world);

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
	public void pause(){
        state = State.Paused; //to not kill the game on pause or minimizing window
        Gdx.app.log("pause","On pause");
        //Gdx.app.exit();
        //TODO Save game state when pause is invoked and resume when resume is invoked
	}

    public void resume(){
    state = State.Resumed;
        //render();
    }

	@Override
	public void dispose() {
		gsm.dispose();
	}
}

package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.Breakthrough;

public class PlayState extends State {
	private BitmapFont font;
	private OrthographicCamera camera;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void create() {
		font = new BitmapFont();
		createCamera();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch batch) {
		float x = Gdx.graphics.getWidth() / 2;
		float y = Gdx.graphics.getHeight() / 2;
		font.draw(batch, "Play State", x, y);

	}

	@Override
	public void dispose() {
		font.dispose();

	}
	
	private void createCamera(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
		Breakthrough.VIEWPORT.setCamera(camera);
	}

}

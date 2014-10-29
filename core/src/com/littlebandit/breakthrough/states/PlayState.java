package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;
import com.littlebandit.breakthrough.entities.entityutilities.EntityFactory;

public class PlayState extends State {
	private BitmapFont font;
	private OrthographicCamera camera;
	private EntityArrayList entities;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void create() {
		font = new BitmapFont();
		entities = new EntityArrayList();
		createCamera();
		createTestEntity();
	}

	@Override
	public void update() {
		entities.updateAll();
		camera.update();

	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(camera.combined);
		float x = Gdx.graphics.getWidth() / 2;
		float y = Gdx.graphics.getHeight() / 2;
		font.draw(batch, "Play State", x, y);
		
		entities.renderAll(batch);

	}

	@Override
	public void dispose() {
		font.dispose();

	}
	
	private void createCamera(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
		Breakthrough.viewport.setCamera(camera);
	}
	
	private void createTestEntity(){
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
		entities.add(EntityFactory.createTestEntity("test", sprite, 100, 100));
	}

}

package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;
import com.littlebandit.breakthrough.entities.entityutilities.EntityFactory;
import com.littlebandit.breakthrough.gameutilities.GameManager;

public class PlayState extends State {
	private BitmapFont font;
	private OrthographicCamera camera;
	private EntityArrayList entities;

	private World world;
	private Box2DDebugRenderer b2dRenderer;
	private OrthographicCamera debugCamera;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void create() {
		font = new BitmapFont();
		entities = new EntityArrayList();
		world = GameManager.getWorld();
		b2dRenderer = new Box2DDebugRenderer();

		createCamera();
		createEntities();
	}

	@Override
	public void update() {
		entities.updateAll();

		world.step(1 / 60f, 6, 3);
		camera.update();
		debugCamera.update();

	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(camera.combined);
		entities.renderAll(batch);

		 batch.setProjectionMatrix(debugCamera.combined);
		 b2dRenderer.render(world, debugCamera.combined);
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

		debugCamera = new OrthographicCamera();
		debugCamera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH / Breakthrough.PIXELS_PER_METER, Breakthrough.VIRTUAL_HEIGHT / Breakthrough.PIXELS_PER_METER);

	}

	private void createEntities() {
		createPaddle();
	}

	private void createPaddle() {
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("paddle.png")));
		entities.add(EntityFactory.createPaddle("paddle", sprite, Breakthrough.VIRTUAL_WIDTH / 2, 40));
	}

}

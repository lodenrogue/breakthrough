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
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.GameManager;

/**
 * Main game play state.
 * 
 * @author Miguel Hernandez
 *
 */

public class PlayState extends State {
	private BitmapFont font;
	private OrthographicCamera camera;
	private EntityArrayList entities;

	private World world;
	private Box2DDebugRenderer b2dRenderer;
	private OrthographicCamera debugCamera;
	private boolean debug = false;

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
		if (debug) {
			debugCamera.update();
		}

	}

	@Override
	public void render(SpriteBatch batch) {
		// Set out camera
		batch.setProjectionMatrix(camera.combined);

		// Render all entities in our list
		entities.renderAll(batch);

		// Render the players score
		font.draw(batch, "Score: " + GameInfo.getScore(), Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 20);
		font.draw(batch, "Debug Mode. Press 'R' to reset positions.", Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 50f);

		if (debug) {
			batch.setProjectionMatrix(debugCamera.combined);
			b2dRenderer.render(world, debugCamera.combined);
		}
	}

	@Override
	public void dispose() {
		font.dispose();
		entities.disposeAll();
	}

	/**
	 * Creates the main state camera and any other debug or auxiliary
	 * cameras.
	 */
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
		Breakthrough.viewport.setCamera(camera);
		GameManager.setCamera(camera);

		debugCamera = new OrthographicCamera();
		debugCamera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH / Breakthrough.PIXELS_PER_METER, Breakthrough.VIRTUAL_HEIGHT / Breakthrough.PIXELS_PER_METER);

	}

	/**
	 * Creates the game game entities.
	 */
	private void createEntities() {
		createPaddle();
		createBall();
		createScreenBounds();
		createBlocks();
	}

	private void createPaddle() {
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("paddle.png")));
		entities.add(EntityFactory.createPaddle("paddle", sprite, Breakthrough.VIRTUAL_WIDTH / 2, 40));
	}

	private void createBall() {
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("ball.png")));
		entities.add(EntityFactory.createBall("ball", sprite, Breakthrough.VIRTUAL_WIDTH / 2, 100));
	}

	private void createBlocks() {
		Texture block = new Texture(Gdx.files.internal("block.png"));
		float x = 200;
		float y = 200;
		for (int i = 0; i < 3; i++) {
			y = 200;
			for (int j = 0; j < 3; j++) {
				Sprite sprite = new Sprite(block);
				entities.add(EntityFactory.createBlock("block", sprite, x, y));
				y += block.getHeight() + 10f;
			}
			x += block.getWidth() + 10f;
		}

	}

	private void createScreenBounds() {
		EntityFactory.createScreenBounds();
	}
}
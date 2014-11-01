package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;
import com.littlebandit.breakthrough.entities.entityutilities.EntityFactory;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;
import com.littlebandit.breakthrough.gameutilities.managers.TextureManager;
import com.littlebandit.breakthrough.gameutilities.managers.WorldManager;
import com.littlebandit.breakthrough.gameutilities.map.MapBuilder;

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
		GameManager.setEntityArrayList(entities);

		b2dRenderer = new Box2DDebugRenderer();

		createCamera();
		createEntities();
	}

	@Override
	public void update() {
		entities.updateAll();
		WorldManager.updateWorld();
		camera.update();
		if (debug) {
			debugCamera.update();
		}

	}

	@Override
	public void render(SpriteBatch batch) {
		// Set our camera
		batch.setProjectionMatrix(camera.combined);

		// Render all entities in our list
		entities.renderAll(batch);

		// Render the players score
		font.draw(batch, "Score: " + GameInfo.getScore(), Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 20);

		if (debug) {
			batch.setProjectionMatrix(debugCamera.combined);
			b2dRenderer.render(WorldManager.getWorld(), debugCamera.combined);
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
		Sprite sprite = new Sprite(TextureManager.getTexture("paddle"));
		entities.add(EntityFactory.createPaddle("paddle", sprite, Breakthrough.VIRTUAL_WIDTH / 2, 40));
	}

	private void createBall() {
		Sprite sprite = new Sprite(TextureManager.getTexture("ball"));
		entities.add(EntityFactory.createBall("ball", sprite, Breakthrough.VIRTUAL_WIDTH / 2, 100));
	}

	private void createBlocks() {
		float width = TextureManager.getTexture("block").getWidth();
		float height = TextureManager.getTexture("block").getHeight();
		MapBuilder.buildLevelMap("level1.map", entities, (0 + width / 2) + width/2, Breakthrough.VIRTUAL_HEIGHT - height, width + 10, height + 10);
	}

	private void createScreenBounds() {
		EntityFactory.createScreenBounds();
	}
}
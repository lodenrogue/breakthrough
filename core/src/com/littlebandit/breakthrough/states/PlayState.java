package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;
import com.littlebandit.breakthrough.entities.entityutilities.EntityFactory;
import com.littlebandit.breakthrough.entities.playstate.Camera;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;
import com.littlebandit.breakthrough.gameutilities.managers.ParticleManager;
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
	private OrthographicCamera backgroundCamera;
	private OrthographicCamera box2dCamera;

	private EntityArrayList entities;
	private Box2DDebugRenderer b2dRenderer;
	private boolean debug = false;
	private int level;
	private Texture background;

	public PlayState(GameStateManager gsm, int level) {
		super(gsm);
		this.level = level;
	}

	@Override
	public void create() {
		font = new BitmapFont();
		b2dRenderer = new Box2DDebugRenderer();

		// Make sure to reset all game info on creation
		// GameInfo.setScore(0);
		GameInfo.setPlayerLives(3);
		GameInfo.setLevel(level);

		createCameras();
		createBackground();
		createEntities();
	}

	@Override
	public void update() {
		entities.updateAll();
		WorldManager.updateWorld();
		camera.update();

		if (debug) {
			box2dCamera.update();
		}

		handleEndOfLevel();

		// If we have zero lives we go to game over!
		if (GameInfo.getPlayerLives() == 0) {
			// GAME OVER!
			gsm.popAndPush(new GameOverState(gsm));
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			GameInfo.setIsLevelReadyToStart(false);
			gsm.popAndPush(new PlayState(gsm, level));
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(backgroundCamera.combined);
		batch.draw(background, 0, 0);

		batch.setProjectionMatrix(camera.combined);
		entities.renderAll(batch);

		font.draw(batch, "Level: " + level, Breakthrough.VIRTUAL_WIDTH / 2 - 100, Breakthrough.VIRTUAL_HEIGHT - 20);
		font.draw(batch, "Score: " + GameInfo.getScore(), Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 20);
		font.draw(batch, "Lives: " + GameInfo.getPlayerLives(), Breakthrough.VIRTUAL_WIDTH / 2 + 100, Breakthrough.VIRTUAL_HEIGHT - 20);
		// font.drawMultiLine(batch, "Debug Mode:\nFPS: " +
		// Gdx.graphics.getFramesPerSecond() +
		// "\nKeys: Space to start. \nR to reset level.", 10, 200);

		if (debug) {
			batch.setProjectionMatrix(box2dCamera.combined);
			b2dRenderer.render(WorldManager.getWorld(), box2dCamera.combined);
		}
	}

	@Override
	public void pause() {
		gsm.pushNew(new PauseMenuState(gsm));

	}

	@Override
	public void dispose() {
		font.dispose();
		entities.disposeAll();
		WorldManager.destroyAllBodies();
		ParticleManager.reset();
	}

	/**
	 * Creates the main state camera and any other debug or auxiliary
	 * cameras.
	 */
	private void createCameras() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
		Breakthrough.viewport.setCamera(camera);
		GameManager.setCamera(camera);

		box2dCamera = new OrthographicCamera();
		box2dCamera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH / Breakthrough.PIXELS_PER_METER, Breakthrough.VIRTUAL_HEIGHT / Breakthrough.PIXELS_PER_METER);

		backgroundCamera = new OrthographicCamera();
		backgroundCamera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
	}

	/**
	 * Creates the game game entities.
	 */
	private void createEntities() {
		entities = new EntityArrayList();
		GameManager.setEntityArrayList(entities);

		createCameraEntity();
		createScreenBounds();
		createBlocks();
		createBall();
		createPaddle();

	}

	private void createBackground() {
		int random = MathUtils.random(3) + 1;

		if (random == 1) {
			background = TextureManager.getTexture("background1");
		}
		if (random == 2) {
			background = TextureManager.getTexture("background2");
		}
		if (random == 3) {
			background = TextureManager.getTexture("background3");
		}
		if (random == 4) {
			background = TextureManager.getTexture("background4");
		}
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
		MapBuilder.buildLevelMap("level" + level + ".map", entities, (0 + width / 2) + width / 2, Breakthrough.VIRTUAL_HEIGHT - height, width + 10, height + 10);
	}

	private void createScreenBounds() {
		entities.add(EntityFactory.createScreenBounds("bounds", null, 0, 0));
	}

	private void createCameraEntity() {
		entities.add(new Camera("camera", null, 0, 0));
	}

	private void handleEndOfLevel() {
		// if all blocks have been destroyed
		if (entities.findFirstInstanceOf("block") == null) {
			// Do level finish stuff
			int nextLevel = GameInfo.getLevel() + 1;

			// if there is a next level file then move on to the
			// next level or else go back to the main menu.

			if (Gdx.files.internal("level" + nextLevel + ".map").exists()) {
				GameInfo.setLevel(nextLevel);
				GameInfo.setIsLevelReadyToStart(false);
				gsm.popAndPush(new PlayState(gsm, nextLevel));
			}
			else {
				gsm.popAndPush(new MenuState(gsm));
			}

		}
	}
}
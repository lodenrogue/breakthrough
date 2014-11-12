package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Camera;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;
import com.littlebandit.breakthrough.entities.entityutilities.EntityFactory;
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
	private OrthographicCamera box2dCamera;

	private EntityArrayList entities;
	private Box2DDebugRenderer b2dRenderer;
	private boolean debug = false;
	private int level;

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
		createEntities();
	}

	@Override
	public void update() {
		// logic updates
		entities.updateAll();
		WorldManager.updateWorld();
		camera.update();

		// debug camera update
		if (debug) {
			box2dCamera.update();
		}

		// If we have zero lives we go to game over!
		if (GameInfo.getPlayerLives() == 0) {
			gsm.popAndPush(new GameOverState(gsm));
		}

		// If R is pressed restart the level
		if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			GameInfo.setIsLevelReadyToStart(false);
			gsm.popAndPush(new PlayState(gsm, level));
		}

		handleEndOfLevel();
	}

	@Override
	public void render(SpriteBatch batch) {
		// Render all entities
		batch.setProjectionMatrix(camera.combined);
		entities.renderAll(batch);

		// Draw all text
		font.draw(batch, "Level: " + level, Breakthrough.VIRTUAL_WIDTH / 2 - 100, Breakthrough.VIRTUAL_HEIGHT - 20);
		font.draw(batch, "Score: " + GameInfo.getScore(), Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 20);
		font.draw(batch, "Lives: " + GameInfo.getPlayerLives(), Breakthrough.VIRTUAL_WIDTH / 2 + 100, Breakthrough.VIRTUAL_HEIGHT - 20);

		// show debug renderer
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

	private void createPaddle() {
		Sprite frame1 = new Sprite(TextureManager.getTexture("paddle00"));
		Sprite frame2 = new Sprite(TextureManager.getTexture("paddle01"));

		Array<Sprite> animation = new Array<Sprite>();
		animation.addAll(frame1, frame2);

		entities.add(EntityFactory.createPaddle("paddle", frame1, animation, .30f, Breakthrough.VIRTUAL_WIDTH / 2, 40));
	}

	private void createBall() {
		Sprite sprite = new Sprite(TextureManager.getTexture("ball"));
		entities.add(EntityFactory.createBall("ball", sprite, Breakthrough.VIRTUAL_WIDTH / 2, 100));
	}

	private void createBlocks() {
		float width = TextureManager.getTexture("block00").getWidth();
		float height = TextureManager.getTexture("block00").getHeight();
		MapBuilder.buildLevelMap("level" + level + ".map", entities, width + 40, Breakthrough.VIRTUAL_HEIGHT - height * 3, width, height);
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
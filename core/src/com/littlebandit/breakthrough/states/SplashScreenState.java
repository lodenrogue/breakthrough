package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.entityutilities.EntityArrayList;
import com.littlebandit.breakthrough.entities.entityutilities.EntityFactory;
import com.littlebandit.breakthrough.entities.entityutilities.Link;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;
import com.littlebandit.breakthrough.gameutilities.managers.TextureManager;

/**
 * Splash Screen State
 *
 * Created by Ashish on 04-11-2014.
 */
public class SplashScreenState extends State {
	private OrthographicCamera camera;
	private long startTime;
	private EntityArrayList entities;

	public SplashScreenState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void create() {
		createCamera();
		createEntities();

		startTime = TimeUtils.millis();
	}

	@Override
	public void update() {
		entities.updateAll();
		camera.update();

		// If 6 seconds have passed change to menu state.
		if (TimeUtils.millis() > (startTime + 6000)) {
			gsm.popAndPush(new MenuState(gsm));
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) || Gdx.input.isTouched()) {
			gsm.popAndPush(new MenuState(gsm));
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(camera.combined);
		entities.renderAll(batch);
	}

	@Override
	public void dispose() {
		entities.disposeAll();
	}

	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
		Breakthrough.viewport.setCamera(camera);
		GameManager.setCamera(camera);
	}

	private void createEntities() {
		entities = new EntityArrayList();
		GameManager.setEntityArrayList(entities);
		GameManager.setLinksArray(new Array<Link>());

		createLittleBanditSplash();
		createTitle();
	}

	private void createLittleBanditSplash() {
		Sprite sprite = new Sprite(TextureManager.getTexture("littlebanditlogo"));
		Entity littleBanditSplash = EntityFactory.createLittleBanditSplash("littlebanditsplash", sprite, 0, 0);
		entities.add(littleBanditSplash);
		littleBanditSplash.getSprite().setAlpha(0);

		Link link = (Link) littleBanditSplash;
		link.start();
	}

	private void createTitle() {
		Sprite sprite = new Sprite(TextureManager.getTexture("title"));
		Entity title = EntityFactory.createTitle("title", sprite, 0, 0);
		entities.add(title);
		title.getSprite().setAlpha(0);
	}
}
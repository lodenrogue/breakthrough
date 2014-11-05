package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;

/**
 * Splash Screen State
 *
 * Created by Ashish on 04-11-2014.
 */
public class SplashScreenState extends State {


    public SplashScreenState(GameStateManager gsm) {super(gsm);}
    private OrthographicCamera camera;
    private Texture texture;
    private long startTime;

    @Override
    public void create() {
        createCamera();
        texture = new Texture(Gdx.files.internal("splash.png"));
        startTime = TimeUtils.millis();
    }

    @Override
    public void update() {
        camera.update();
        if (TimeUtils.millis()>(startTime+5000)){ //splash image for 5 seconds
            gsm.popAndPush(new MenuState(gsm)); //open menu state
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.draw(texture, 0 , 0);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
        Breakthrough.viewport.setCamera(camera);
        GameManager.setCamera(camera);
    }
}

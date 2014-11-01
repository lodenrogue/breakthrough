package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.Breakthrough;
import com.littlebandit.breakthrough.gameutilities.GameInfo;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;
import com.littlebandit.breakthrough.gameutilities.managers.GameManager;

/**
 * Game Over!
 */
public class GameOverState extends State{
    
    private BitmapFont font;
    private OrthographicCamera camera;
    
    public GameOverState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Breakthrough.VIRTUAL_WIDTH, Breakthrough.VIRTUAL_HEIGHT);
        Breakthrough.viewport.setCamera(camera);
        GameManager.setCamera(camera);
        font = new BitmapFont();
    }

    @Override
    public void update() {
        camera.update();
        
        // Wait for the player to press any key to reset the game
        if (Gdx.input.isKeyPressed(Keys.ANY_KEY))
        {
            gsm.popAndPush(new PlayState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        
        // Set out camera
        batch.setProjectionMatrix(camera.combined);

        // Render the players score
        font.draw(batch, "GAME OVER, Press Any Key to try again! ", Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 20);
        font.draw(batch, "FINAL SCORE: " + GameInfo.getScore(), Breakthrough.VIRTUAL_WIDTH / 2, Breakthrough.VIRTUAL_HEIGHT - 40);
    }

    @Override
    public void dispose() {
        font.dispose();
    }
    
}  // end GameOverState

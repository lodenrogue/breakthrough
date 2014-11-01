package com.littlebandit.breakthrough.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.gameutilities.managers.GameStateManager;

/**
 * State class offers abstract methods for derivative states to be used in a
 * state based game. Takes in a GameStateManager in constructor to handle state
 * changes.
 * 
 * @author Miguel Hernandez
 *
 */
public abstract class State {
	protected GameStateManager gsm;

	public State(GameStateManager gsm) {
		this.gsm = gsm;
	}

	/**
	 * Called when the state is created. Put all set up here.
	 */
	public abstract void create();

	/**
	 * Logic update method.
	 */
	public abstract void update();

	/**
	 * Called to render graphics.
	 * 
	 * @param batch SpriteBatch used to render components.
	 */
	public abstract void render(SpriteBatch batch);

	/**
	 * This method is called in the GameStateManager to both update and
	 * render this state.
	 * 
	 * @param batch
	 */
	public final void updateAndRender(SpriteBatch batch) {
		update();
		render(batch);
	}

	/**
	 * Called when resources need to be released. Typically, when this state
	 * is being removed from the stack.
	 */
	public abstract void dispose();

}

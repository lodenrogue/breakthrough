package com.littlebandit.breakthrough.gameutilities.managers;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.littlebandit.breakthrough.states.State;

/**
 * Manager for game states. Handles states in a stack-like data structure. Used
 * to push and pop game states and handle render and dispose calls.
 * 
 * @author Miguel Hernandez
 *
 */
public class GameStateManager {

	private Stack<State> states;

	public GameStateManager() {
		states = new Stack<State>();
	}

	/**
	 * Calls render on the first element in the state stack.
	 * 
	 * @param sb
	 */
	public void render(SpriteBatch batch) {
		states.peek().updateAndRender(batch);
	}

	/**
	 * Called when the game is paused.
	 */
	public void pause() {
		states.peek().pause();
	}

	/**
	 * Called when the game is resumed.
	 */
	public void resume() {
		states.peek().resume();
	}

	/**
	 * If the state stack is not empty it removes the first element and puts
	 * the passed state at the top of the stack.
	 * 
	 * @param s
	 */
	public void popAndPush(State s) {
		if (!states.isEmpty()) {
			states.peek().dispose();
			states.pop();
		}
		states.push(s);
		s.create();

	}

	/**
	 * Removes the top element from the stack.
	 */
	public void pop() {
		states.pop();
	}

	/**
	 * Puts the passed state at the top of the stack.
	 * 
	 * @param s State to push.
	 */
	public void pushNew(State s) {
		states.push(s);
		s.create();
	}

	/**
	 * Iterates through states in the stack and calls the dispose method for
	 * each.
	 */
	public void dispose() {
		for (State s : states) {
			s.dispose();
		}
	}
}

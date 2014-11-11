package com.vertextrigger.screens;

import com.badlogic.gdx.Screen;

/**
 * The first screen the user sees, with the option of selecting the level they
 * want to play. The first level will always be able to be selected & so will
 * previously completed levels.
 */
public class MainScreen implements Screen {

	/**
	 * Render method is invoked repeatedly once per frame,
	 * approximately 60 frames per second, during the game
	 */
	@Override
	public void render(float delta) {
	}

	/**
	 * Automatically called when application is resized
	 */
	@Override
	public void resize(int width, int height) {
	}

	/**
	 * Show method is invoked once when the main screen is first created & is
	 * used to set up the main screen
	 */
	@Override
	public void show() {
	}

	/**
	 * Hide method is invoked automatically when the screen is switch to another
	 * screen & is an appropriate place to dispose of the assets being used in
	 * this screen
	 */
	@Override
	public void hide() {
		// Dispose assets being used in this screen
	}

	// UNUSED METHOD FROM INTERFACE
	@Override
	public void pause() {
	}

	// UNUSED METHOD FROM INTERFACE
	@Override
	public void resume() {
	}

	/**
	 * Dispose method needs to be invoked manually when this class is no longer
	 * being used to dispose of the assets it's using
	 */
	@Override
	public void dispose() {
	}
}
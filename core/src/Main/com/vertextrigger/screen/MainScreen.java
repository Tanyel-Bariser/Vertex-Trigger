package com.vertextrigger.screen;

import com.badlogic.gdx.Screen;
import com.vertextrigger.main.VertexTrigger;

/**
 * The first screen the user sees, with the option of selecting the level they
 * want to play. The first level will always be able to be selected & so will
 * previously completed levels.
 */
public class MainScreen implements Screen {
	
	/**
	 * Sets main game class for smooth screen transitions
	 * 
	 * @param vertexTrigger main game class
	 */
	public MainScreen(VertexTrigger vertexTrigger) {
		// Set main game class
	}

	/**
	 * Render method is invoked repeatedly once per frame, approximately 60
	 * frames per second, during the game
	 */
	@Override
	public void render(float delta) {
		// Set the colour to clear the screen to
		// Clear the screen to the selected colour
		// Play main screen music
		// Draw all sprites to the screen in one batch
		// Draw the label buttons from the stage to the screen
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
		// Create "Stage" to handle buttons & distribute input events
		// Set stage as this screen's input processor
		// Create a sprite batch for later rendering all sprite in one batch
		// Initialise all sprites & fonts needed for this screen
		// Create label buttons to allow user to select the level to play
	}

	/**
	 * Creates a list of labels so users can select the level they want to play
	 */
	private void levelSelectionSetup() {
		// Create clickable/touchable labels that transition to the appropriate
		// level screen, i.e. Level One, Level Two, etc.
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
		// Dispose assets being used in this screen
	}
}
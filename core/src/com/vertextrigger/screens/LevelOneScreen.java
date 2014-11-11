package com.vertextrigger.screens;

import com.badlogic.gdx.Screen;
import com.vertextrigger.VertexTrigger;

/**
 * 
 */
public class LevelOneScreen implements Screen {
	
	/**
	 * Sets main game class for smooth screen transitions
	 * 
	 * @param vertexTrigger main game class
	 */
	public LevelOneScreen(VertexTrigger vertexTrigger) {
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
		// If the game state is "running", i.e. not paused
				// Adjust gravity of game world based on delta
				// Update game world based on time step
				// For each entity in the entity container
						// Update each entity & store their updated sprites
						// in the sprite container for later rendering
				// Update camera position to follow the player
				// Remove sprites that are not in the view of the camera from
				// the container of sprites to be rendered
		// Draw all sprites in one batch to the screen
		// Draw all button/label images
	}

	/**
	 * Automatically called when application is resized
	 */
	@Override
	public void resize(int width, int height) {
	}

	/**
	 * Show method is invoked once when the level one screen is first created &
	 * is used to set up the screen of the first level
	 */
	@Override
	public void show() {
		// Play Level One music
		// Create a "Stage" to handle buttons & distribute input events
		// Create Controller to create & manage all virtual game play buttons
		// for the level
		// Set the stage & controller class as the current input processors
		// Create a sprite batch for later rendering all sprite in one batch
		// Create camera to be able to project a portion of the game world to
		// the user's screen
		// Create the game world with a gravity of 9.81 m/s2
		// Create the collision detector to recognise contacts between game objects
		// Set the collision detector to the game world so it can detect game 
		// objects that reside within the game world
		// Create the ground, walls, ceiling, etc. for the level & add all of it's
		// sprites to the container of sprites for later rendering
		// Create all of the platforms & add the sprites to the sprite container
		// Create all of the enemies & dangerous balls & add these sprites to the
		// sprite container for later rendering
		// Create the main player & add his sprite to the sprite container
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

	/**
	 * Invoked when the user pauses the game play
	 */
	@Override
	public void pause() {
		// Set game to paused
	}

	/**
	 * Invoked when the user unpauses the game to resume playing
	 */
	@Override
	public void resume() {
		// Set game to running
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
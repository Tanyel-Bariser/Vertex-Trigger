package com.vertextrigger.screens;

import com.badlogic.gdx.Screen;
import com.vertextrigger.VertexTrigger;
import com.vertextrigger.levelbuilders.LevelBuilder;

/**
 * 
 */
public class GameScreen implements Screen {
	private VertexTrigger vertexTrigger;
	private LevelBuilder levelBuilder;
	
	/**
	 * Sets main game class for smooth screen transitions
	 * 
	 * @param vertexTrigger main game class
	 */
	public GameScreen(VertexTrigger vertexTrigger, LevelBuilder levelBuilder) {
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
						// Update each entity & store their updated
						// sprite in the entity sprite container
				// Update camera position to follow the player
				// Add only those sprites that are in the view of the camera 
				// projection into a separate container of sprites to be rendered
		// Draw all sprites in one batch to the user's screen
		// Draw all button/label images to the user's screen
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
		// Create the static platforms & add these sprites to the sprite container
		// Create all of the moving & timed platforms & add these entities to a
		// separate container
		// Create all of the enemies & dangerous balls & add these entities to the
		// entity container
		// Create the main player & add his entity to the entity container
	}
	
	/**
	 * Resets the level layout when player has died & repositions
	 * the player back to the initial position of the level
	 */
	public void resetLevel() {
		
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
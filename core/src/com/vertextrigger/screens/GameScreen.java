package com.vertextrigger.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.VertexTrigger;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.levelbuilders.LevelBuilder;

/**
 * 
 */
public class GameScreen implements Screen {
	private VertexTrigger vertexTrigger;
	private LevelBuilder levelBuilder;
	private World world;
	private Array<Entity> entities;
	private Array<Sprite> entitySprites;
	private Array<Sprite> sprites;
	private final float GRAVITY = -9.81f;
	
	/**
	 * Sets main game class for smooth screen transitions
	 * 
	 * @param vertexTrigger main game class
	 */
	public GameScreen(VertexTrigger vertexTrigger, LevelBuilder levelBuilder) {
		// Set main game class
		// Set the specific level builder to set up & build the specific level,
		// i.e. level 1, level 2 or level 3, etc.
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
			
			// Empty the entity sprite container
			entitySprites.clear();
			// For each entity in the entity container
			for (Entity entity : entities) {
				// Update each entity
				Sprite sprite = entity.update(delta);
				// Store their updated sprite in the entity sprite container
				entitySprites.add(sprite);
			}
		
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
		
		// Create the game world with a gravity of -9.81 m/s2 on the Y-axis &
		// for performance set true to not simulating inactive bodies
		world = new World(new Vector2(0, GRAVITY), true);
		
		// Create the collision detector to recognise contacts between game objects
		// Set the collision detector to the game world so it can detect game 
		// objects that reside within the game world
		
		// Create the static platforms, portals, ground, walls, ceiling, etc. for the
		// level & add all of their corresponding sprites to the container of
		// sprites for later rendering
		sprites = levelBuilder.buildLevelLayout(world);
		
		// Create all dynamic/kinematic game objects for this level, i.e. the
		// player, enemies, dangerous balls, moving platforms, etc. & add these
		// entities into a container so they each can be updated once per frame
		// during gameplay
		entities = levelBuilder.buildEntities(world);
	}
	
	/**
	 * @param entity is added to the entity container/data structure
	 */
	public void addEntity(Entity entity) {
		// Added an entity to the entity container
		entities.add(entity);
	}
	
	/**
	 * Resets the level layout when player has died & repositions
	 * the player back to the initial position of the level
	 * Does not recreate game objects, it only resets their initial positions
	 */
	public void resetLevel() {
		levelBuilder.resetLevelLayout();
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
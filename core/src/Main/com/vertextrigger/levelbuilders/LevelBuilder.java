package com.vertextrigger.levelbuilders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.screens.GameScreen;

public abstract class LevelBuilder {
	protected World world;
	protected Array<Entity> entities;
	protected Array<Sprite> sprites;
	protected GameScreen gameScreen;
	
	protected LevelBuilder() {
		entities = new Array<Entity>();
		sprites = new Array<Sprite>();
	}
	
	/**
	 * Create player at the initial position of the particular game level
	 * Then adds player to the entities container
	 * 
	 * @param world for the player to reside in
	 * @param gameScreen responsible for rendering the game
	 */
	protected abstract void createPlayer(World world, GameScreen gameScreen);

	/**
	 * Create all enemies needed for the particular game level at the
	 * specific initial positions & to follow their predefined path
	 * Then adds the enemies to the entities container
	 * 
	 * @param world for all enemies in this level to reside in
	 */
	protected abstract void createEnemies(World world);

	/**
	 * Create all dangerous balls needed for the particular game level at
	 * the specific initial positions & to follow their predefined path
	 * Then adds the dangerous balls to the entities container
	 * 
	 * @param world for all dangerous balls to reside in
	 */
	protected abstract void createDangerousBalls(World world);

	/**
	 * Create all moving platforms needed for the particular game level at the
	 * specific initial positions & to follow their predefined path
	 * Then adds the moving platforms to the entities container
	 * 
	 * @param world for all platforms to be built in
	 */
	protected abstract void createMovingPlatforms(World world);

	/**
	 * Create all timed platforms needed for the particular game level at the
	 * specific initial positions & the predefined time for the platforms to
	 * perform their actions
	 * Then adds the timed platforms to the entities container
	 * 
	 * @param world for all platforms to be built in
	 */
	protected abstract void createTimedPlatforms(World world);

	/**
	 * Create all static platforms needed for the particular game level at the
	 * specific initial positions
	 * Then adds the platform's corresponding sprites to the sprites container
	 * 
	 * @param world for all platforms to be built in
	 */
	protected abstract void createStaticPlatforms(World world);

	/**
	 * Create the ground, ceiling & walls for the particular level's layout
	 * Then adds the ground, ceiling & wall's corresponding sprites to the
	 * sprites container
	 * 
	 * @param world for the ground, ceiling & walls to be built in
	 */
	protected abstract void createGroundWalls(World world);

	/**
	 * Resets the positions of all entities when player
	 * dies back to their initial positions of the level
	 */
	public abstract void resetLevelLayout();
	
	/**
	 * Invokes all methods required to build all of this level's entities &
	 * stores them in the entities container, before returning the container.
	 * 
	 * @param world for all game objects in this level to reside in
	 * @return all entities required for this level
	 */
	public Array<Entity> buildEntities(World world) {
		// Create player then add to the entities container
		createPlayer(world, gameScreen);
		// Create enemies then add them to the entities container
		createEnemies(world);
		// Create dangerous ball(s) then add them to the entities container
		createDangerousBalls(world);
		// Return container of all entities needed for this level
		return entities;
	}
	
	/**
	 * Invokes all methods required to build all of this level's platforms,
	 * ground, ceiling and walls and stores them in their corresponding
	 * sprites in the sprites container before returning the container.
	 * 
	 * @param world for all game objects in this level to reside in
	 * @return all entities required for this level
	 */
	public Array<Sprite> buildLevelLayout(World world) {
		createStaticPlatforms(world);
		createGroundWalls(world);
		return sprites;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
}
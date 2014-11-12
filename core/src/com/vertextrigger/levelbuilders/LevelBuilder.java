package com.vertextrigger.levelbuilders;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public interface LevelBuilder {
	
	/**
	 * @param world for all game objects in this level to reside in
	 */
	void setWorld(World world);

	/**
	 * Create player at the initial position of the particular game level
	 * 
	 * @return player
	 */
	Entity createPlayer();

	/**
	 * Create all enemies needed for the particular game level at the
	 * specific initial positions & to follow the path for that level
	 * 
	 * @return a container of enemies
	 */
	Array<Entity> createEnemies();

	/**
	 * Create all dangerous balls needed for the particular game level at the
	 * specific initial positions & to follow the path for that level
	 * 
	 * @return a container of dangerous balls
	 */
	Array<Entity> createDanerousBalls();

	/**
	 * Create all moving platforms needed for the particular game level at the
	 * specific initial positions & to follow the path for that level
	 * 
	 * @return a container of moving platforms
	 */
	Array<Entity> createMovingPlatforms();

	/**
	 * Create all timed platforms needed for the particular game level at the
	 * specific initial positions & the predefined time for the platforms to
	 * perform their actions
	 * 
	 * @return a container of timed platforms
	 */
	Array<Entity> createTimedPlatforms();

	/**
	 * Create all static platforms needed for the particular game level at the
	 * specific initial positions
	 * 
	 * @return a container of static platform sprites
	 */
	Array<Sprite> createStaticPlatforms();

	/**
	 * Create the ground, ceiling & walls for the particular level's layout
	 * 
	 * @param world for the level's ground, ceiling & walls to be created in
	 */
	Array<Sprite> createGroundWalls();

	/**
	 * Resets the level layout when player has died & repositions the player
	 * back to the initial position of the level
	 * 
	 * DO NOT RECREATE GAME OBJECTS, ONLY REPOSITION THEM AS IT'S FASTER
	 */
	void resetLevelLayout();
}
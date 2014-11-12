package com.vertextrigger.levelbuilders;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public interface LevelBuilder {

	/**
	 * Create player at the initial position of the particular game level
	 * 
	 * @param world player will reside in
	 * @return player
	 */
	Entity createPlayer(World world);

	/**
	 * Create all enemies needed for the particular game level at the
	 * specific initial positions & to follow the path for that level
	 * 
	 * @param world enemies will reside in
	 * @return a container of enemies
	 */
	Array<Entity> createEnemies(World world);

	/**
	 * Create all dangerous balls needed for the particular game level at the
	 * specific initial positions & to follow the path for that level
	 * 
	 * @param world dangerous balls will reside in
	 * @return a container of dangerous balls
	 */
	Array<Entity> createDanerousBalls(World world);

	/**
	 * Create all moving platforms needed for the particular game level at the
	 * specific initial positions & to follow the path for that level
	 * 
	 * @param world moving platforms will reside in
	 * @return a container of moving platforms
	 */
	Array<Entity> createMovingPlatforms(World world);

	/**
	 * Create all timed platforms needed for the particular game level at the
	 * specific initial positions & the predefined time for the platforms to
	 * perform their actions
	 * 
	 * @param world timed platforms will reside in
	 * @return a container of timed platforms
	 */
	Array<Entity> createTimedPlatforms(World world);

	/**
	 * Create all static platforms needed for the particular game level at the
	 * specific initial positions
	 * 
	 * @param world static platforms will reside in
	 * @return a container of static platform sprites
	 */
	Array<Sprite> createStaticPlatforms(World world);

	/**
	 * Create the ground, ceiling & walls for the particular level's layout
	 * 
	 * @param world for the level's ground, ceiling & walls to be created in
	 */
	void createGroundWalls(World world);

	/**
	 * Resets the level layout when player has died & repositions the player
	 * back to the initial position of the level
	 * 
	 * DO NOT RECREATE GAME OBJECTS, ONLY REPOSITION THEM AS IT'S FASTER
	 */
	void resetLevelLayout();
}
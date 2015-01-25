package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Creates the ground, ceiling & walls for each level in the game
 */
public class GroundWallFactory {

	/**
	 * Given the game world this method creates the physical level
	 * layout for the first level of the game, i.e. the ground, walls,
	 * ceiling, etc. within this world.
	 * 
	 * @param world the ground, walls & ceiling will be created in
	 * @return array of sprites for the background of the first level
	 */
	public static Array<Sprite> createLevelOneGroundWalls(World world) {
		// Connect coordinate positions within the game world to each
		// other in dot to dot fashion to create the ground, ceiling
		// & walls of the first level
		// Label the ground, ceiling & wall fixture "Ground"
		
		// Position all sprites need for the first level
		// Put all sprites into a container
		// Return container of background, ground, walls, etc. sprites
		return null;
	}
	

	/**
	 * Given the game world this method creates the physical level
	 * layout for the second level of the game, i.e. the ground, walls,
	 * ceiling, etc. within this world.
	 * 
	 * @param world the ground, walls & ceiling will be created in
	 * @return array of sprites for the background of the second level
	 */
	public static Array<Sprite> createLevelTwoGroundWalls(World world) {
		// Connect coordinate positions within the game world to each
		// other in dot to dot fashion to create the ground, ceiling
		// & walls of the second level
		// Label the ground, ceiling & wall fixture "Ground"

		// Position all sprites need for the second level
		// Put all sprites into a container
		// Return container of background, ground, walls, etc. sprites
		return null;
	}
}
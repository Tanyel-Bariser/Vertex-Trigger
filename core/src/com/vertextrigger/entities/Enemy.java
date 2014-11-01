package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinate;

/**
 * Enemies can will kill the player if touched & follows a predefined path
 * This class manages an enemy's physical body & its movements & sprite animation
 */
public class Enemy {

	/**
	 * Creates enemy's physical body & its physical properties
	 * Creates sprite & animation factories
	 * 
	 * @param world the enemy will reside in
	 * @param path is a series of x & y coordinates for the dangerous ball to follow
	 */
	public Enemy(World world, Array<Coordinate> path) {
		// Create & set sprite factory
		// Create & set animation factory
		// Initialise physical properties, i.e. polygon shape, dynamic body, etc.
		// Set the path for the enemy to follow in a loop
		// Set position of enemy based on first x & y coordinate of its path
		// If path has a second coordinate
				// Set next coordinate to move to as the second coordinate in path
		// Create physical body
		// Set identifier label as "Enemy"
		// Set sprite
	}
	
	/**
	 * Moves the enemy further along its predefined
	 * path with the distance moved dependent on the delta.
	 * Delta is needed to frame rate independent movement
	 * 
	 * @param delta time passed between previous & current frame
	 * @return updated enemy sprite
	 */
	public Sprite updatePath(float delta) {
		// If there is a next coordinate to move to
				// Move enemy toward next coordinate
				// at a distance dependent on delta
				// If enemy has reached this next coordinate
						// If another coordinate exists
								// Set next coordinate to this new coordinate
						// If another coordinate does not exist
								// Set next coordinate as the initial coordinate
		// Set enemy's sprite position & angle to match
		// the new position of enemy's physical body
		// Return enemy sprite after it's position/angle has been updated
		return null;
	}
	
	/**
	 * Initialise sprites & animation objects
	 */
	private void spriteAnimationSetup() {
		// Create & set all sprites & animations the enemy will need
	}
	
	public Sprite updateSprite(float delta) {
		// Add delta to current animation key frame time
		// If enemy is rising/jumping
				// Set enemy sprite based on jumping animation key frame
		// If enemy is falling
				// Set enemy sprite based on falling animation key frame
		// If enemy is moving left
				// Set enemy sprite based on running animation key frame
		// Flip enemy sprite so that if he's moving left the sprite
		// is facing left and vice versa if he is moving right
		// Return updated sprite
		return null;
	}
}
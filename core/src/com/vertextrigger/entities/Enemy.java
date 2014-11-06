package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinate;

/**
 * Enemies can will kill the player if touched & follows a predefined path
 * This class manages an enemy's physical body & its movements & sprite animation
 */
public class Enemy implements Updatable {

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
	 * Initialise sprites & animation objects
	 */
	private void spriteAnimationSetup() {
		// Create & set all sprites & animations the enemy will need
	}
	
	/**
	 * Moves the enemy further along its predefined
	 * path with the distance moved dependent on the delta.
	 * Delta is needed to frame rate independent movement.
	 * 
	 * Chooses appropriate enemy sprite based on animation.
	 * Returns the updated enemy's sprite for rendering.
	 * 
	 * @param delta time passed between previous & current frame
	 * @return updated enemy sprite
	 */
	@Override
	public Sprite update(float delta) {
		// Move enemy further along it's predefined path based on delta
		
		// Add delta to current animation key frame time
		// If enemy is rising/jumping
				// Set enemy sprite based on jumping animation key frame
		// If enemy is falling
				// Set enemy sprite based on falling animation key frame
		// If enemy is moving left
				// Set enemy sprite based on running animation key frame
		// Flip enemy sprite so that if he's moving left the sprite
		// is facing left and vice versa if he is moving right

		// Set enemy's sprite position & angle to match
		// the new position of enemy's physical body
		// Return enemy sprite after it's position/angle has been updated
		return null;
	}
}
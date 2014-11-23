package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinate;

/**
 * DangerousBall is an game object that will kill the player if touched and
 * follows a predefined path, such as, traversing the edges of a platform
 */
public abstract class DangerousBall implements Entity {
	// Predefined path for dangerous ball's physical body to follow
	protected Path path;
	protected float speed;

	/**
	 * Initialises the physical properties of the Dangerous Ball's physical body
	 * Sets Dangerous Ball's sprite, could be fire ball or spiked ball, etc.
	 * Sets the path of the dangerous ball to traverse
	 * 
	 * @param world the dangerous ball will reside in
	 * @param sprite for the image of the dangerous ball
	 * @param coordinates is the path, series of x & y coordinates, the dangerous ball follows
	 */
	public DangerousBall(World world, Sprite sprite, Array<Coordinate> coordinates, float radius, float speed) {
		// Set sprite for dangerous ball
		// Create physical body
		// Set initial position based on first coordinate
		// Set radius of dangerous ball
		// Set the speed the dangerous ball moves at
		// Set the coordinates of the predefined path
		// for the enemy to follow in a loop
	}
	
	/**
	 * Create & set sprite for dangerous ball
	 */
	protected abstract void spriteSetup();
	
	/**
	 * Moves the dangerous ball further along its predefined
	 * path with the distance moved dependent on the delta.
	 * Delta is needed for frame rate independent movement.
	 * Returns sprite after it's position has been updated.
	 * 
	 * @param delta time passed between previous & current frame
	 * @return updated sprite of this dangerous ball
	 */
	@Override
	public Sprite update(float delta) {
		// Move dangerous ball along it's predefined path based on delta
		// Set dangerous ball's sprite position & angle to match
		// the new position of dangerous ball's physical body
		// Return sprite after it's position/angle has been updated
		return null;
	}
}
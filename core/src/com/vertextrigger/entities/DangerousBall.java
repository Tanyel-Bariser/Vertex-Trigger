package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinate;

/**
 * DangerousBall is an game object that will kill the player if touched and
 * follows a predefined path, such as, traversing the edges of a platform
 */
public class DangerousBall implements Entity {
	private Path path;

	/**
	 * Initialises the physical properties of the Dangerous Ball's physical body
	 * Sets Dangerous Ball's sprite, could be fire ball or spiked ball, etc.
	 * Sets the path of the dangerous ball to traverse
	 * 
	 * @param world the dangerous ball will reside in
	 * @param sprite for the image of the dangerous ball
	 * @param path is a series of x & y coordinates for the dangerous ball to follow
	 */
	public DangerousBall(World world, Sprite sprite, Array<Coordinate> coordinates) {
		// Initialise physical properties, i.e. circle shape, kinematic body, etc.
		// Set the path's coordinates for the dangerous ball to follow in a loop
		// Create physical body
		// Set identifier label as "DangerousBall"
		// Set sprite
	}
	
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
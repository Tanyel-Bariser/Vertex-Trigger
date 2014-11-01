package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinates;

/**
 * DangerousBall is an game object that will kill the player if touched and
 * follows a predefined path, such as, traversing the edges of a platform
 */
public class DangerousBall {

	/**
	 * Initialises the physical properties of the Dangerous Ball's physical body
	 * Sets Dangerous Ball's sprite, could be fire ball or spiked ball, etc.
	 * Sets the path of the dangerous ball to traverse
	 * 
	 * @param sprite for the image of the dangerous ball
	 * @param path is a series of x & y coordinates for the dangerous ball to follow
	 */
	public DangerousBall(Sprite sprite, Array<Coordinates> path) {
		// Initialise physical properties, i.e. circle shape, kinematic body, etc.
		// Set the path for the dangerous ball to follow in a loop
		// Set position of dangerous ball based on first x & y coordinate of its path
		// If path has a second coordinate
				// Set next coordinate to move to as the second coordinate in path
		// Create physical body
		// Set identifier label as "DangerousBall"
		// Set sprite
	}
	
	/**
	 * Moves the dangerous ball further along its predefined
	 * path with the distance moved dependent on the delta.
	 * Delta is needed to frame rate independent movement
	 * 
	 * @param delta time passed between previous & current frame
	 * @return
	 */
	public Sprite updatePath(float delta) {
		// If there is a next coordinate to move to
				// Move dangerous ball toward next coordinate
				// at a distance dependent on delta
				// If dangerous ball has reached this next coordinate
						// If another coordinate exists
								// Set next coordinate to this new coordinate
		// Set dangerous ball's sprite position & angle to match
		// the new position of dangerous ball's physical body
		// Return sprite after it's position/angle has been updated
		return null;
	}
}
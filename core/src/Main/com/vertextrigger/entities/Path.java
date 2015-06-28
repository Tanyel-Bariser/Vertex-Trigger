package com.vertextrigger.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

/**
 * Stores the predefined path of an entity's body & moves the body along
 * it's predefined path at a distance based on the frame rate delta.
 */
public class Path {
	private Body body;

	/**
	 * Sets body & the body's predefined path.
	 * Sets entity's initial position & sets the first coordinate
	 * the entity should move to along it's predefined path.
	 * 
	 * @param entity
	 * @param path
	 */
	public Path(Body body, Array<Vector2> coordinates) {
		// Set entity's physical body
		// Set entity's predefined path's coordinates
		// Set position of body based on first x & y coordinate of it's path
		// If path has a second coordinate
				// Set next coordinate to move to as the second coordinate in path
	}
	
	/**
	 * Moves body along it's predefined path by a distance
	 * dependent on delta for frame rate independent movement.
	 * 
	 * @param delta time passed between previous & current frame
	 */
	void moveAlongPath(float delta) {
		// If there is a next coordinate to move to
				// Move body toward next coordinate
				// at a distance dependent on delta
				// If body has reached this next coordinate
						// If another coordinate exists
								// Set next coordinate to this new coordinate
						// If another coordinate does not exist
								// Set next coordinate as the initial coordinate
	}
}
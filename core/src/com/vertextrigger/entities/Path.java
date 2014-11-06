package com.vertextrigger.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinate;

/**
 * Stores the predefined path of an entity & moves the entity along
 * it's predefined path at a distance based on the frame rate delta.
 */
public class Path {

	/**
	 * Sets entity & the entity's predefined path.
	 * Sets entity's initial position & sets the first coordinate
	 * the entity should move to along it's predefined path.
	 * 
	 * @param entity
	 * @param path
	 */
	public Path(Body entity, Array<Coordinate> coordinates) {
		// Set entity
		// Set entity's predefined path's coordinates
		// Set position of entity based on first x & y coordinate of it's path
		// If path has a second coordinate
				// Set next coordinate to move to as the second coordinate in path
	}
	
	/**
	 * Moves entity along it's predefined path by a distance
	 * dependent on delta for frame independent movement.
	 * 
	 * @param delta time passed between previous & current frame
	 */
	public void moveAlongPath(float delta) {
		// If there is a next coordinate to move to
				// Move entity toward next coordinate
				// at a distance dependent on delta
				// If entity has reached this next coordinate
						// If another coordinate exists
								// Set next coordinate to this new coordinate
						// If another coordinate does not exist
								// Set next coordinate as the initial coordinate
	}
}
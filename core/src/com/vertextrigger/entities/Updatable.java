package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Ensures all dynamic/kinematic game objects have an update method to update 
 * their physical position's & return their updated sprite for rendering.
 */
public interface Updatable {

	/**
	 * Updates an entity's physical position & returns
	 * the sprite after its position has been updated.
	 * 
	 * @param delta time passed between the previous & current frame
	 * @return updated sprite position for rendering of current frame
	 */
	Sprite update(float delta);
}
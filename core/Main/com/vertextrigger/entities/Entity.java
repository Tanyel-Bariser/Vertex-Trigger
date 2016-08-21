package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.vertextrigger.inanimate.portal.Teleportable;

/**
 * All Entities have a physical body & an associated sprite that need updating once per frame.
 *
 * Ensures all dynamic/kinematic game objects have an update method to update their physical position's & return their updated sprite for rendering.
 */
public interface Entity extends Teleportable {
	/**
	 * Updates an entity's physical position & returns the sprite after its position has been updated.
	 *
	 * @param delta
	 *            time passed between the previous & current frame
	 * @param alpha
	 * @return updated sprite position for rendering of current frame
	 */
	Sprite update(final float delta, final float alpha);

	void setFacingLeft();

	void setFacingRight();

	float getOffsetX();

	float getOffsetY();

	void cachePosition();
}
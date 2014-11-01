package com.vertextrigger.entities;

import com.badlogic.gdx.utils.Pool;

/**
 * A pool of bullets that can be reused to avoid allocation
 * 
 * Useful inherited methods include obtain() and free(Bullet)
 */
public class BulletPool extends Pool<Bullet> {

	/**
	 * Creates new bullet object if there are no more currently left 
	 * in the bullet pool. Lazy instantiation.
	 */
	@Override
	protected Bullet newObject() {
		// Create & return a new Bullet
		return null;
	}
}
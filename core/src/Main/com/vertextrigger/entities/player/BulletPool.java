package com.vertextrigger.entities.player;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;
import com.vertextrigger.factories.SpriteFactory;

/**
 * A pool of bullets that can be reused to avoid allocation
 * 
 * Useful inherited methods include obtain() and free(Bullet)
 */
class BulletPool extends Pool<Bullet> {
	private final World world;
	private final SpriteFactory factory;
	
	BulletPool(World world, SpriteFactory factory) {
		this.world = world;
		this.factory = factory;
	}

	/**
	 * Creates new bullet object if there are no more currently left 
	 * in the bullet pool. Lazy instantiation.
	 */
	@Override
	protected Bullet newObject() {
		return new Bullet(world, factory);
	}
}
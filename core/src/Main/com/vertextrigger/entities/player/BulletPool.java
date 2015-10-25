package com.vertextrigger.entities.player;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.animationfactory.AbstractAnimationFactory;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

/**
 * A pool of bullets that can be reused to avoid allocation
 * 
 * Useful inherited methods include obtain() and free(Bullet)
 */
class BulletPool extends Pool<Bullet> {
	private final World world;
	private final SpriteFactory spriteFactory;
	
	BulletPool(World world, SpriteFactory spriteFactory) {
		this.world = world;
		this.spriteFactory = spriteFactory;
	}

	/**
	 * Creates new bullet object if there are no more currently left 
	 * in the bullet pool. Lazy instantiation.
	 */
	@Override
	protected Bullet newObject() {
		GameObjectSize size = GameObjectSize.getBulletSize();
		Bullet bullet = new Bullet(world, new SpriteFactory().createCoreSprite("bullet", size));
		AbstractGameScreen.addBullet(bullet);
		return bullet;
	}
}
package com.vertextrigger.entities.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.screens.GameScreen;

class Gun {
	private final GameScreen gameScreen;
	private final BulletPool bulletPool;
	private final Array<Bullet> bullets;

	Gun(World world, GameScreen gameScreen) {
		this(gameScreen, new BulletPool(world), new Array<Bullet>());
	}
	
	Gun(GameScreen gameScreen, BulletPool pool, Array<Bullet> bullets) {
		this.gameScreen = gameScreen;
		this.bulletPool = pool;
		this.bullets = bullets;
	}
	
	/**
	 * Bullets are shot from the position of the player's gun
	 * in the direction the player is facing
	 */
	void shoot(Vector2 position, float horizontalDirection) {
		Bullet bullet = bulletPool.obtain();
		//TODO Set bullet position to exact height of gun
		bullet.setPosition(position);

		boolean gunPointingLeft = 0 > horizontalDirection;
		bullet.shoot(gunPointingLeft);

		bullets.add(bullet);
		gameScreen.addEntity(bullet);
	}

	void freeExpiredBullets() {
		for(Bullet bullet : bullets) {
			if(bullet.isExistenceTimeExpired()) {
				bulletPool.free(bullet);
				bullets.removeValue(bullet, true);
			}
		}
	}
}
package com.vertextrigger.entities.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.screen.AbstractGameScreen;

public class Gun {
	private final AbstractGameScreen gameScreen;
	private final BulletPool bulletPool;
	private final Array<Bullet> bullets;

	public Gun(AbstractGameScreen gameScreen, BulletPool pool) {
		this.gameScreen = gameScreen;
		this.bulletPool = pool;
		bullets = new Array<Bullet>();
	}
	
	/**
	 * Bullets are shot from the position of the player's gun
	 * in the direction the player is facing
	 */
	void shoot(Vector2 position, boolean gunPointingLeft) {
		Bullet bullet = bulletPool.obtain();
		if (gunPointingLeft) {
			bullet.setPosition(new Vector2(position.x - 0.01f, position.y+0.6f));
		} else {
			bullet.setPosition(new Vector2(position.x + 0.01f, position.y+0.6f));
		}
		bullet.shoot(gunPointingLeft);

		bullets.add(bullet);
		gameScreen.addEntity(bullet);
	}

	void freeExpiredBullets() {
		for(Bullet bullet : bullets) {
			boolean initial = bullet.isInInitialPosition();
			if (!bullet.isVisible() && !initial) {
				bulletPool.free(bullet);
				bullets.removeValue(bullet, true);
			}
		}
	}
	
	void destroyTouchingBullets() {
		for (Bullet bullet : bullets) {
			if (!bullet.getBody().isAwake()) {
				bulletPool.free(bullet);
				bullets.removeValue(bullet, true);
			}
		}
	}
}
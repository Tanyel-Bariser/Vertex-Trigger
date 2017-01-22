package com.vertextrigger.entities.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.Timer.Task;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.bullet.Bullet;
import com.vertextrigger.factory.entityfactory.BulletFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class Gun {
	private static final float SHOT_POWER = 0.0015f;
	private final AbstractGameScreen gameScreen;
	private final BulletFactory bulletFactory;
	private boolean canShoot = true;
	private boolean isPlayerShielded = false;

	public Gun(final AbstractGameScreen gameScreen, final BulletFactory bulletFactory) {
		this.gameScreen = gameScreen;
		this.bulletFactory = bulletFactory;
	}

	/**
	 * Bullets are shot from the position of the player's gun in the direction the player is facing
	 */
	boolean shoot(final Vector2 position, final boolean gunPointingLeft) {
		if (canShoot) {
			final Bullet bullet = bulletFactory.createPlayerBullet();
			bullet.getBody().setActive(true);
			if (gunPointingLeft) {
				bullet.setPosition(new Vector2(position.x - getShootOffsetDistanceX(), position.y + getShootOffsetDistanceY()));
			} else {
				bullet.setPosition(new Vector2(position.x + getShootOffsetDistanceX(), position.y + getShootOffsetDistanceY()));
			}

			shootBullet(bullet, gunPointingLeft);
			AudioManager.playShootSound();
			gameScreen.addEntity(bullet);
			canShoot = false;

			Timer.schedule(new Task() {
				@Override
				public void run() {
					canShoot = true;
				}
			}, 0.5f);

			return true;
		}
		return false;
	}

	private float getShootOffsetDistanceX() {
		return isPlayerShielded ? 0.3f : 0.1f;
	}

	private float getShootOffsetDistanceY() {
		return 0.1f;
	}

	void setShielded() {
		isPlayerShielded = true;
	}

	private void shootBullet(final Bullet bullet, final boolean shootLeft) {
		final Vector2 velocity = shootLeft ? new Vector2(-SHOT_POWER, 0) : new Vector2(SHOT_POWER, 0);
		bullet.shoot(velocity);
	}
}
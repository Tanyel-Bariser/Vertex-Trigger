package com.vertextrigger.entities.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.Timer.Task;
import com.vertextrigger.factory.entityfactory.BulletFactory;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.AudioManager;

public class Gun {
	private final AbstractGameScreen gameScreen;
	private final BulletFactory bulletFactory;
	private boolean canShoot = true;

	public Gun(final AbstractGameScreen gameScreen, final BulletFactory bulletFactory) {
		this.gameScreen = gameScreen;
		this.bulletFactory = bulletFactory;
	}

	/**
	 * Bullets are shot from the position of the player's gun in the direction the player is facing
	 */
	boolean shoot(final Vector2 position, final boolean gunPointingLeft) {
		if (canShoot) {
			final Bullet bullet = bulletFactory.createBullet();
			bullet.getBody().setActive(true);
			if (gunPointingLeft) {
				bullet.setPosition(new Vector2(position.x - 0.1f, position.y + 0.1f));
			} else {
				bullet.setPosition(new Vector2(position.x + 0.1f, position.y + 0.1f));
			}

			bullet.shoot(gunPointingLeft);
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

}
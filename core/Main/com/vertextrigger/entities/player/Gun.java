package com.vertextrigger.entities.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.factory.entityfactory.BulletFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class Gun {
	private final AbstractGameScreen gameScreen;
	private final BulletFactory bulletFactory;

	public Gun(AbstractGameScreen gameScreen, BulletFactory bulletFactory) {
		this.gameScreen = gameScreen;
		this.bulletFactory = bulletFactory;
	}
	
	
	/**
	 * Bullets are shot from the position of the player's gun
	 * in the direction the player is facing
	 */
	void shoot(Vector2 position, boolean gunPointingLeft) {
		Bullet bullet = bulletFactory.createBullet();
		bullet.getBody().setActive(true);
		if (gunPointingLeft) {
			bullet.setPosition(new Vector2(position.x - 0.1f, position.y + 0.1f));
		} else {
			bullet.setPosition(new Vector2(position.x + 0.1f, position.y + 0.1f));
		}
		bullet.shoot(gunPointingLeft);

		gameScreen.addEntity(bullet);
	}
	
	
}
package com.vertextrigger.entities.player;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.screens.GameScreen;

public class Gun {
	private final Body player;
	private final GameScreen gameScreen;
	private final BulletPool bulletPool;

	Gun(World world, Body player, GameScreen gameScreen) {
		this.player = player;
		this.gameScreen = gameScreen;
		bulletPool = new BulletPool(world);
	}
	
	/**
	 * Bullets are shot from the position of the player's gun
	 * in the direction the player is facing
	 */
	public void shoot() {
		Bullet bullet = bulletPool.obtain();
		//TODO Set bullet position to exact position of gun
		bullet.setPosition(player.getPosition());

		boolean gunPointingLeft = 0 > player.getLinearVelocity().x;
		bullet.shoot(gunPointingLeft);

		gameScreen.addEntity(bullet);
	}
}
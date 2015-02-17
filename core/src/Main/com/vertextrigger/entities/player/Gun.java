package com.vertextrigger.entities.player;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.screens.GameScreen;

public class Gun {
	private final Body player;
	private final GameScreen gameScreen;
	private final BulletPool bulletPool;
	private final Array<Bullet> bullets;

	Gun(World world, Body player, GameScreen gameScreen) {
		this(player, gameScreen, new BulletPool(world), new Array<Bullet>());
	}
	
	Gun(Body player, GameScreen gameScreen, BulletPool pool, Array<Bullet> bullets) {
		this.player = player;
		this.gameScreen = gameScreen;
		this.bulletPool = pool;
		this.bullets = bullets;
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

		bullets.add(bullet);
		gameScreen.addEntity(bullet);
	}
}
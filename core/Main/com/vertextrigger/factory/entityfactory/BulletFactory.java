package com.vertextrigger.factory.entityfactory;

import static com.vertextrigger.util.GameObjectSize.*;

import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.bullet.*;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.BulletBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class BulletFactory {
	private final World world;

	public BulletFactory(final World world) {
		this.world = world;
	}

	public Bullet createBeeBullet() {
		final Body bulletBody = createBulletBody();
		final Bullet bullet = new BeeBullet(bulletBody, new SpriteFactory().createCoreSprite("bullet", BEE_BULLET_SIZE));
		AbstractGameScreen.addBullet(bullet);
		return bullet;
	}

	public Bullet createPlayerBullet() {
		final Body bulletBody = createBulletBody();
		final Bullet bullet = new PlayerBullet(bulletBody, new SpriteFactory().createCoreSprite("bullet", PLAYER_BULLET_SIZE));
		AbstractGameScreen.addBullet(bullet);
		return bullet;
	}

	private Body createBulletBody() {
		final BulletBodyFactory factory = new BulletBodyFactory();
		return factory.createBulletBody(world);
	}
}
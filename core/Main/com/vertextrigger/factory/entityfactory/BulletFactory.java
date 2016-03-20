package com.vertextrigger.factory.entityfactory;

import static com.vertextrigger.util.GameObjectSize.BULLET_SIZE;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.entities.player.Bullet;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.BulletBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

public class BulletFactory {
	private final World world;
	
	BulletFactory(World world) {
		this.world = world;
	}

	public Bullet createBullet() {
	GameObjectSize size = BULLET_SIZE;
	BulletBodyFactory factory = new BulletBodyFactory();
	Body bulletBody = factory.createBulletBody(world);
	Bullet bullet = new Bullet(bulletBody, new SpriteFactory().createCoreSprite("bullet", size));
	AbstractGameScreen.addBullet(bullet);
	return bullet;
	}
}
package com.vertextrigger.factory.entityfactory;

import static com.vertextrigger.util.GameObjectSize.*;

import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.ai.*;
import com.vertextrigger.entities.MagnetFlowField;
import com.vertextrigger.entities.bullet.*;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.BulletBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class BulletFactory {
	private final World world;
	private final MagnetFlowField magnetFlowField;

	public BulletFactory(final World world, final MagnetFlowField magnetFlowField) {
		this.world = world;
		this.magnetFlowField = magnetFlowField;
	}

	public Bullet createBeeBullet() {
		final float zeroLinearSpeedThreshold = 0.1f;
		final float maxLinearSpeed = 10f;
		final float maxLinearAcceleration = 10;
		final float maxAngularSpeed = 10f;
		final float maxAngularAcceleration = 10f;

		final Body bulletBody = createBulletBody(true, false);
		final SteerableBody steerableBody = new SteerableBody(bulletBody, maxLinearAcceleration, maxLinearSpeed, maxAngularAcceleration,
				maxAngularSpeed, zeroLinearSpeedThreshold, 10, false);
		final MagnetBehaviour magnetBehaviour = magnetFlowField != null ? new MagnetBehaviour(steerableBody, magnetFlowField) : null;
		final Bullet bullet = new BeeBullet(bulletBody, new SpriteFactory().createEnemySprite("sting", BEE_BULLET_SIZE), magnetBehaviour);
		AbstractGameScreen.addBullet(bullet);
		return bullet;
	}

	public Bullet createPlayerBullet() {
		final Body bulletBody = createBulletBody(false, true);
		final Bullet bullet = new PlayerBullet(bulletBody, new SpriteFactory().createCoreSprite("bullet", PLAYER_BULLET_SIZE));
		AbstractGameScreen.addBullet(bullet);
		return bullet;
	}

	private Body createBulletBody(final boolean isAffectedByGravity, final boolean isBouncy) {
		final BulletBodyFactory factory = new BulletBodyFactory();
		return factory.createBulletBody(world, isAffectedByGravity, isBouncy);
	}
}
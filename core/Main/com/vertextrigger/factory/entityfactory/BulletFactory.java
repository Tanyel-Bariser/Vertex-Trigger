package com.vertextrigger.factory.entityfactory;

import static com.vertextrigger.util.GameObjectSize.*;

import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField.FlowField;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.ai.*;
import com.vertextrigger.entities.bullet.*;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.BulletBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class BulletFactory {
	private final World world;
	private final Array<FlowField<Vector2>> magnetFlowFields;

	public BulletFactory(final World world, final Array<FlowField<Vector2>> magnetFlowFields) {
		this.world = world;
		this.magnetFlowFields = magnetFlowFields;
	}

	public Bullet createBeeBullet() {
		final float zeroLinearSpeedThreshold = 0.1f;
		final float maxLinearSpeed = 10f;
		final float maxLinearAcceleration = 10;
		final float maxAngularSpeed = 10f;
		final float maxAngularAcceleration = 10f;

		final Body bulletBody = createBulletBody();
		final SteerableBody steerableBody = new SteerableBody(bulletBody, maxLinearAcceleration, maxLinearSpeed, maxAngularAcceleration,
				maxAngularSpeed, zeroLinearSpeedThreshold, 10, false);
		final Array<Magnet> magnets = new Array<>();
		for (final FlowField<Vector2> magnetFlowField : magnetFlowFields) {
			magnets.add(new Magnet(steerableBody, magnetFlowField));
		}
		final Bullet bullet = new BeeBullet(bulletBody, new SpriteFactory().createCoreSprite("bullet", BEE_BULLET_SIZE), magnets);
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
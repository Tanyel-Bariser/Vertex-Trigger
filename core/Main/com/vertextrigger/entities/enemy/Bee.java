package com.vertextrigger.entities.enemy;

import static com.vertextrigger.util.GameObjectSize.BEE_SIZE;

import com.badlogic.gdx.ai.steer.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.ai.SteerableBody;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.bullet.Bullet;
import com.vertextrigger.factory.entityfactory.BulletFactory;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

public class Bee extends AbstractFlyingEnemy {
	private static final float HEIGHT_ABOVE_PLAYER = 2f;
	private static final int SHOT_FREQUENCY = 3;
	final Steerable<Vector2> target;
	final AbstractGameScreen screen;
	private final BulletFactory bulletFactory;
	private float timeElapsed;
	private final SteerableBody steerable;

	public Bee(final Body body, final AnimationSet animationSet, final Steerable<Vector2> target, final BulletFactory bulletFactory,
			final AbstractGameScreen screen, final SteerableBody steerable) {
		super(body, animationSet, target, steerable);
		this.target = target;
		this.bulletFactory = bulletFactory;
		this.screen = screen;
		this.steerable = steerable;
	}

	@Override
	public void die() {
		AudioManager.playEnemyKilledSound();
		animator.playDeathAnimation(this);
	}

	@Override
	public float getOffsetX() {
		return BEE_SIZE.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return BEE_SIZE.getOffsetY();
	}

	@Override
	protected void applyWanderSteering(final SteeringAcceleration<Vector2> steering, final float time) {
		if (isNotSufficientlyHigherThanTarget()) {
			moveHigher();
		} else {
			super.applyWanderSteering(steering, time);
		}
	}

	private boolean isNotSufficientlyHigherThanTarget() {
		return (getPosition().y - target.getPosition().y) < HEIGHT_ABOVE_PLAYER;
	}

	private void moveHigher() {
		steerable.setLinearVelocity(steerable.getLinearVelocity().add(0, 0.1f));
	}

	private void shoot() {
		final Bullet bullet = bulletFactory.createBeeBullet();
		bullet.getBody().setActive(true);

		final float beeHeight = GameObjectSize.BEE_SIZE.getPhysicalHeight();

		bullet.setPosition(getPosition().add(0, -(beeHeight * 2)));
		bullet.cachePosition();

		final Vector2 velocity = calculateVelocity(steerable.getOrientation());

		bullet.shoot(velocity);
		AudioManager.playBeeShootSound();
		screen.addEntity(bullet);
	}

	private Vector2 calculateVelocity(final float orientation) {
		return new Vector2(getShotPower(-(float) Math.sin(orientation)), getShotPower((float) Math.cos(orientation)));
	}

	private float getShotPower(final float shotPower) {
		return shotPower / 1_000;
	}

	@Override()
	public Sprite update(final float delta, final float alpha) {
		timeElapsed += delta;

		if (timeElapsed >= SHOT_FREQUENCY) {
			shoot();
			timeElapsed = 0;
		}

		return super.update(delta, alpha);
	}
}
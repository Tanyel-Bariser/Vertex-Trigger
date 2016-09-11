package com.vertextrigger.entities.enemy;

import static com.vertextrigger.util.GameObjectSize.BEE_SIZE;

import com.badlogic.gdx.ai.steer.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.bullet.Bullet;
import com.vertextrigger.factory.entityfactory.BulletFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class Bee extends AbstractFlyingEnemy {
	final Steerable<Vector2> target;
	final AbstractGameScreen screen;
	private final BulletFactory bulletFactory;

	public Bee(final Body body, final AnimationSet animationSet, final Steerable<Vector2> target, final BulletFactory bulletFactory,
			final AbstractGameScreen screen) {
		super(body, animationSet, target);
		this.target = target;
		this.bulletFactory = bulletFactory;
		this.screen = screen;
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
		return (getPosition().y - target.getPosition().y) < 1f;
	}

	private void moveHigher() {
		setLinearVelocity(getLinearVelocity().add(0, 0.1f));
	}

	private void shoot() {
		final Bullet bullet = bulletFactory.createBeeBullet();
		bullet.getBody().setActive(true);
		bullet.setPosition(new Vector2(0, 0));
		bullet.cachePosition();

		final Vector2 velocity = calculateVelocity(getPosition(), getOrientation());

		bullet.shoot(velocity);
		AudioManager.playBeeShootSound();
		screen.addEntity(bullet);
	}

	private Vector2 calculateVelocity(final Vector2 position, final float orientation) {
		return new Vector2(-0.0015f, -0.0015f);
	}

	float x = 0;

	@Override()
	public Sprite update(final float delta, final float alpha) {
		x++;
		if (x > 10) {
			shoot();
			x = 0;
		}

		return super.update(delta, alpha);
	}
}
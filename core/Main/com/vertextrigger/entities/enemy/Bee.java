package com.vertextrigger.entities.enemy;

import static com.vertextrigger.util.GameObjectSize.BEE_SIZE;

import com.badlogic.gdx.ai.steer.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.entities.AnimationSet;

public class Bee extends AbstractFlyingEnemy {
	final Steerable<Vector2> target;

	public Bee(final Body body, final AnimationSet animationSet, final Steerable<Vector2> target) {
		super(body, animationSet, target);
		this.target = target;
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
}
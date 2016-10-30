package com.vertextrigger.entities.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.ai.SteerableBody;
import com.vertextrigger.entities.MagnetFlowField;

public class BeeBullet extends EnemyBullet {
	private static final MagnetFlowField FLOW_FIELD = new MagnetFlowField(null, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 100);
	private final SteeringAcceleration<Vector2> magnetFieldSteeringOutput;
	private final FollowFlowField<Vector2> magneticField;
	private final SteerableBody steerable;

	public BeeBullet(final Body body, final Sprite sprite, final SteerableBody steerable) {
		super(body, sprite);
		this.steerable = steerable;
		magnetFieldSteeringOutput = new SteeringAcceleration<>(new Vector2());
		magneticField = new FollowFlowField<>(steerable);
		magneticField.setFlowField(FLOW_FIELD);
	}

	@Override
	public Sprite update(final float delta, final float alpha) {
		magneticField.calculateSteering(magnetFieldSteeringOutput);
		applySteering(magnetFieldSteeringOutput, delta); // DELEGATE applySteering() METHOD TO MAGNET CLASS
		return super.update(delta, alpha);
	}

	private void applySteering(final SteeringAcceleration<Vector2> steering, final float time) {
		// Update position and linear velocity. Velocity is trimmed to maximum speed
		steerable.setPosition(getPosition().mulAdd(steerable.getLinearVelocity(), time));
		steerable.setLinearVelocity(steerable.getLinearVelocity().mulAdd(steering.linear, time).limit(steerable.getMaxLinearSpeed()));

		// Update orientation and angular velocity setOrientation(getOrientation() + (getAngularVelocity() * time));
		steerable.setAngularVelocity(steerable.getAngularVelocity() + (steering.angular * time));
	}

	@Override
	public boolean isTooSlow() {
		return false; // TODO remove me after magnet tested
	}
}
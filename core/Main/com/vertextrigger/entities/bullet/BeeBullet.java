package com.vertextrigger.entities.bullet;

import com.badlogic.gdx.ai.steer.*;
import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField.FlowField;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BeeBullet extends EnemyBullet implements Steerable<Vector2> {
	private final SteeringAcceleration<Vector2> magnetFieldSteeringOutput;
	private final FollowFlowField<Vector2> magneticField;
	private final boolean independentFacing = true;
	private float zeroLinearSpeedThreshold = 0.1f;
	private float maxLinearSpeed = 10f;
	private float maxLinearAcceleration = 10;
	private float maxAngularSpeed = 10f;
	private float maxAngularAcceleration = 10f;
	private boolean isTagged;

	public BeeBullet(final Body body, final Sprite sprite) {
		super(body, sprite);
		magnetFieldSteeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
		magneticField = new FollowFlowField<Vector2>(this);
		magneticField.setFlowField(new FlowField<Vector2>() {
			@Override
			public Vector2 lookup(final Vector2 position) {
				return null;
			}
		});
	}

	@Override
	public Sprite update(final float delta, final float alpha) {
		magneticField.calculateSteering(magnetFieldSteeringOutput);
		applyMagnetFieldSteering(magnetFieldSteeringOutput, delta);
		return super.update(delta, alpha);
	}

	private void applyMagnetFieldSteering(final SteeringAcceleration<Vector2> steering, final float time) {
		// Update position and linear velocity. Velocity is trimmed to maximum speed
		setLinearVelocity(steering.linear);
		// Update orientation and angular velocity
		if (independentFacing) {
			setOrientation(getOrientation() + (getAngularVelocity() * time));
			setAngularVelocity(getAngularVelocity() + (steering.angular * time));
		} else {
			// For non-independent facing we have to align orientation to linear velocity
			final float newOrientation = calculateOrientationFromLinearVelocity(this);
			if (newOrientation != getOrientation()) {
				setAngularVelocity((newOrientation - getOrientation()) * time);
				setOrientation(newOrientation);
			}
		}
	}

	private void setAngularVelocity(final float angularVelocity) {
		body.setAngularVelocity(angularVelocity);
	}

	protected void setLinearVelocity(final Vector2 linearVelocity) {
		body.setLinearVelocity(linearVelocity);
	}

	// For non-independent facing
	public float calculateOrientationFromLinearVelocity(final Steerable<Vector2> steerable) {
		// If we haven't got any velocity, then we can do nothing.
		if (steerable.getLinearVelocity().isZero(steerable.getZeroLinearSpeedThreshold())) {
			return steerable.getOrientation();
		}

		return steerable.vectorToAngle(steerable.getLinearVelocity());
	}

	// STEERABLE METHODS BELOW

	/* Here you should implement missing methods inherited from Steerable */

	// Actual implementation depends on your coordinate system.
	// Here we assume the y-axis is pointing upwards.
	@Override
	public float vectorToAngle(final Vector2 vector) {
		return (float) Math.atan2(-vector.x, vector.y);
	}

	// Actual implementation depends on your coordinate system.
	// Here we assume the y-axis i)s pointing upwards.
	@Override
	public Vector2 angleToVector(final Vector2 outVector, final float angle) {
		outVector.x = -(float) Math.sin(angle);
		outVector.y = (float) Math.cos(angle);
		return outVector;
	}

	@Override
	public float getOrientation() {
		return body.getAngle();
	}

	@Override
	public void setOrientation(final float orientation) {
		body.setTransform(getPosition(), orientation);
	}

	@Override
	public Location<Vector2> newLocation() {
		return this;
	}

	@Override
	public float getZeroLinearSpeedThreshold() {
		return zeroLinearSpeedThreshold;
	}

	@Override
	public void setZeroLinearSpeedThreshold(final float zeroLinearSpeedThreshold) {
		this.zeroLinearSpeedThreshold = zeroLinearSpeedThreshold;
	}

	@Override
	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed(final float maxLinearSpeed) {
		this.maxLinearSpeed = maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		return maxLinearAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration(final float maxLinearAcceleration) {
		this.maxLinearAcceleration = maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed(final float maxAngularSpeed) {
		this.maxAngularSpeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration() {
		return maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(final float maxAngularAcceleration) {
		this.maxAngularAcceleration = maxAngularAcceleration;
	}

	@Override
	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}

	@Override
	public float getAngularVelocity() {
		return body.getAngularVelocity();
	}

	@Override
	public float getBoundingRadius() {
		return 10;
	}

	@Override
	public boolean isTagged() {
		return isTagged;
	}

	@Override
	public void setTagged(final boolean isTagged) {
		this.isTagged = isTagged;
	}
}
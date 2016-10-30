package com.vertextrigger.ai;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class SteerableBody implements Steerable<Vector2> {
	private float zeroSpeedThreshold;
	private float maxLinearSpeed;
	private float maxLinearAcceleration;
	private float maxAngularSpeed;
	private float maxAngularAcceleration;
	private final float boundingRadius;
	private boolean tagged;
	private final Body body;

	public SteerableBody(final Body body, final float maxLinearAcc, final float maxLinearSpeed, final float maxAngularAcc,
			final float maxAngularSpeed, final float zeroThreshold, final float boundingRadius, final boolean tagged) {
		this.body = body;
		maxLinearAcceleration = maxLinearAcc;
		maxAngularAcceleration = maxAngularAcc;
		this.maxLinearSpeed = maxLinearSpeed;
		this.maxAngularSpeed = maxAngularSpeed;
		this.tagged = tagged;
		this.boundingRadius = boundingRadius;
		zeroSpeedThreshold = zeroThreshold;
	}

	@Override
	public Vector2 getPosition() {
		return body.getPosition();
	}

	@Override
	public float getOrientation() {
		return body.getAngle();
	}

	@Override
	public void setOrientation(final float orientation) {
		body.setTransform(getPosition(), orientation);
	}

	// Actual implementation depends on your coordinate system.
	// Here we assume the y-axis is pointing upwards.
	@Override
	public float vectorToAngle(final Vector2 vector) {
		return (float) Math.atan2(-vector.x, vector.y);
	}

	// Actual implementation depends on your coordinate system.
	// Here we assume the y-axis is pointing upwards.
	@Override
	public Vector2 angleToVector(final Vector2 outVector, final float angle) {
		outVector.x = -(float) Math.sin(angle);
		outVector.y = (float) Math.cos(angle);
		return outVector;
	}

	@Override
	public Location<Vector2> newLocation() {
		return this;
	}

	@Override
	public float getZeroLinearSpeedThreshold() {
		return zeroSpeedThreshold;
	}

	@Override
	public void setZeroLinearSpeedThreshold(final float value) {
		zeroSpeedThreshold = value;
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

	public void setLinearVelocity(final Vector2 linearVelocity) {
		body.setLinearVelocity(linearVelocity);
	}

	@Override
	public float getAngularVelocity() {
		return body.getAngularVelocity();
	}

	public void setAngularVelocity(final float angularVelocity) {
		body.setAngularVelocity(angularVelocity);
	}

	@Override
	public float getBoundingRadius() {
		return boundingRadius;
	}

	@Override
	public boolean isTagged() {
		return tagged;
	}

	@Override
	public void setTagged(final boolean tagged) {
		this.tagged = tagged;
	}

	public void setPosition(final Vector2 position) {
		body.setTransform(position, getOrientation());
	}
}
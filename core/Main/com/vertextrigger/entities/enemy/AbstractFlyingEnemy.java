package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.entities.AbstractEntity;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.AnimatorImpl;
import com.vertextrigger.entities.Mortal;

public abstract class AbstractFlyingEnemy extends AbstractEntity implements Enemy, Mortal, Steerable<Vector2> {
	// STEERING FIELDS
	private final SteeringAcceleration<Vector2> faceSteeringOutput;
	private final SteeringAcceleration<Vector2> wanderSteeringOutput;
	private SteeringBehavior<Vector2> face;
	private SteeringBehavior<Vector2> wander;
	private float zeroLinearSpeedThreshold = 0.1f;
	private float maxLinearSpeed = 0.1f;
	private float maxLinearAcceleration = 1f;
	private float maxAngularSpeed = 30f;
	private float maxAngularAcceleration = 30f;
	private boolean isTagged;

	public AbstractFlyingEnemy(final Body body, final AnimationSet animationSet, final Steerable<Vector2> target) {
		super(body, new AnimatorImpl(animationSet));
		faceSteeringOutput = new SteeringAcceleration<>(new Vector2());
		wanderSteeringOutput = new SteeringAcceleration<>(new Vector2());
		if (target != null) {
			face = new Face<>(this).setDecelerationRadius(5f).setTarget(target);
			wander = new Wander<>(this) //
					.setWanderRadius(250) //
					.setWanderRate(MathUtils.PI2 * 4) //
					.setTarget(target); //
		}
	}

	/**
	 * Moves the enemy further along its predefined path with the distance moved dependent on the delta. Delta is needed to frame rate independent
	 * movement.
	 *
	 * Chooses appropriate enemy sprite based on animation. Returns the updated enemy's sprite for rendering.
	 *
	 * @param delta
	 *            time passed between previous & current frame
	 * @return updated enemy sprite
	 */
	@Override
	public Sprite update(final float delta, final float alpha) {
		if (face != null) {
			GdxAI.getTimepiece().update(delta);
			// Calculate steering acceleration
			face.calculateSteering(faceSteeringOutput);
			wander.calculateSteering(wanderSteeringOutput);

			/*
			 * Here you might want to add a motor control layer filtering steering accelerations. For instance, a car in a driving game has physical
			 * constraints on its movement: - it cannot turn while stationary - the faster it moves, the slower it can turn (without going into a
			 * skid) - it can brake much more quickly than it can accelerate - it only moves in the direction it is facing (ignoring power slides)
			 */

			// Apply steering acceleration to move this agent
			applyFaceSteering(faceSteeringOutput, delta);
			applyWanderSteering(wanderSteeringOutput, delta);
		}

		return super.update(delta, alpha);
	}

	/** wander just responsible for linear velocity, not angle/orientation */
	protected void applyWanderSteering(final SteeringAcceleration<Vector2> steering, final float time) {
		// Update position and linear velocity. Velocity is trimmed to maximum speed
		setLinearVelocity(steering.linear);
	}

	/** face solely responsible for angle/orientation */
	private void applyFaceSteering(final SteeringAcceleration<Vector2> steering, final float time) {
		// Update orientation and angular velocity
		setOrientation(getOrientation() + (getAngularVelocity() * time));
		setAngularVelocity(getAngularVelocity() + (steering.angular * time));
	}

	private void setAngularVelocity(final float angularVelocity) {
		body.setAngularVelocity(angularVelocity);
	}

	protected void setLinearVelocity(final Vector2 linearVelocity) {
		body.setLinearVelocity(linearVelocity);
	}

	// STEERABLE METHODS BELOW

	// Actual implementation depends on your coordinate system.
	// Here we assume the y-axis is pointing upwards.
	/** reversed default implementation as we want bee to face player with its rear end rather than front */
	@Override
	public float vectorToAngle(final Vector2 vector) {
		return (float) Math.atan2(vector.x, -vector.y);
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
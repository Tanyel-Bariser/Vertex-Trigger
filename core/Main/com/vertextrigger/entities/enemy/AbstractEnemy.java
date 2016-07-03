package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.*;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.Path;

/**
 * Enemies can kill the player if touched & follows a predefined path This class manages an enemy's physical body & its movements & sprite animation
 */
public abstract class AbstractEnemy implements Steerable<Vector2>, Mortal {
	protected Path path;
	protected Body body;
	protected AnimationSet animationSet;
	protected Animator animator;
	protected boolean isDead;
	protected Vector2 newPositionFromPortal;
	protected boolean facingLeft;
	protected boolean isDeathAnimationFinished;
	private final InterpolatedPosition enemyState;
	private boolean isTeleportable = true;

	// STEERING FIELDS
	private final SteeringAcceleration<Vector2> steeringOutput;
	private final SteeringBehavior<Vector2> steeringBehavior;
	private final boolean independentFacing = true;

	public AbstractEnemy(final Array<Vector2> coordinates, final Body body, final AnimationSet animationSet) {
		path = null;
		this.body = body;
		this.animationSet = animationSet;
		isDeathAnimationFinished = false;
		isDead = false;
		animator = new Animator(animationSet);
		animator.setEntity(this);
		setUserData(body);
		enemyState = new InterpolatedPosition(this.body);
		steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
		steeringBehavior = new Wander<Vector2>(this).setFaceEnabled(true) // We want to use Face internally (independent facing is on)
				.setAlignTolerance(0.001f) // Used by Face
				.setDecelerationRadius(5) // Used by Face
				.setTimeToTarget(0.1f) // Used by Face
				.setWanderOffset(90) //
				.setWanderOrientation(10) //
				.setWanderRadius(40) //
				.setWanderRate(MathUtils.PI2 * 4).setTarget(new Location<Vector2>() {

					@Override
					public float vectorToAngle(final Vector2 vector) {
						return (float) Math.atan2(-vector.x, vector.y);
					}

					@Override
					public void setOrientation(final float orientation) {
					}

					@Override
					public Location<Vector2> newLocation() {
						return this;
					}

					@Override
					public Vector2 getPosition() {
						return new Vector2(10, 0);
					}

					@Override
					public float getOrientation() {
						return 0;
					}

					@Override
					public Vector2 angleToVector(final Vector2 outVector, final float angle) {
						outVector.x = -(float) Math.sin(angle);
						outVector.y = (float) Math.cos(angle);
						return outVector;
					}
				});
	}

	@Override
	public Body getBody() {
		return body;
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
		if (steeringBehavior != null) {
			GdxAI.getTimepiece().update(delta);
			// Calculate steering acceleration
			steeringBehavior.calculateSteering(steeringOutput);

			/*
			 * Here you might want to add a motor control layer filtering steering accelerations. For instance, a car in a driving game has physical
			 * constraints on its movement: - it cannot turn while stationary - the faster it moves, the slower it can turn (without going into a
			 * skid) - it can brake much more quickly than it can accelerate - it only moves in the direction it is facing (ignoring power slides)
			 */

			// Apply steering acceleration to move this agent
			// applySteering(steeringOutput, delta);
		}

		if (newPositionFromPortal != null) {
			body.setTransform(newPositionFromPortal, 0);
			setNewPositionFromPortal(null);
		}
		return animator.getUpdatedSprite(delta, enemyState.getNewPosition(alpha, body), enemyState.getNewAngle(alpha, body));
		// Move enemy further along it's predefined path based on delta
		// Update enemy sprite based on animation
		// Set enemy's sprite position & angle to match
		// the new position of enemy's physical body
		// Return enemy sprite after it's position/angle has been updated
	}

	private void applySteering(final SteeringAcceleration<Vector2> steering, final float time) {
		// Update position and linear velocity. Velocity is trimmed to maximum speed
		getPosition().mulAdd(getLinearVelocity(), time);
		getLinearVelocity().mulAdd(steering.linear, time).limit(getMaxLinearSpeed());

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
		// setLinearVelocity(steering.linear);
	}

	// For non-independent facing
	public float calculateOrientationFromLinearVelocity(final Steerable<Vector2> steerable) {
		// If we haven't got any velocity, then we can do nothing.
		if (steerable.getLinearVelocity().isZero(steerable.getZeroLinearSpeedThreshold())) {
			return steerable.getOrientation();
		}

		return steerable.vectorToAngle(steerable.getLinearVelocity());
	}

	@Override
	public void setFacingLeft() {
		facingLeft = true;
	}

	@Override
	public void setFacingRight() {
		facingLeft = false;
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	@Override
	public void setDead() {
		isDead = true;
	}

	@Override
	public void setDeathAnimationFinished() {
		isDeathAnimationFinished = true;
	}

	@Override
	public boolean isDeathAnimationFinished() {
		return isDeathAnimationFinished;
	}

	@Override
	public void die() {
		AudioManager.playEnemyKilledSound();
		animator.playDeathAnimation(this);
	}

	@Override
	public void setUserData(final Body body) {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}

	@Override
	public void setNewPositionFromPortal(final Vector2 newPositionFromPortal) {
		this.newPositionFromPortal = newPositionFromPortal;
	}

	@Override
	public void cachePosition() {
		enemyState.setState(body);
	}

	@Override
	public boolean isTeleportable() {
		return isTeleportable;
	}

	@Override
	public void exitedPortal() {
		isTeleportable ^= true;
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
	// Here we assume the y-axis is pointing upwards.
	@Override
	public Vector2 angleToVector(final Vector2 outVector, final float angle) {
		outVector.x = -(float) Math.sin(angle);
		outVector.y = (float) Math.cos(angle);
		return outVector;
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

	private void setAngularVelocity(final float angularVelocity) {
		body.setAngularVelocity(angularVelocity);
	}

	@Override
	public float getBoundingRadius() {
		return 10;
	}

	@Override
	public boolean isTagged() {
		return false;
	}

	@Override
	public void setTagged(final boolean tagged) {
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
	public void setOrientation(final float angle) {
		body.setTransform(getPosition(), angle);
	}

	@Override
	public Location<Vector2> newLocation() {
		return null;
	}

	@Override
	public float getZeroLinearSpeedThreshold() {
		return 0;
	}

	@Override
	public void setZeroLinearSpeedThreshold(final float value) {
	}

	@Override
	public float getMaxLinearSpeed() {
		return 0.5f;
	}

	@Override
	public void setMaxLinearSpeed(final float maxLinearSpeed) {
	}

	@Override
	public float getMaxLinearAcceleration() {
		return 0.5f;
	}

	@Override
	public void setMaxLinearAcceleration(final float maxLinearAcceleration) {
	}

	@Override
	public float getMaxAngularSpeed() {
		return 0.5f;
	}

	@Override
	public void setMaxAngularSpeed(final float maxAngularSpeed) {
	}

	@Override
	public float getMaxAngularAcceleration() {
		return 0.5f;
	}

	@Override
	public void setMaxAngularAcceleration(final float maxAngularAcceleration) {
	}
}
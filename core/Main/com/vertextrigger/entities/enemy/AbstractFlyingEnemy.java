package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.*;
import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.ai.SteerableBody;
import com.vertextrigger.entities.*;

public abstract class AbstractFlyingEnemy extends AbstractEntity implements Enemy, Mortal {
	// STEERING FIELDS
	private final SteeringAcceleration<Vector2> faceSteeringOutput;
	private final SteeringAcceleration<Vector2> wanderSteeringOutput;
	private SteeringBehavior<Vector2> face;
	private SteeringBehavior<Vector2> wander;
	private final SteerableBody steerable;

	public AbstractFlyingEnemy(final Body body, final AnimationSet animationSet, final Steerable<Vector2> target, final SteerableBody steerable) {
		super(body, new AnimatorImpl(animationSet));
		this.steerable = steerable;
		faceSteeringOutput = new SteeringAcceleration<>(new Vector2());
		wanderSteeringOutput = new SteeringAcceleration<>(new Vector2());
		if (target != null) {
			face = new Face<>(steerable).setDecelerationRadius(5f).setTarget(target);
			wander = new Wander<>(steerable) //
					.setWanderOffset(90) //
					.setWanderOrientation(10) //
					.setWanderRadius(250) //
					.setWanderRate(MathUtils.PI2 * 4) //
					.setTarget(target); //
		}
	}

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

	protected void applyWanderSteering(final SteeringAcceleration<Vector2> steering, final float time) {
		// Update position and linear velocity. Velocity is trimmed to maximum speed
		steerable.setLinearVelocity(steering.linear);
		// Update orientation and angular velocity
		steerable.setOrientation(steerable.getOrientation() + (steerable.getAngularVelocity() * time));
		steerable.setAngularVelocity(steerable.getAngularVelocity() + (steering.angular * time));
	}

	private void applyFaceSteering(final SteeringAcceleration<Vector2> steering, final float time) {
		// Update orientation and angular velocity
		steerable.setOrientation(steerable.getOrientation() + (steerable.getAngularVelocity() * time));
		steerable.setAngularVelocity(steerable.getAngularVelocity() + (steering.angular * time));
	}
}
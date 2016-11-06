package com.vertextrigger.ai;

import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField.FlowField;
import com.badlogic.gdx.math.Vector2;

public class Magnet {
	private final SteeringAcceleration<Vector2> magnetFieldSteeringOutput;
	private final FollowFlowField<Vector2> magneticField;
	private final SteerableBody steerable;

	public Magnet(final SteerableBody steerable, final FlowField<Vector2> magnetFlowField) {
		this.steerable = steerable;
		magnetFieldSteeringOutput = new SteeringAcceleration<>(new Vector2());
		magneticField = new FollowFlowField<>(steerable);
		magneticField.setFlowField(magnetFlowField);
	}

	public void calculateSteering() {
		magneticField.calculateSteering(magnetFieldSteeringOutput);
	}

	public void applySteering(final float time) {
		// Update position and linear velocity. Velocity is trimmed to maximum speed
		steerable.setPosition(steerable.getPosition().mulAdd(steerable.getLinearVelocity(), time));
		steerable
		.setLinearVelocity(steerable.getLinearVelocity().mulAdd(magnetFieldSteeringOutput.linear, time).limit(steerable.getMaxLinearSpeed()));

		// Update orientation and angular velocity setOrientation(getOrientation() + (getAngularVelocity() * time));
		steerable.setAngularVelocity(steerable.getAngularVelocity() + (magnetFieldSteeringOutput.angular * time));
	}
}
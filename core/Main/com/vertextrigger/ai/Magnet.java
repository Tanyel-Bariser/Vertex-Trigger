package com.vertextrigger.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField;
import com.badlogic.gdx.math.Vector2;
import com.vertextrigger.entities.MagnetFlowField;

public class Magnet {
	private static final MagnetFlowField FLOW_FIELD = new MagnetFlowField(null, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 100);
	private final SteeringAcceleration<Vector2> magnetFieldSteeringOutput;
	private final FollowFlowField<Vector2> magneticField;
	private final SteerableBody steerable;

	public Magnet(final SteerableBody steerable) {
		this.steerable = steerable;
		magnetFieldSteeringOutput = new SteeringAcceleration<>(new Vector2());
		magneticField = new FollowFlowField<>(steerable);
		magneticField.setFlowField(FLOW_FIELD);
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
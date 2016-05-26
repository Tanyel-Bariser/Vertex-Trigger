package com.vertextrigger.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Snapshot of an {@code Entity} dimensional state at a given time
 */
public class InterpolatedPosition {
	private final Vector2 previousPosition = new Vector2();
	private final Vector2 newPosition = new Vector2();
	private float previousAngle = 0;
	private float newAngle = 0;

	public InterpolatedPosition(final Body body) {
		setState(body);
	}

	public void setState(final Body body) {
		previousPosition.x = body.getPosition().x;
		previousPosition.y = body.getPosition().y;
		previousAngle = body.getAngle();
	}

	private void interpolatePosition(final float alpha, final Body body) {
		newPosition.x = body.getPosition().x + ((body.getPosition().x - previousPosition.x) * alpha);
		newPosition.y = body.getPosition().y + ((body.getPosition().y - previousPosition.y) * alpha);
	}

	private void interpolateAngle(final float alpha, final Body body) {
		newAngle = (body.getAngle() * alpha) + (previousAngle * (1.0f - alpha));
	}

	public Vector2 getPreviousPosition() {
		return previousPosition;
	}

	public float getPreviousAngle() {
		return previousAngle;
	}

	public Vector2 getNewPosition(final float alpha, final Body body) {
		interpolatePosition(alpha, body);
		return newPosition;
	}

	public float getNewAngle(final float alpha, final Body body) {
		interpolateAngle(alpha, body);
		return newAngle;
	}
}
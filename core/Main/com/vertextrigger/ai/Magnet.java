package com.vertextrigger.ai;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Magnet {
	private final Body body;
	private final Sprite sprite;
	private final int strength;
	private final Vector2 flowField2dArrayPosition;

	public Magnet(final Body body, final Sprite sprite, final int strength, final Vector2 flowField2dArrayPosition) {
		this.body = body;
		this.sprite = sprite;
		this.strength = strength;
		this.flowField2dArrayPosition = flowField2dArrayPosition;
	}

	public Body getBody() {
		return body;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getStrength() {
		return strength;
	}

	public Vector2 getPosition() {
		return flowField2dArrayPosition;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Magnet [strength=").append(strength).append(", position=").append(flowField2dArrayPosition).append("]");
		return builder.toString();
	}
}
package com.vertextrigger.ai;

import com.badlogic.gdx.math.Vector2;

public class Magnet {
	private final int strength;
	private final Vector2 position;

	public Magnet(final int strength, final Vector2 position) {
		this.strength = strength;
		this.position = position;
	}

	public int getStrength() {
		return strength;
	}

	public Vector2 getPosition() {
		return position;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Magnet [strength=").append(strength).append(", position=").append(position).append("]");
		return builder.toString();
	}
}
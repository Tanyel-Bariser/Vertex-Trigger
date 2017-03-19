package com.vertextrigger.ai;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.Timer.Task;
import com.vertextrigger.collisiondetection.Collidable;
import com.vertextrigger.entities.MagnetFlowField;

public class Magnet implements Collidable {
	private final Body body;
	private final Sprite sprite;
	private final int strength;
	private final Vector2 flowField2dArrayPosition;
	private boolean active = true;
	private MagnetFlowField magnetFlowField;

	public Magnet(final Body body, final Sprite sprite, final int strength, final Vector2 flowField2dArrayPosition) {
		this.body = body;
		this.sprite = sprite;
		this.strength = strength;
		this.flowField2dArrayPosition = flowField2dArrayPosition;
		setUserData();
	}

	public void setMagnetFlowField(final MagnetFlowField magnetFlowField) {
		this.magnetFlowField = magnetFlowField;
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
		final java.lang.StringBuilder builder = new java.lang.StringBuilder();
		builder.append("Magnet [strength=").append(strength).append(", position=").append(flowField2dArrayPosition).append("]");
		return builder.toString();
	}

	@Override
	public void setUserData() {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}

	public boolean isActive() {
		return active;
	}

	private void changeState(final boolean active) {
		this.active = active;
		magnetFlowField.generateFlowField();
	}

	public synchronized void switchOff() {
		changeState(false);
		Timer.schedule(new Task() {
			@Override
			public void run() {
				changeState(true);
			}
		}, 3);
	}
}
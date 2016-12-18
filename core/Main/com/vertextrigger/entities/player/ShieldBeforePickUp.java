package com.vertextrigger.entities.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.collisiondetection.Collidable;

public class ShieldBeforePickUp implements Collidable {
	private final Body body;
	private final Sprite sprite;

	public ShieldBeforePickUp(final Body body, final Sprite sprite) {
		this.body = body;
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void setUserData() {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}
}
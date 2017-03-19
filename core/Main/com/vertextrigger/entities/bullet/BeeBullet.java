package com.vertextrigger.entities.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.ai.MagnetBehaviour;

public class BeeBullet extends EnemyBullet {
	private final MagnetBehaviour magnetBehaviour;

	public BeeBullet(final Body body, final Sprite sprite, final MagnetBehaviour magnetBehaviour) {
		super(body, sprite);
		this.magnetBehaviour = magnetBehaviour;
	}

	@Override
	public Sprite update(final float delta, final float alpha) {
		magnetBehaviour.calculateSteering();
		magnetBehaviour.applySteering(delta);
		return super.update(delta, alpha);
	}

	@Override
	public boolean isTooSlow() {
		return false; // TODO remove me after magnet tested
	}
}
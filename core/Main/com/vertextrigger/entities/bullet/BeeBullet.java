package com.vertextrigger.entities.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.ai.Magnet;

public class BeeBullet extends EnemyBullet {
	private final Magnet magnet;

	public BeeBullet(final Body body, final Sprite sprite, final Magnet magnet) {
		super(body, sprite);
		this.magnet = magnet;
	}

	@Override
	public Sprite update(final float delta, final float alpha) {
		magnet.calculateSteering();
		magnet.applySteering(delta);
		return super.update(delta, alpha);
	}

	@Override
	public boolean isTooSlow() {
		return false; // TODO remove me after magnet tested
	}
}
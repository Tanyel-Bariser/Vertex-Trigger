package com.vertextrigger.entities.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.ai.Magnet;

public class BeeBullet extends EnemyBullet {
	private final Array<Magnet> magnets;

	public BeeBullet(final Body body, final Sprite sprite, final Array<Magnet> magnets) {
		super(body, sprite);
		this.magnets = magnets;
	}

	@Override
	public Sprite update(final float delta, final float alpha) {
		for (final Magnet magnet : magnets) {
			magnet.calculateSteering();
			magnet.applySteering(delta);
		}
		return super.update(delta, alpha);
	}

	@Override
	public boolean isTooSlow() {
		return false; // TODO remove me after magnet tested
	}
}
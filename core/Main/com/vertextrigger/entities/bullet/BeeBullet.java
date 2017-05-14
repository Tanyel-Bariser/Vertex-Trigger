package com.vertextrigger.entities.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.Timer.Task;
import com.vertextrigger.ai.MagnetBehaviour;

public class BeeBullet extends EnemyBullet {
	private final MagnetBehaviour magnetBehaviour;
	private volatile boolean isTooSlow;

	public BeeBullet(final Body body, final Sprite sprite, final MagnetBehaviour magnetBehaviour) {
		super(body, sprite);
		this.magnetBehaviour = magnetBehaviour;
	}

	@Override
	public Sprite update(final float delta, final float alpha) {
		if (magnetBehaviour != null && isMagnetAttractionOn) {
			magnetBehaviour.calculateSteering();
			magnetBehaviour.applySteering(delta);
		}
		return super.update(delta, alpha);
	}

	@Override
	public boolean isTooSlow() {
		final Vector2 velocity = body.getLinearVelocity();
		if (velocity.isZero()) {
			startTooSlowTimer();
		}

		return isTooSlow;
	}

	private void startTooSlowTimer() {
		Timer.schedule(new Task() {
			@Override
			public void run() {
				isTooSlow = true;
			}
		}, 3f);
	}
}
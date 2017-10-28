package com.vertextrigger.entities.enemy;

import static com.vertextrigger.util.GameObjectSize.POKER_BODY_SIZE;

import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.*;

public class Poker extends AbstractEntity implements Enemy {
	public Poker(final Body body, final AnimationSet animationSet) {
		super(body, new AnimatorImpl(animationSet));
	}

	@Override
	public float getOffsetX() {
		return POKER_BODY_SIZE.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return POKER_BODY_SIZE.getOffsetY();
	}

	@Override
	public void setUserData() {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}

	@Override
	public void die() {
		AudioManager.playEnemyKilledSound();
		animator.playDeathAnimation(this);
	}
}
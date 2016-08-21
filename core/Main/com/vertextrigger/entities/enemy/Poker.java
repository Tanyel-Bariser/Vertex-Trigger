package com.vertextrigger.entities.enemy;

import static com.vertextrigger.util.GameObjectSize.POKER_BODY_SIZE;

import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.AnimationSet;

public class Poker extends AbstractEnemy {
	public Poker(final Body body, final AnimationSet animationSet) {
		super(body, animationSet);
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
	public void setUserData(final Body body) {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			if (fix.getUserData() instanceof PokerHead) {
				((PokerHead) fix.getUserData()).setPoker(this);
			} else {
				fix.setUserData(this);
			}
		}
	}
}
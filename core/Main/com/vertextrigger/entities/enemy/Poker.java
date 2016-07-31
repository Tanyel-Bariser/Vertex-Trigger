package com.vertextrigger.entities.enemy;

import static com.vertextrigger.util.GameObjectSize.POKER_BODY_SIZE;

import com.badlogic.gdx.physics.box2d.Body;
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
}
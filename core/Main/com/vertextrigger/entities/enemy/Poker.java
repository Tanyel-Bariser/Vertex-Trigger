package com.vertextrigger.entities.enemy;

import static com.vertextrigger.util.GameObjectSize.POKER_BODY_SIZE;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.AnimationSet;

public class Poker extends AbstractEnemy {
	public Poker(final Array<Vector2> coordinates, final Body body, final AnimationSet animationSet, final Steerable<Vector2> target) {
		super(coordinates, body, animationSet, target);
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
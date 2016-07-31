package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.entities.AnimationSet;

import static com.vertextrigger.util.GameObjectSize.BEE_SIZE;

public class Bee extends AbstractFlyingEnemy {
	public Bee(final Body body, final AnimationSet animationSet, final Steerable<Vector2> target) {
		super(body, animationSet, target);
	}

	@Override
	public float getOffsetX() {
		return BEE_SIZE.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return BEE_SIZE.getOffsetY();
	}
}

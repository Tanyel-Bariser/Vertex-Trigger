package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.AnimationSet;

public class Poker extends AbstractEnemy {
	
	public Poker(Array<Vector2> coordinates, Body body, AnimationSet anims) {
		super(coordinates, body, anims);
	}

	@Override
	protected void spriteAnimationSetup() {
	}

	@Override
	public Sprite update(float delta) {
		return animator.getUpdatedSprite(delta, body.getAngle(), body.getPosition());
	}
}
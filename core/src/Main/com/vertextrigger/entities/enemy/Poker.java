package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.util.GameObjectSize;

public class Poker extends AbstractEnemy {
	private boolean facingLeft;
	private boolean isDeathAnimationFinished;
	
	public Poker(Array<Vector2> coordinates, Body body, AnimationSet anims) {
		super(coordinates, body, anims);
		isDeathAnimationFinished = false;
	}

	@Override
	protected void spriteAnimationSetup() {
	}

	@Override
	public Sprite update(float delta) {
		super.update(delta);
		return animator.getUpdatedSprite(delta, body.getAngle(), body.getPosition());
	}

	@Override
	public Body getBody() {
		return body;
	}

	@Override
	public void setFacingLeft() {
		facingLeft = true;
	}

	@Override
	public void setFacingRight() {
		facingLeft = false;
	}

	@Override
	public float getOffsetX() {
		return GameObjectSize.getPokerSize().getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return GameObjectSize.getPokerSize().getOffsetY();
	}

	@Override
	protected void playDeathAnimation() {
		animator.playDeathAnimation(this);
	}

	@Override
	public void setDeathAnimationFinished() {
		isDeathAnimationFinished = true;
	}
	
	@Override
	public boolean isDeathAnimationFinished() {
		return isDeathAnimationFinished;
	}
}
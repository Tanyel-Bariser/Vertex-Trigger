package com.vertextrigger.entities.enemy;
import static com.vertextrigger.util.GameObjectSize.POKER_BODY_SIZE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.AnimationSet;

public class Poker extends AbstractEnemy {
	private boolean facingLeft;
	private boolean isDeathAnimationFinished;
	
	public Poker(Array<Vector2> coordinates, Body body, AnimationSet anims) {
		super(coordinates, body, anims);
		isDeathAnimationFinished = false;
		body.setUserData(this);
		for (Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
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
		return POKER_BODY_SIZE.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return POKER_BODY_SIZE.getOffsetY();
	}

	@Override
	public void die() {
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
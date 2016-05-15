package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.Timer.Task;

public class Animator {
	private Entity entity;
	private Body body;
	private Animation currentAnimation;
	private final AnimationSet animationSet;
	private boolean movingLeft;

	public Animator(final AnimationSet animationSet) {
		this.animationSet = animationSet;
		currentAnimation = animationSet.getStanding();
	}

	public void setEntity(final Entity entity) {
		this.entity = entity;
		body = entity.getBody();
	}

	public void setHorizontalMovement(final float horizontalMovement) {
		final float leftMovementThreshold = -0.3f;
		final float rightMovementThreshold = 0.3f;
		if (horizontalMovement < leftMovementThreshold) {
			movingLeft = true;
		} else if (horizontalMovement > rightMovementThreshold) {
			movingLeft = false;
		}
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	float currentAngle = 0;
	float frameTime;

	public Sprite getUpdatedSprite(final float delta, final float bodyAngle, final Vector2 newPosition) {
		frameTime += delta;
		final Sprite sprite = (Sprite) currentAnimation.getKeyFrame(frameTime);

		final float newRotation = getNewRotation(bodyAngle);
		sprite.rotate(newRotation);
		currentAngle = sprite.getRotation();
		sprite.setPosition(newPosition.x - (sprite.getWidth() / entity.getOffsetX()), newPosition.y - (sprite.getHeight() / entity.getOffsetY()));
		faceSpriteCorrectDirection(sprite);
		return sprite;
	}

	public void setAnimationType() {
		if (((Mortal) body.getUserData()).isDead()) {
			setAnimationDeath();
		} else if (body.getLinearVelocity().y > 0.05) {
			setAnimationRising();
		} else if (body.getLinearVelocity().y < -0.05) {
			setAnimationFalling();
		} else if ((body.getLinearVelocity().x > 0.05) || (body.getLinearVelocity().x < -0.05)) {
			setAnimationMoving();
		} else {
			setAnimationStanding();
		}
	}

	private void setAnimationStanding() {
		currentAnimation = animationSet.getStanding();
	}

	public void setAnimationShooting() {
		currentAnimation = animationSet.getShooting();
	}

	private void setAnimationMoving() {
		currentAnimation = animationSet.getMoving();
	}

	private void setAnimationFalling() {
		currentAnimation = animationSet.getFalling();
	}

	private void setAnimationRising() {
		currentAnimation = animationSet.getRising();
	}

	private float getNewRotation(float bodyAngle) {
		bodyAngle = bodyAngle * MathUtils.radiansToDegrees;
		return bodyAngle - currentAngle;
	}

	private void faceSpriteCorrectDirection(final Sprite sprite) {
		final boolean spriteFacingLeft = sprite.isFlipX();
		final boolean correctlyFacingLeft = movingLeft && spriteFacingLeft;
		final boolean correctlyFacingRight = !movingLeft && !spriteFacingLeft;
		final boolean alreadyFacingCorrectDirection = correctlyFacingLeft || correctlyFacingRight;
		if (!alreadyFacingCorrectDirection) {
			sprite.flip(true, false);
		}

		if (sprite.isFlipX()) {
			entity.setFacingLeft();
		} else {
			entity.setFacingRight();
		}
	}

	public void setAnimationDeath() {
		currentAnimation = animationSet.getDeath();
	}

	public void playDeathAnimation(final Mortal entity) {
		setAnimationDeath();
		Timer.schedule(new Task() {
			@Override
			public void run() {
				entity.setDeathAnimationFinished();
			}
		}, animationSet.getDeath().getAnimationDuration());
	}
}
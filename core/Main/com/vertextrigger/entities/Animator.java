package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Animator {
	private Entity entity;
	private Body body;
	private Animation currentAnimation;
	private AnimationSet animationSet;
	private boolean movingLeft;

	public Animator(AnimationSet animationSet) {
		this.animationSet = animationSet;
		currentAnimation = animationSet.getStanding();
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
		body = entity.getBody();
	}

	public void setHorizontalMovement(float horizontalMovement) {
		float leftMovementThreshold = -0.3f;
		float rightMovementThreshold = 0.3f;
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

	public Sprite getUpdatedSprite(float delta, float bodyAngle, Vector2 newPosition) {
		frameTime += delta;
		Sprite sprite = (Sprite) currentAnimation.getKeyFrame(frameTime);

		float newRotation = getNewRotation(bodyAngle);
		sprite.rotate(newRotation);
		currentAngle = sprite.getRotation();
		sprite.setPosition(
				newPosition.x - sprite.getWidth() / entity.getOffsetX(),
				newPosition.y - sprite.getHeight() / entity.getOffsetY());
		faceSpriteCorrectDirection(sprite);
		return sprite;
	}

	public void setAnimationType() {
		if ( ((Mortal)body.getUserData()).isDead() ) {
			setAnimationDeath();
		} else if (body.getLinearVelocity().y > 0.05) {
			setAnimationRising();
		} else if (body.getLinearVelocity().y < -0.05) {
			setAnimationFalling();
		} else if (body.getLinearVelocity().x > 0.05
				|| body.getLinearVelocity().x < -0.05) {
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

	private void faceSpriteCorrectDirection(Sprite sprite) {
		boolean spriteFacingLeft = sprite.isFlipX();
		boolean correctlyFacingLeft = movingLeft && spriteFacingLeft;
		boolean correctlyFacingRight = !movingLeft && !spriteFacingLeft;
		boolean alreadyFacingCorrectDirection = correctlyFacingLeft
				|| correctlyFacingRight;
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
		}, animationSet.getDeath().getAnimationDuration() / 2);
	}
}
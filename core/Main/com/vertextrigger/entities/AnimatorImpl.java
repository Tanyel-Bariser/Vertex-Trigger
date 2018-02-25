package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.Timer.Task;
import com.vertextrigger.entities.enemy.Mouse;

public class AnimatorImpl implements Animator {
	private Entity entity;
	private Body body;
	private Animation currentAnimation;
	private final AnimationSet animationSet;
	private boolean movingLeft;
	private float currentAngle = 0;
	private float frameTime;
	private boolean isAnimationTypeUpdateable = true;

	public AnimatorImpl(final AnimationSet animationSet) {
		this.animationSet = animationSet;
		currentAnimation = animationSet.getStanding();
	}

	@Override
	public void setEntity(final Entity entity) {
		this.entity = entity;
		body = entity.getBody();
	}

	@Override
	public void setHorizontalMovement(final float horizontalMovement) {
		final float leftMovementThreshold = -0.1f;
		final float rightMovementThreshold = 0.1f;
		if (horizontalMovement < leftMovementThreshold && entity.isVolitionallyMoving()) {
			movingLeft = true;
		} else if (horizontalMovement > rightMovementThreshold && entity.isVolitionallyMoving()) {
			movingLeft = false;
		}
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	@Override
	public Sprite getUpdatedSprite(final float delta, final Vector2 position, final float angle) {
		if (isAnimationTypeUpdateable) {
			setAnimationType();
		}
		frameTime += delta;
		final Sprite sprite = (Sprite) currentAnimation.getKeyFrame(frameTime);

		final float newRotation = getNewRotation(angle);
		sprite.rotate(newRotation);
		currentAngle = sprite.getRotation();
		sprite.setPosition(position.x - (sprite.getWidth() / entity.getOffsetX()), position.y - (sprite.getHeight() / entity.getOffsetY()));
		faceSpriteCorrectDirection(sprite);
		return sprite;
	}

	public void setAnimationType() {
		if (body.getUserData() instanceof Mortal && ((Mortal) body.getUserData()).isDead()) {
			setAnimation(animationSet.getDeath());
		} else if (body.getLinearVelocity().y > 0.01 && entity.isFeetOnGround() == false) {
			setAnimation(animationSet.getRising());
		} else if (body.getLinearVelocity().y < -0.01 && entity.isFeetOnGround() == false) {
			setAnimation(animationSet.getFalling());
		} else if ((body.getLinearVelocity().x > 0.05) && entity.isVolitionallyMoving() || (body.getLinearVelocity().x < -0.05)
				&& entity.isVolitionallyMoving()) {
			setAnimation(animationSet.getMoving());
		} else {
			setAnimation(animationSet.getStanding());
		}
	}

	private void resetFrameTime() {
		frameTime = 0;
	}

	private void setAnimation(final Animation animation) {
		if (currentAnimation != animation) {
			resetFrameTime();
			currentAnimation = animation;
		}
	}

	public void setAnimationShooting() {
		setAnimation(animationSet.getShooting());
	}

	private float getNewRotation(float bodyAngle) {
		bodyAngle = bodyAngle * MathUtils.radiansToDegrees;
		return bodyAngle - currentAngle;
	}

	private void faceSpriteCorrectDirection(final Sprite sprite) {
		// the instanceof hack is mental i know but without it mouse faces wrong direction
		final boolean spriteFacingLeft = entity instanceof Mouse ? !sprite.isFlipX() : sprite.isFlipX();
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

	@Override
	public void playShootAnimation(final boolean isGunFired) {
		if (isGunFired) {
			setAnimation(animationSet.getShooting());
			isAnimationTypeUpdateable = false;
			Timer.schedule(new Task() {
				@Override
				public void run() {
					isAnimationTypeUpdateable = true;
				}
			}, 0.1f);
		}
	}

	@Override
	public void playDeathAnimation(final Mortal entity) {
		setAnimation(animationSet.getDeath());
		Timer.schedule(new Task() {
			@Override
			public void run() {
				entity.setDeathAnimationFinished();
			}
		}, animationSet.getDeath().getAnimationDuration());
	}
}
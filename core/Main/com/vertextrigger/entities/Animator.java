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
	private float currentAngle = 0;
	private float frameTime;
	private boolean isAnimationTypeUpdateable = true;

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
		final String m = movingLeft ? "left" : "right";
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

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
		if (((Mortal) body.getUserData()).isDead()) {
			setAnimation(animationSet.getDeath());
		} else if (body.getLinearVelocity().y > 0.01) {
			setAnimation(animationSet.getRising());
		} else if (body.getLinearVelocity().y < -0.01) {
			setAnimation(animationSet.getFalling());
		} else if ((body.getLinearVelocity().x > 0.05) || (body.getLinearVelocity().x < -0.05)) {
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
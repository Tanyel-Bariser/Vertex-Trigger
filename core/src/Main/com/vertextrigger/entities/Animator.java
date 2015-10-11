package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Animator {
		static final float X_OFFSET = 1.9f;
		static final float Y_OFFSET = 2.2f;
		private Animation currentAnimation;
		private boolean movingLeft;
		private AnimationSet animationSet;
		private final Body body;
		
		public Animator(AnimationSet anims, Body body) {
			animationSet = anims;
			this.body = body;
			currentAnimation = anims.getStanding();		}
			
		void setHorizontalMovement(float horizontalMovement) {
			float leftMovementThreshold = -0.3f;
			float rightMovementThreshold = 0.3f;
			if (horizontalMovement < leftMovementThreshold) {
				movingLeft = true;
			} else if (horizontalMovement > rightMovementThreshold) {
				movingLeft = false;
			}
		}
		
		boolean isMovingLeft() {
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
			sprite.setPosition(newPosition.x - sprite.getWidth()/X_OFFSET, newPosition.y - sprite.getHeight()/Y_OFFSET);
			faceSpriteCorrectDirection(sprite);
			return sprite;
		}
		
		private void setAnimationType() {
			if (body.getLinearVelocity().y > 0.1) {
				setAnimationRising();
			} else if (body.getLinearVelocity().y < -0.1) {
				setAnimationFalling();			
			} else if (body.getLinearVelocity().x > 10 || body.getLinearVelocity().x < -10) {
				setAnimationMoving();
			} else {
				setAnimationStanding();
			}
		}
		
		private void setAnimationStanding() {
			currentAnimation = animationSet.getStanding();
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
			boolean alreadyFacingCorrectDirection = correctlyFacingLeft || correctlyFacingRight;
			if (!alreadyFacingCorrectDirection) {
				sprite.flip(true, false);
			}
			
			if (sprite.isFlipX()) {
				//Player.setFacingLeft();
				// TODO why is this static
				// TODO use polymorhism on entity to setfacingleft on any entity
			}
			else {
				//Player.setFacingRight();
			}
		}
	}

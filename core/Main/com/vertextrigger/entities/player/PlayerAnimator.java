package com.vertextrigger.entities.player;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.factory.animationfactory.PlayerAnimationFactory;

public class PlayerAnimator {
/*	static final float X_OFFSET = 1.9f;
	static final float Y_OFFSET = 2.2f;
	private final AnimationSet animationSet;
	private final Animation runAnimation;
	private final Animation standAnimation;
	private final Animation shootAnimation;
	private final Animation risingAnimation;
	private final Animation fallingAnimation;
	private final Animation deathAnimation;
	private Animation currentAnimation;
	private boolean movingLeft;
	
	public PlayerAnimator() {
		this(new PlayerAnimationFactory());
	}
	
	PlayerAnimator(PlayerAnimationFactory factory) {
		this.animationSet = factory.createAnimationSet();
		runAnimation = animationSet.getMoving();
		standAnimation = animationSet.getStanding();
		shootAnimation = animationSet.getShooting();
		risingAnimation = animationSet.getRising();
		fallingAnimation = animationSet.getFalling();
		deathAnimation = animationSet.getDeath();
		currentAnimation = runAnimation;
	}
		
	void setAnimationRunning() {
		currentAnimation = runAnimation;
	}
	void setAnimationStanding() {
		currentAnimation = standAnimation;
	}
	void setAnimationShooting() {
		currentAnimation = shootAnimation;
	}
	void setAnimationRising() {
		currentAnimation = risingAnimation;
	}
	void setAnimationFalling() {
		currentAnimation = fallingAnimation;
	}
	void setAnimationDying() {
		currentAnimation = deathAnimation;
	}
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
	Sprite getUpdatedSprite(float delta, float bodyAngle, Vector2 newPosition) {
		frameTime += delta;
		Sprite sprite = (Sprite) currentAnimation.getKeyFrame(frameTime);
		
		float newRotation = getNewRotation(bodyAngle);
		sprite.rotate(newRotation);
		currentAngle = sprite.getRotation();	
		sprite.setPosition(newPosition.x - sprite.getWidth()/X_OFFSET, newPosition.y - sprite.getHeight()/Y_OFFSET);
		faceSpriteCorrectDirection(sprite);
		return sprite;
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
			Player.setFacingLeft();
		}
		else {
			Player.setFacingRight();
		}
	}*/
}
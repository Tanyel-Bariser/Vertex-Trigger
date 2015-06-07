package com.vertextrigger.entities.player;

import com.badlogic.gdx.graphics.g2d.*;
import com.vertextrigger.factories.PlayerAnimationFactory;

class PlayerAnimator {
	private final Animation runAnimation;
	private final Animation standAnimation;
	private final Animation shootAnimation;
	private final Animation risingAnimation;
	private final Animation fallingAnimation;
	private final Animation deathAnimation;
	private Animation currentAnimation;
	private Sprite sprite;
	private boolean movingLeft;
	private float x, y;
	private float angle;
	
	PlayerAnimator() {
		this(new PlayerAnimationFactory());
	}
	
	PlayerAnimator(PlayerAnimationFactory factory) {
		runAnimation = factory.getRun();
		standAnimation = factory.getStanding();
		shootAnimation = factory.getShoot();
		risingAnimation = factory.getRise();
		fallingAnimation = factory.getFall();
		deathAnimation = factory.getDeath();
		currentAnimation = standAnimation;
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
	void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	void setAngle(float angle) {
		this.angle = angle;
	}
	
	Sprite getUpdatedSprite(float delta) {
		
		// Add delta to current animation key frame time
		sprite = (Sprite) currentAnimation.getKeyFrame(delta);
		faceSpriteCorrectDirection();
		
		// Set player's sprite position & angle to match the new position of player's physical body
		// Return player sprite after it's position/angle has been updated
		return sprite;
	}
	
	private void faceSpriteCorrectDirection() {
		boolean spriteFacingLeft = sprite.isFlipX();
		boolean correctlyFacingLeft = movingLeft && spriteFacingLeft;
		boolean correctlyFacingRight = !movingLeft && !spriteFacingLeft;
		boolean alreadyFacingCorrectDirection = correctlyFacingLeft || correctlyFacingRight;
		if (!alreadyFacingCorrectDirection) {
			sprite.flip(true, false);
		}
	}
}
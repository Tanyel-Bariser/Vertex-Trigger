package com.vertextrigger.entities.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.factories.AnimationFactory;

final class PlayerAnimator {
	private final Body player;
	private final Animation runAnimation;
	private final Animation deathAnimation;
	private Sprite sprite;
	private boolean movingLeft;
	
	PlayerAnimator(Body body) {
		this(body, new AnimationFactory());
	}

	PlayerAnimator(Body body, AnimationFactory factory) {
		player = body;
		runAnimation = factory.getPlayerRun();
		deathAnimation = factory.getPlayerDeath();
	}
	
	Sprite getUpdatedSprite(float delta) {
		// Add delta to current animation key frame time
		// If player is rising/jumping
				// Set player sprite based on jumping animation key frame
		// If player is falling
				// Set player sprite based on falling animation key frame
		// If player is running, user is pressing directional button AND player is in contact with floor
				// Set player sprite based on running animation key frame
		sprite = (Sprite) runAnimation.getKeyFrame(delta);
		faceSpriteCorrectDirection();
		
		// Set player's sprite position & angle to match the new position of player's physical body
		// Return player sprite after it's position/angle has been updated
		return sprite;
	}
	
	private void faceSpriteCorrectDirection() {
		setMovingLeft();
		boolean spriteFacingLeft = sprite.isFlipX();
		boolean correctlyFacingLeft = movingLeft && spriteFacingLeft;
		boolean correctlyFacingRight = !movingLeft && !spriteFacingLeft;
		boolean alreadyFacingCorrectDirection = correctlyFacingLeft || correctlyFacingRight;
		if (!alreadyFacingCorrectDirection) {
			sprite.flip(true, false);
		}
	}
	
	private void setMovingLeft() {
		float movement = player.getLinearVelocity().x;
		float leftMovementThreshold = -0.3f;
		float rightMovementThreshold = 0.3f;
		if (movement < leftMovementThreshold) {
			movingLeft = true;
		} else if (movement > rightMovementThreshold) {
			movingLeft = false;
		}
	}
}
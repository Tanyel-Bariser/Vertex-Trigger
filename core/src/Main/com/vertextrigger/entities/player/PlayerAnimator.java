package com.vertextrigger.entities.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.factories.AnimationFactory;

final class PlayerAnimator {
	private final Body player;
	private final Animation runAnimation;
	private final Animation deathAnimation;
	
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
		// If player is running, i.e. movement is not zero
				// Set player sprite based on running animation key frame
		
		// Flip player sprite so that if he's moving left, i.e. movement + xForce is negative,
		// the sprite is facing left and vice versa if he is moving right
		
		// Set player's sprite position & angle to match the new position of player's physical body
		// Return player sprite after it's position/angle has been updated
		return null;
	}
}
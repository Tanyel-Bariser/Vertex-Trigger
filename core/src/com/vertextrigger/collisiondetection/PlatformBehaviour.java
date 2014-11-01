package com.vertextrigger.collisiondetection;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * This class encapsulates the physical behaviour the player undergoes
 * while standing on particular platforms and also the behaviour of
 * the platforms themselves when they're stood on
 */
public class PlatformBehaviour {

	/**
	 * @param player object whose attributes are manipulated in
	 * 				 accordance with the platform he's standing on   
	 */
	public PlatformBehaviour(Body player) {
		// Set player's physical body
	}

	/**
	 * Causes player's movements to be consistent
	 * with moving on slippery surface
	 */
	public void onSlipperyPlatform() {
		// Set player's angle to that of the platform
		// Allow player the ability to jump
		// Cause player to slide & delay in stopping &
		// changing direction due to momentum
		// Push player in direction of, and magnitude of,
		// platform angle (if platform is slanted)
	}

	/**
	 * Falling platforms fall after one second if player stands on them 
	 * 
	 * @param platform that falls when stood on
	 */
	public void onFallingPlatform(Body platform) {
		// Set player's angle to that of the platform
		// Allow player the ability to jump
		// Wait for 1 second then cause platform position to gradually
		// change once per frame in the direction it is falling
	}

	/**
	 * Crumbling platforms break apart after 2 seconds if player stands on them
	 * 
	 * @param platform that crumbles when stood on
	 */
	public void onCrumblingPlatform(Body platform) {
		// Set player's angle to that of the platform
		// Allow player the ability to jump
		// Wait for 2 seconds then play platform crumbling
		// animation then remove platform from the level
	}

	/**
	 * Direction changing platforms only move when the player is on it.
	 * Each time the player newly lands on the platform it starts moving
	 * in a new direction from the direction it was moving previously.
	 * 
	 * @param platform that changes direction each time it's jumped on
	 */
	public void onDirectionChangingPlatform(Body platform) {
		// Set player's angle to that of the platform
		// Allow player the ability to jump
		// Change direction to another direction other than the direction
		// it was last moving in, either up, down, left or right, randomly
		// Start moving the platform again, now in the new direction
	}

	/**
	 * Sticky platforms cause the player to move more slowly and his feet
	 * to stick to the platform, regardless of gravity, even upside down
	 */
	public void onStickyPlatform() {
		// Set player's angle to that of the platform
		// Allow player the ability to jump
		// Slow down player's running speed
		// Remove effect of gravity on the player while in contact
		// Apply force to player in a direction towards platform to maintain contact
	}

	/**
	 * Conveyor belt platforms, can also be platforms near a magnet or fan,
	 * which can push or pull the player in a particular direction  
	 */
	public void onConveyorBeltPlatform() {
		// Set player's angle to that of the platform
		// Allow player the ability to jump
		// Apply additional force to player in direction platform dictates
	}
}
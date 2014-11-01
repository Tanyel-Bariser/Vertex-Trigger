package com.vertextrigger.entities;

import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Bullets are shot from the player's position horizontally.
 * Bullets are freed 5 seconds after being shot.
 */
public class Bullet implements Poolable {

	/**
	 * Initialises the physical properties of the bullet's physical body
	 */
	public Bullet() {
		// Initialise physical properties, i.e. circle shape, very bouncy, etc.
		// Set position out of camera's/user's view
		// Create physical body
		// Set identifier label as "Bullet"
		// Set bullet existence time to 5 seconds from being shot
	}
	
	/**
	 * Bullets are shot from the position of the player's gun
	 * towards the specified direction
	 * 
	 * @param xPostion of player's gun
	 * @param yPosition of player's gun
	 * @param shootLeft if true shoots bullet left, else shoots right
	 */
	public void shootBullet(float xPostion, float yPosition, boolean shootLeft) {
		// Set x & y coordinate position of bullet
		// If shooting left
				// Shoot bullet towards the left direction
		// If shooting right
				// Shoot bullet towards the right direction
	}
	
	/**
	 * Bullets exist for 5 seconds & the amount of time the bullet has
	 * left if updated each time this method is called.
	 * 
	 * @param delta the amount of time passed
	 * @return whether or not bullet should be freed
	 */
	public boolean bulletTimeRanOut(float delta) {
		// Reduce bullet existence time by the delta
		// If bullet time has run out, i.e. below zero
				// Return true so bullet can be freed from BulletPool
		// If bullet time has not run out, i.e. above zero
				// Return false so bullet stays active
		return false;
	}

	/**
	 * Resets a bullet for reuse after it is freed
	 * (5 seconds after being shot)
	 */
	@Override
	public void reset() {
		// Reset position of bullet to out of camera's/user's view
		// Reset bullet existence time back to 5 seconds
	}
}
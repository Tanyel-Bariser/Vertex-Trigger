package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Bullets are shot from the player's position horizontally.
 * Bullets are freed 5 seconds after being shot.
 */
public class Bullet implements Poolable, Entity {
	private Body body;

	/**
	 * Initialises the physical properties of the bullet's physical body
	 * Sets the bullet's existence time
	 * Creates the bullet's sprite
	 * 
	 * @param world the bullet will reside in
	 */
	public Bullet(World world) {
		// Initialise physical properties, i.e. circle shape, very bouncy, etc.
		// Set position out of camera's/user's view
		// Create physical body
		
		// Set as a bullet for continuous collision detection
		body.setBullet(true);
		
		
		// Set identifier label as "Bullet" (for fixture not body)
		// Set bullet sprite
		// Set bullet existence time to 5 seconds from being shot
	}
	
	/**
	 * Shoots bullet from player's gun
	 * 
	 * @param gunPointingLeft if true shoot left else shoots right
	 */
	void shoot(boolean gunPointingLeft) {
		// Set the vertical y-axis force to apply to the bullet to zero
		float impulseX, impulseY = 0;
		// If the player is facing/pointing gun towards the left direction
		if (gunPointingLeft) {
			// Set the horizontal x-axis force to apply to the bullet in the left direction
			impulseX = -500;
		// If the player is facing/pointing gun towards the right direction
		} else {
			// Set the horizontal x-axis force to apply to the bullet in the right direction
			impulseX = 500;
		}
		// Wake body if sleeping to continue simulation
		boolean wake = true;
		// Shoot the bullet
		body.applyLinearImpulse(new Vector2(impulseX, impulseY), body.getPosition(), wake);
	}
	
	/**
	 * Allows player to set the position of the bullet to that of his gun
	 * 
	 * @param position to set bullet
	 */
	void setPosition(Vector2 position) {
		// Set position of the bullet
		float angle = 0;
		body.setTransform(position, angle);
	}
	
	/**
	 * Updates the bullet sprite so that its position matches
	 * that of the bullet's physical body, therefore should
	 * be called once per frame for accurate rendering.
	 * 
	 * @param delta not used
	 * @return updated sprite of this bullet
	 */
	@Override
	public Sprite update(float delta) {
		// Get x & y coordinates of the bullets physical body
		// Get the angle of the bullets physical body
		// Set the position of the bullet sprite to match the bullet's
		// physical body position
		// Set the angle of the bullet sprite to match the bullet's
		// physical body angle
		// Return bullet's sprite after it's position/angle has been updated
		return null;
	}
	
	/**
	 * Bullets exist for 5 seconds & the amount of time the bullet has
	 * left if updated each time this method is called.
	 * 
	 * @param delta the amount of time passed
	 * @return whether or not bullet should be freed
	 */
	boolean bulletTimeRanOut(float delta) {
		// Reduce bullet existence time by the delta
		// If bullet time has run out, i.e. below or equal to zero seconds
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
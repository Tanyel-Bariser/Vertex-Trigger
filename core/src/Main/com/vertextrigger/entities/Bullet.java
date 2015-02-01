package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.vertextrigger.factories.bodyfactories.BulletBodyFactory;

/**
 * Bullets are shot from the player's position horizontally.
 * Bullets are freed 5 seconds after being shot.
 */
class Bullet implements Poolable, Entity {
	private final Body body;
	float existenceTime;
	private static final float SHOT_POWER = 500;
	static final float TOTAL_EXISTENCE_TIME = 5f;

	/**
	 * Creates bullet's physical body
	 * Creates the bullet's sprite
	 * 
	 * @param world the bullet will reside in
	 */
	Bullet(World world) {
		body = BulletBodyFactory.getBulletBody(world);
		// Set bullet sprite
	}
	
	Bullet(Body body) {
		this.body = body;
	}
	
	void shoot(boolean shootLeft) {
		float left = -SHOT_POWER;
		float right = SHOT_POWER;
		if (shootLeft) shoot(left);
		else shoot(right);
	}
	
	private void shoot(float horizontalShotDirection) {
		existenceTime = TOTAL_EXISTENCE_TIME;
		Vector2 directionOfShot = new Vector2(horizontalShotDirection, 0);
		boolean wakeToContinueSimulation = true;
		body.applyLinearImpulse(directionOfShot, body.getPosition(),
				wakeToContinueSimulation);
	}
	
	/**
	 * Allows player to set the position of the bullet to that of his gun
	 * 
	 * @param position to set bullet
	 */
	void setPosition(Vector2 position) {
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
	boolean isBulletExistenceTimeOut(float delta) {
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
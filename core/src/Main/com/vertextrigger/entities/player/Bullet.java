package com.vertextrigger.entities.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.factories.bodyfactories.BulletBodyFactory;

/**
 * Bullets are shot from the player's position horizontally.
 * Bullets are freed 5 seconds after being shot.
 */
class Bullet implements Poolable, Entity {
	static final float TOTAL_EXISTENCE_TIME = 5f;
	private static final float SHOT_POWER = 500;
	private final Body body;
	float existenceTime;

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
	
	float getXPosition() {
		return body.getPosition().x;
	}
	
	void shoot(boolean shootLeft) {
		float left = -SHOT_POWER;
		float right = SHOT_POWER;
		if (shootLeft) shoot(left);
		else shoot(right);
	}
	
	private void shoot(float horizontalVelocity) {
		// Initialise bullet existence time when bullet is first shot
		existenceTime = TOTAL_EXISTENCE_TIME;
		Vector2 velocity = new Vector2(horizontalVelocity, 0);
		boolean wakeForSimulation = true;
		body.applyLinearImpulse(velocity, body.getPosition(),
				wakeForSimulation);
	}
	
	/**
	 * Allows player to set the position of the bullet to that of his gun
	 * 
	 * @param position to set bullet
	 */
	void setPosition(Vector2 position) {
		body.setTransform(position, 0);
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
		updateRemainingExistenceTime(delta);
		// Get x & y coordinates of the bullets physical body
		// Get the angle of the bullets physical body
		// Set the position of the bullet sprite to match the bullet's
		// physical body position
		// Set the angle of the bullet sprite to match the bullet's
		// physical body angle
		// Return bullet's sprite after it's position/angle has been updated
		return null;
	}	

	private void updateRemainingExistenceTime(float delta) {
		existenceTime -= delta;
	}
	
	/**
	 * @return whether or not bullet should be freed
	 */
	boolean isExistenceTimeExpired() {
		return existenceTime < 0;
	}

	/**
	 * Invoked automatically by libGDX when BulletPool.free(Bullet) is called
	 */
	@Override
	public void reset() {
		body.setTransform(BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW, 0);
		existenceTime = TOTAL_EXISTENCE_TIME;
	}
}
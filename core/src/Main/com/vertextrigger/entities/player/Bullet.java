package com.vertextrigger.entities.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.vertextrigger.entities.Entity;

/**
 * Bullets are shot from the player's position horizontally.
 * Bullets are freed 5 seconds after being shot.
 */
class Bullet implements Poolable, Entity {
	static final float TOTAL_EXISTENCE_TIME = 5f;
	static final float SHOT_POWER = 500;
	private final Body body;
	private float existenceTime = -1;
	private final Sprite sprite;
	
	/**
	 * Creates bullet's physical body
	 * Creates the bullet's sprite
	 * 
	 * @param world the bullet will reside in
	 */
	Bullet(World world, Sprite sprite) {
		body = BulletBodyFactory.getBulletBody(world);
		this.sprite = sprite;
	}
	
	Bullet(Body body, Sprite sprite) {
		this.body = body;
		this.sprite = sprite;
	}
	
	float getRemainingTime() {
		return existenceTime;
	}
	
	Vector2 getPosition() {
		return body.getPosition();
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
		Vector2 currentPosition = getPosition();
		float currentAngle = body.getAngle() * MathUtils.radiansToDegrees;
		sprite.setPosition(currentPosition.x, currentPosition.y);
		sprite.setRotation(currentAngle);
		return sprite;
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
	 * Invoked automatically when BulletPool.free(Bullet) is called
	 */
	@Override
	public void reset() {
		body.setTransform(BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW, 0);
		existenceTime = TOTAL_EXISTENCE_TIME;
	}
}
package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.Coordinate;

/**
 * Platform that does something after a certain amount of time,
 * such as disappears/reappears, counts down then explodes
 * or spikes go in & out of the platform, etc.
 */
public abstract class TimedPlatform implements Entity {

	/**
	 * Initialises the physical properties of the platform's physical body
	 * Sets platforms's sprite & animation
	 * Sets time for timed platform
	 * 
	 * @param world the moving platform will reside in
	 * @param sprite platform's image
	 * @param time before the platform's action is required
	 */
	public TimedPlatform(World world, Sprite sprite, float time) {
		// Set sprite & animation for platform
		// Set time for timed platform
		// Create physical platform body
	}
	
	/**
	 * Create timed platform's physical body & physical properties.
	 * 
	 * @param world the platform will reside in
	 * @param position of platform in game world
	 * @return physical body of platform 
	 */
	protected abstract Body createPlatformBody(World world, Coordinate position);
	
	/**
	 * Create & set sprite & animation for moving platform
	 */
	protected abstract void spriteSetup();
	
	/**
	 * Once enough time has passed, the timed platform performs
	 * it's specific action & chooses appropriate platform
	 * sprite based on animation.
	 * 
	 * @param delta time passed between previous & current frame
	 * @return updated platform's sprite
	 */
	@Override
	public abstract Sprite update(float delta);
}
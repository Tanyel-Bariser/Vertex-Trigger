package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
	
/**
 * Platform that does something after a certain amount of time, such as
 * disappears/reappears, counts down then explodes, spikes go in & out
 * of the platform or platform rotates every 4 seconds etc.
 */
public abstract class TimedPlatform implements Entity {
	protected float time;
	protected Sprite sprite;
	protected Animation animation;

	/**
	 * Initialises the physical properties of the platform's physical body
	 * Sets platforms's sprite & animation
	 * 
	 * @param world the timed platform will reside in
	 * @param time before the platform's action is required
	 */
	public TimedPlatform(World world, Vector2 position) {
		// Set sprite & animation for platform
		setupSpriteAnimation();
		// Create physical platform body
	}
	

	/**
	 * Create & set sprite & animation for moving platform
	 */
	protected abstract void setupSpriteAnimation();
	
	/**
	 * Create timed platform's physical body & physical properties.
	 * 
	 * @param world the platform will reside in
	 * @param position of platform in game world
	 * @return physical body of platform 
	 */
	protected abstract Body createPlatformBody(World world, Vector2 position);
	
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
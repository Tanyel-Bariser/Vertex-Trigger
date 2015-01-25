package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.util.Coordinate;

/**
 * Platform rotates 90 degrees once every 4 seconds
 */
public class RotatingPlatform extends TimedPlatform {
	private final float TIME_BETWEEN_ROTATION = 2f;

	public RotatingPlatform(World world, Coordinate position) {
		super(world, position);
		// Set the amount of time to pass between each time the platform rotates
		time = TIME_BETWEEN_ROTATION;
	}

	/**
	 * 
	 */
	@Override
	protected void setupSpriteAnimation() {
	}

	/**
	 * 
	 */
	@Override
	protected Body createPlatformBody(World world, Coordinate position) {
		return null;
	}

	/**
	 * 
	 */
	@Override
	public Sprite update(float delta) {
		return null;
	}
}
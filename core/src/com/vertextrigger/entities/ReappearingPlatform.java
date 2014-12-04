package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.Coordinate;

public class ReappearingPlatform extends TimedPlatform {
	private final float SECONDS_BETWEEN_DISAPPEAR_REAPPEAR = 3f;

	public ReappearingPlatform(World world, Coordinate position) {
		super(world, position);
		// Set the amount of time to pass between
		// the platform disappearing/reappearing
		time = SECONDS_BETWEEN_DISAPPEAR_REAPPEAR;
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
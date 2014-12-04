package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.Coordinate;

public class ExplodingPlatform extends TimedPlatform {
	private final float SECONDS_BEFORE_EXPLODE = 4f;
	
	/**
	 * 
	 * @param world
	 * @param position
	 */
	public ExplodingPlatform(World world, Coordinate position) {
		super(world, position);
		// Set the amount of time to pass between platform explosions
		time = SECONDS_BEFORE_EXPLODE;
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
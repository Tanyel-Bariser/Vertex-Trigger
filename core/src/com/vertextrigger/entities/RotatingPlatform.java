package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.Coordinate;

/**
 * Platform rotates 90 degrees once every 4 seconds
 */
public class RotatingPlatform extends TimedPlatform {

	public RotatingPlatform(World world, Sprite sprite, Coordinate position,
			float time) {
		super(world, sprite, position, time);
	}

	@Override
	protected Body createPlatformBody(World world, Coordinate position) {
		return null;
	}

	@Override
	protected void spriteSetup() {
		
	}

	@Override
	public Sprite update(float delta) {
		return null;
	}
}
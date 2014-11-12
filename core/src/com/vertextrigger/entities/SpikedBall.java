package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinate;

/**
 * 
 */
public class SpikedBall extends DangerousBall {

	/**
	 * 
	 * @param world
	 * @param sprite
	 * @param coordinates
	 */
	public SpikedBall(World world, Sprite sprite, Array<Coordinate> coordinates, float radius) {
		super(world, sprite, coordinates, radius);
	}

	/**
	 * 
	 */
	@Override
	protected void spriteSetup() {
	}
}
package com.vertextrigger.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.util.Coordinate;

/**
 * 
 */
public class SpikedBall extends DangerousBall {

	/**
	 * 
	 * @param world
	 * @param coordinates
	 */
	public SpikedBall(World world, Array<Coordinate> coordinates) {
		super(world, coordinates);
	}

	/**
	 * 
	 */
	@Override
	protected void spriteSetup() {
	}

	/**
	 * 
	 */
	@Override
	protected void createBody(World world, Coordinate coordinates) {
		// Set radius of spiked ball
		// Set the speed the spiked ball moves at
	}
}
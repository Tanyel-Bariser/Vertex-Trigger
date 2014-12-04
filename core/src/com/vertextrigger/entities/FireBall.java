package com.vertextrigger.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinate;

/**
 * 
 */
public class FireBall extends DangerousBall {

	/**
	 * 
	 * @param world
	 * @param coordinates
	 */
	public FireBall(World world, Array<Coordinate> coordinates) {
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
	protected void createBody(World world, Coordinate coordinate) {
		// Set radius of fire ball
		// Set the speed the fire ball moves at
	}
}
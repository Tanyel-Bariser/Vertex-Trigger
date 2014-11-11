package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
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
	 * @param sprite
	 * @param coordinates
	 */
	public FireBall(World world, Sprite sprite, Array<Coordinate> coordinates) {
		super(world, sprite, coordinates);
	}

	/**
	 * 
	 */
	@Override
	protected Body createDangerousBall(World world, Coordinate position) {
		return null;
	}

	/**
	 * 
	 */
	@Override
	protected void spriteSetup() {
		
	}
}

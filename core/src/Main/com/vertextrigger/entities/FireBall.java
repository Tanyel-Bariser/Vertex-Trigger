package com.vertextrigger.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * 
 */
public class FireBall extends DangerousBall {

	/**
	 * 
	 * @param world
	 * @param coordinates
	 */
	public FireBall(World world, Array<Vector2> coordinates) {
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
	protected void createBody(World world, Vector2 coordinate) {
		// Set radius of fire ball
		// Set the speed the fire ball moves at
	}

	@Override
	public Body getBody() {
		return null;
	}

	@Override
	public void setFacingLeft() {
	}

	@Override
	public void setFacingRight() {
	}

	@Override
	public float getOffsetX() {
		return 0;
	}

	@Override
	public float getOffsetY() {
		return 0;
	}
}
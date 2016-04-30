package com.vertextrigger.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 *
 */
public class SpikedBall extends DangerousBall {

	/**
	 *
	 * @param world
	 * @param coordinates
	 */
	public SpikedBall(final World world, final Array<Vector2> coordinates) {
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
	protected void createBody(final World world, final Vector2 coordinates) {
		// Set radius of spiked ball
		// Set the speed the spiked ball moves at
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
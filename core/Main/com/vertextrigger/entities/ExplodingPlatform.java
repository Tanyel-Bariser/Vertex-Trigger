package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class ExplodingPlatform extends TimedPlatform {
	private final float SECONDS_BEFORE_EXPLODE = 4f;
	
	/**
	 * 
	 * @param world
	 * @param position
	 */
	public ExplodingPlatform(World world, Vector2 position) {
		super(world, position);
		// Set the amount of time to pass between platform explosions
		time = SECONDS_BEFORE_EXPLODE;
		setUserData(null);
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
	protected Body createPlatformBody(World world, Vector2 position) {
		return null;
	}

	/**
	 * 
	 */
	@Override
	public Sprite update(float delta) {
		return null;
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

	@Override
	public void setUserData(Body body) {
		throw new IllegalStateException("Unimplemented");
	}
}
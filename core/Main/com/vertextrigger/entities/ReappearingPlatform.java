package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class ReappearingPlatform extends TimedPlatform {
	private final float SECONDS_BETWEEN_DISAPPEAR_REAPPEAR = 3f;

	public ReappearingPlatform(World world, Vector2 position) {
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
}
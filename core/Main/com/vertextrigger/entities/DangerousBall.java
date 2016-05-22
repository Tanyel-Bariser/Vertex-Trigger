package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * DangerousBall is an game object that will kill the player if touched and follows a predefined path, such as, traversing the edges of a platform
 */
public abstract class DangerousBall implements Entity {
	// Predefined path for dangerous ball's physical body to follow
	protected Path path;
	protected Body body;
	protected Sprite sprite;
	protected float speed;
	protected Vector2 newPositionFromPortal;
	private final InterpolatedPosition ballState;

	/**
	 * Initialises the physical properties of the Dangerous Ball's physical body Sets Dangerous Ball's sprite, could be fire ball or spiked ball, etc.
	 * Sets the path of the dangerous ball to traverse
	 *
	 * @param world
	 *            the dangerous ball will reside in
	 * @param coordinates
	 *            is the path, series of x & y coordinates, the dangerous ball follows
	 */
	public DangerousBall(final World world, final Array<Vector2> coordinates) {
		// Set sprite for dangerous ball
		spriteSetup();
		// Create physical body at initial position based on first coordinate
		createBody(world, coordinates.first());
		// Set the coordinates of the predefined path
		// for the dangerous ball to follow in a loop
		path = new Path(body, coordinates);
		setUserData(body);
		ballState = new InterpolatedPosition(body);
	}

	/**
	 * Defers sprite setup to subclasses
	 */
	protected abstract void spriteSetup();

	/**
	 * Defers creation of body & physical properties to subclasses
	 */
	protected abstract void createBody(final World world, final Vector2 coordinates);

	/**
	 * Moves the dangerous ball further along its predefined path with the distance moved dependent on the delta. Delta is needed for frame rate
	 * independent movement. Returns sprite after it's position has been updated.
	 *
	 * @param delta
	 *            time passed between previous & current frame
	 * @return updated sprite of this dangerous ball
	 */
	@Override
	public Sprite update(final float delta, final float alpha) {
		// Move dangerous ball along it's predefined path based on delta
		// Set dangerous ball's sprite position & angle to match
		// the new position of dangerous ball's physical body
		// Return sprite after it's position/angle has been updated
		return null;
	}

	@Override
	public void setUserData(final Body body) {
		throw new IllegalStateException("Unimplemented");
	}

	@Override
	public void setNewPositionFromPortal(final Vector2 newPositionFromPortal) {
		this.newPositionFromPortal = newPositionFromPortal;
	}

	@Override
	public void cachePosition() {
		ballState.setState(body);
	}
}
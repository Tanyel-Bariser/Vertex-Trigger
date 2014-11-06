package com.vertextrigger;

/**
 * Container for 2D x-axis & y-axis coordinates used
 * for representing a position in the game world
 */
public class Coordinate {
	public final float x;
	public final float y;

	/**
	 * Sets an x-axis & y-axis coordinate to represent
	 * a position in the game world.
	 * 
	 * @param x position on x-axis of game world
	 * @param y position on y-axis of game world
	 */
	public Coordinate(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
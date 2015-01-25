package com.vertextrigger.util;

/**
 * Container for 2D x-axis & y-axis coordinates used
 * for representing a position in the game world
 */
public class Coordinate {
	public final float x; // x-axis coordinate
	public final float y; // y-axis coordinate

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
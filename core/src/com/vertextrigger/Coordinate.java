package com.vertextrigger;

/**
 * Data Structure containing x & y coordinates used
 * for representing a position in the game world
 */
public class Coordinate {
	public final float x;
	public final float y;

	/**
	 * Sets the coordinates for game world positions
	 * 
	 * @param x coordinate on x-axis of game world
	 * @param y coordinate on y-axis of game world
	 */
	public Coordinate(float x, float y) {
		this.x = x;
		this.y = y;
	}
}

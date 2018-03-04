package com.vertextrigger.util;

import com.badlogic.gdx.math.Vector2;

public class PositionUtil {

	public static Vector2 convertPosition(final int containerWidth, final int containerHeight, final Vector2 worldPosition) {
		return new Vector2(worldPosition).add(containerWidth, containerHeight);
	}

	public static boolean closeEnough(final float a, final float b) {
		return Math.abs(a - b) < 0.02;
	}
}

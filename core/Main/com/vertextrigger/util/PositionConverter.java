package com.vertextrigger.util;

import com.badlogic.gdx.math.Vector2;

public class PositionConverter {

	public static Vector2 convertPosition(final int containerWidth, final int containerHeight, final Vector2 worldPosition) {
		if (worldPosition.x > containerWidth || worldPosition.x < -containerWidth || worldPosition.y > containerHeight
				|| worldPosition.y < -containerHeight) {
			throw new IllegalArgumentException("Magnet cannot be placed outside of level container");
		}

		return new Vector2(worldPosition).add(containerWidth, containerHeight);
	}
}
package com.vertextrigger.entities.movingplatform;

import com.badlogic.gdx.math.Vector2;
import com.vertextrigger.util.GameObjectSize;

public abstract class Path {

	static final float DEFAULT_STEP_SIZE = 5 * GameObjectSize.OBJECT_SIZE;

	final Vector2 pathStart;
	final Vector2 pathEnd;
	final float stepSize;

	Path(final Vector2 pathStart, final Vector2 pathEnd, final float stepSize) {
		this.pathStart = pathStart;
		this.pathEnd = pathEnd;
		this.stepSize = stepSize;
	}

	abstract public Vector2 getStep(final Vector2 bodyPosition);
}
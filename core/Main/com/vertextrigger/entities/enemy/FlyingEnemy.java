package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.Path;

public class FlyingEnemy extends AbstractEnemy {
	public FlyingEnemy(Array<Vector2> coordinates, Body body, AnimationSet animationSet) {
		super(coordinates, body, animationSet);
	}
	
	public FlyingEnemy(Array<Vector2> coordinates, Body body, AnimationSet animationSet, Path path) {
		this(coordinates, body, animationSet);
		this.path = path;
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
package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.Enemy;

public class MadPoker extends Enemy {

	public MadPoker(Array<Vector2> coordinates, Body body, Sprite sprite) {
		super(coordinates, body, sprite);
	}

	@Override
	protected void spriteAnimationSetup() {
	}

	@Override
	protected Sprite updateSprite(float delta) {
		return null;
	}

}

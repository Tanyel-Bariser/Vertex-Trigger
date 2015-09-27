package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

public class Poker extends AbstractEnemy {

	public Poker(Array<Vector2> coordinates, Body body, Sprite sprite) {
		super(coordinates, body, sprite);
	}

	@Override
	protected void spriteAnimationSetup() {
	}

	@Override
	public Sprite update(float delta) {
		return updateSprite(delta);
	}
	
	@Override
	protected Sprite updateSprite(float delta) {
		Vector2 newBodyPosition = body.getPosition();
		sprite.setPosition(newBodyPosition.x, newBodyPosition.y);
		return sprite;
	}

}

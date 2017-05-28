package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.inanimate.StaticPlatform;

public class Pit extends StaticPlatform implements Enemy {

	public Pit(final Sprite sprite, final Body body) {
		super(sprite, body);
	}

	@Override
	public void setDead() {
	}
}
package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public interface Animator {

	void playShootAnimation(final boolean shoot);

	void setHorizontalMovement(final float x);

	Sprite getUpdatedSprite(final float delta, final Vector2 newPosition, final float newAngle);

	void playDeathAnimation(final Mortal mortal);

	void setEntity(final Entity entity);

}
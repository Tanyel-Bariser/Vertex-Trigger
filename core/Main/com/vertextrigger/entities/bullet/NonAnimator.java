package com.vertextrigger.entities.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.vertextrigger.entities.*;

public class NonAnimator implements Animator {
	private Entity entity;
	private final Sprite sprite;

	public NonAnimator(final Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public void setEntity(final Entity entity) {
		this.entity = entity;
	}

	@Override
	public void playShootAnimation(final boolean shoot) {
	}

	@Override
	public void setHorizontalMovement(final float x) {
	}

	@Override
	public Sprite getUpdatedSprite(final float delta, final Vector2 newPosition, final float newAngle) {
		sprite.setPosition((newPosition.x - entity.getOffsetX()), (newPosition.y - entity.getOffsetY()));
		return sprite;
	}

	@Override
	public void playDeathAnimation(final Mortal mortal) {
	}
}
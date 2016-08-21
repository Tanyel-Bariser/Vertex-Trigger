package com.vertextrigger.entities.bullet;

import static com.vertextrigger.util.GameObjectSize.BEE_BULLET_SIZE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class EnemyBullet extends Bullet {

	public EnemyBullet(final Body body, final Sprite sprite) {
		super(body, sprite);
	}

	@Override
	public float getOffsetX() {
		return BEE_BULLET_SIZE.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return BEE_BULLET_SIZE.getOffsetY();
	}
}
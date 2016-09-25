package com.vertextrigger.entities.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.entities.AbstractEntity;

/**
 * Bullets are shot from the player's position horizontally. Bullets are freed 5 seconds after being shot.
 */
public abstract class Bullet extends AbstractEntity {
	protected int numberOfCollisions;
	private final Sprite sprite;
	private boolean destroyBullet = false;
	private int collisions;

	public Bullet(final Body body, final Sprite sprite) {
		super(body, new NonAnimator(sprite));
		this.sprite = sprite;
	}

	public void shoot(final Vector2 velocity) {
		body.applyLinearImpulse(velocity, body.getPosition(), true);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setPosition(final Vector2 position) {
		body.setTransform(position, 0);
	}

	public void setDestroyBullet() {
		destroyBullet = true;
	}

	public boolean destroyBullet() {
		return destroyBullet;
	}

	private void checkCollisions() {
		if (collisions > numberOfCollisions) {
			destroyBullet = true;
		}
	}

	/**
	 * Updates the bullet sprite so that its position matches that of the bullet's physical body, therefore should be called once per frame for
	 * accurate rendering.
	 *
	 * @param delta
	 *            not used
	 * @return updated sprite of this bullet
	 */
	@Override
	public Sprite update(final float delta, final float alpha) {
		checkCollisions();
		return super.update(delta, alpha);
	}

	public boolean isTooSlow() {
		final Vector2 velocity = body.getLinearVelocity();
		final float speedThreshold = 2f;
		return (velocity.x < speedThreshold) && (velocity.x > -speedThreshold) && (velocity.y < speedThreshold) && (velocity.y > -speedThreshold);
	}

	public void incrementCollisions() {
		collisions++;
	}
}
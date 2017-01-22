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
	private boolean isBulletDestroyed = false;
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
		body.setTransform(position, body.getAngle());
	}

	public void destroyBullet() {
		isBulletDestroyed = true;
	}

	public boolean isBulletDestroyed() {
		return isBulletDestroyed;
	}

	private void checkCollisions() {
		if (collisions > numberOfCollisions) {
			isBulletDestroyed = true;
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
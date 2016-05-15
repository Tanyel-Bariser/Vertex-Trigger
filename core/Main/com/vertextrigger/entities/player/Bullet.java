package com.vertextrigger.entities.player;

import static com.vertextrigger.util.GameObjectSize.BULLET_SIZE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.Entity;

/**
 * Bullets are shot from the player's position horizontally. Bullets are freed 5 seconds after being shot.
 */
public class Bullet implements Entity {
	static final float SHOT_POWER = 0.0015f;
	private final Body body;
	private final Sprite sprite;
	private boolean destroyBullet = false;
	private Vector2 newPositionFromPortal;
	private int collisions;

	public Bullet(final Body body, final Sprite sprite) {
		this.body = body;
		this.sprite = sprite;
		setUserData(body);
	}

	Vector2 getPosition() {
		return body.getPosition();
	}

	void shoot(final boolean shootLeft) {
		final float left = -SHOT_POWER;
		final float right = SHOT_POWER;
		if (shootLeft) {
			shoot(left);
		} else {
			shoot(right);
		}
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setHitPlayer() {
		destroyBullet = true;
	}

	public boolean hitPlayer() {
		return destroyBullet;
	}

	private void checkCollisions() {
		if (collisions > 4) {
			destroyBullet = true;
		}
	}

	private void shoot(final float horizontalVelocity) {
		final Vector2 velocity = new Vector2(horizontalVelocity, 0);
		final boolean wakeForSimulation = true;
		body.applyLinearImpulse(velocity, body.getPosition(), wakeForSimulation);
	}

	/**
	 * Allows player to set the position of the bullet to that of his gun
	 *
	 * @param position
	 *            to set bullet
	 */
	void setPosition(final Vector2 position) {
		body.setTransform(position, 0);
	}

	@Override
	public void setNewPositionFromPortal(final Vector2 newPortalPosition) {
		newPositionFromPortal = newPortalPosition;
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
	public Sprite update(final float delta) {
		if (newPositionFromPortal != null) {
			body.setTransform(newPositionFromPortal, 0);
			setNewPositionFromPortal(null);
		}
		checkCollisions();
		sprite.setPosition((body.getPosition().x - getOffsetX()) + 0.22f, (body.getPosition().y - getOffsetY()) + 0.17f);
		return sprite;
	}

	public static void destroyBullet(final Fixture fix) {
		fix.getBody().setTransform(50, 50, 0);
	}

	@Override
	public Body getBody() {
		return body;
	}

	@Override
	public void setFacingLeft() {
	}

	@Override
	public void setFacingRight() {
	}

	@Override
	public float getOffsetX() {
		return BULLET_SIZE.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return BULLET_SIZE.getOffsetY();
	}

	@Override
	public void setUserData(final Body body) {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
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
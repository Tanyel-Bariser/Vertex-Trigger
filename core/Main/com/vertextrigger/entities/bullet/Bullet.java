package com.vertextrigger.entities.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.*;

/**
 * Bullets are shot from the player's position horizontally. Bullets are freed 5 seconds after being shot.
 */
public abstract class Bullet implements Entity {
	protected final Body body;
	private final Sprite sprite;
	private boolean destroyBullet = false;
	private Vector2 newPositionFromPortal;
	private int collisions;
	private final InterpolatedPosition bulletState;
	private boolean isTeleportable = true;

	public Bullet(final Body body, final Sprite sprite) {
		this.body = body;
		this.sprite = sprite;
		setUserData(body);
		bulletState = new InterpolatedPosition(this.body);
	}

	public void shoot(final Vector2 velocity) {
		body.applyLinearImpulse(velocity, body.getPosition(), true);
	}

	Vector2 getPosition() {
		return body.getPosition();
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

	/**
	 * Allows player to set the position of the bullet to that of his gun
	 *
	 * @param position
	 *            to set bullet
	 */
	public void setPosition(final Vector2 position) {
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
	public Sprite update(final float delta, final float alpha) {
		if (newPositionFromPortal != null) {
			body.setTransform(newPositionFromPortal, 0);
			cachePosition();
			setNewPositionFromPortal(null);
		}
		checkCollisions();
		final Vector2 newPosition = bulletState.getNewPosition(alpha, body);
		sprite.setPosition((newPosition.x - getOffsetX()) + 0.22f, (newPosition.y - getOffsetY()) + 0.17f);
		return sprite;
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

	@Override
	public void cachePosition() {
		bulletState.setState(body);
	}

	@Override
	public boolean isTeleportable() {
		return isTeleportable;
	}

	@Override
	public void exitedPortal() {
		isTeleportable ^= true;
	}
}
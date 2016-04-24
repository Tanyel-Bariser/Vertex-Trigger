package com.vertextrigger.entities.player;

import static com.vertextrigger.util.GameObjectSize.BULLET_SIZE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.vertextrigger.entities.Entity;

/**
 * Bullets are shot from the player's position horizontally. Bullets are freed 5
 * seconds after being shot.
 */
public class Bullet implements Entity {
	static final float SHOT_POWER = 0.001f;
	private final Body body;
	private final Sprite sprite;
	private boolean hitPlayer = false;
	private Vector2 newPositionFromPortal;

	public Bullet(Body body, Sprite sprite) {
		this.body = body;
		this.sprite = sprite;
		setUserData(body);
	}

	Vector2 getPosition() {
		return body.getPosition();
	}

	void shoot(boolean shootLeft) {
		float left = -SHOT_POWER;
		float right = SHOT_POWER;
		if (shootLeft)
			shoot(left);
		else
			shoot(right);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setHitPlayer() {
		hitPlayer = true;
	}

	public boolean hitPlayer() {
		return hitPlayer;
	}

	private void shoot(float horizontalVelocity) {
		Vector2 velocity = new Vector2(horizontalVelocity, 0);
		boolean wakeForSimulation = true;
		body.applyLinearImpulse(velocity, body.getPosition(), wakeForSimulation);
	}

	/**
	 * Allows player to set the position of the bullet to that of his gun
	 * 
	 * @param position
	 *            to set bullet
	 */
	void setPosition(Vector2 position) {
		body.setTransform(position, 0);
	}
	
	@Override
	public void setNewPositionFromPortal(Vector2 newPortalPosition) {
		this.newPositionFromPortal = newPortalPosition;
	}

	/**
	 * Updates the bullet sprite so that its position matches that of the
	 * bullet's physical body, therefore should be called once per frame for
	 * accurate rendering.
	 * 
	 * @param delta
	 *            not used
	 * @return updated sprite of this bullet
	 */
	@Override
	public Sprite update(float delta) {
		if (newPositionFromPortal != null) {
			body.setTransform(newPositionFromPortal, 0);
			setNewPositionFromPortal(null);
		}

		Gdx.app.log("", "" + this.getBody().getLinearVelocity());
		
		sprite.setPosition(body.getPosition().x - getOffsetX() + 0.22f,
				body.getPosition().y - getOffsetY() + 0.17f);
		return sprite;
	}

	public static void destroyBullet(Fixture fix) {
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
	public void setUserData(Body body) {
		body.setUserData(this);
		for (Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}

	public boolean isTooSlow() {
		Vector2 velocity = body.getLinearVelocity();
		float speedThreshold = 0.5f;
		return velocity.x < speedThreshold && velocity.x > -speedThreshold && 
				velocity.y < speedThreshold && velocity.y > -speedThreshold;
	}
}
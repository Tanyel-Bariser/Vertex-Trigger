package com.vertextrigger.entities.player;
import static com.vertextrigger.factory.bodyfactory.BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW;
import static com.vertextrigger.util.GameObjectSize.BULLET_SIZE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.vertextrigger.entities.Entity;

/**
 * Bullets are shot from the player's position horizontally.
 * Bullets are freed 5 seconds after being shot.
 */
public class Bullet implements Poolable, Entity {
	static final float SHOT_POWER = 0.1f;
	private final Body body;
	private final Sprite sprite;
	private boolean isFreeable = false;
	
	Bullet(Body body, Sprite sprite) {
		this.body = body;
		this.sprite = sprite;
		body.setUserData(this);
		for (Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}
	
	Vector2 getPosition() {
		return body.getPosition();
	}
	
	void shoot(boolean shootLeft) {
		float left = -SHOT_POWER;
		float right = SHOT_POWER;
		if (shootLeft) shoot(left);
		else shoot(right);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFreeable() {
		isFreeable = true;
	}
	
	public boolean isFreeable() {
		return isFreeable;
	}
	
	private void shoot(float horizontalVelocity) {
		Vector2 velocity = new Vector2(horizontalVelocity, 0);
		boolean wakeForSimulation = true;
		body.applyLinearImpulse(velocity, body.getPosition(),
				wakeForSimulation);
	}
	
	/**
	 * Allows player to set the position of the bullet to that of his gun
	 * 
	 * @param position to set bullet
	 */
	void setPosition(Vector2 position) {
		body.setTransform(position, 0);
	}
	
	/**
	 * Updates the bullet sprite so that its position matches
	 * that of the bullet's physical body, therefore should
	 * be called once per frame for accurate rendering.
	 * 
	 * @param delta not used
	 * @return updated sprite of this bullet
	 */
	
	static Vector2 newPositionFromPortal;
	public static void setNewPositionFromPortal(Vector2 newPosition) {
		newPositionFromPortal = newPosition;
	}
	public static Vector2 getNewPositionFromPortal() {
		return newPositionFromPortal;
	}
	@Override
	public Sprite update(float delta) {
		if (getNewPositionFromPortal() != null) {
			body.setTransform(getNewPositionFromPortal(), 0);
			setNewPositionFromPortal(null);
		}
		
		sprite.setPosition(body.getPosition().x - getOffsetX()+0.22f, 
				body.getPosition().y - getOffsetY()+0.17f);
		return sprite;
	}
		
	public static void destroyBullet(Fixture fix) {
		fix.getBody().setTransform(50, 50, 0);
	}

	/**
	 * Invoked automatically when BulletPool.free(Bullet) is called
	 */
	@Override
	public void reset() {
		Gdx.app.log("RESET","");
		body.setTransform(INITIAL_POSITION_OUT_OF_CAMERA_VIEW.x, INITIAL_POSITION_OUT_OF_CAMERA_VIEW.y, 0);
		body.setLinearVelocity(new Vector2(0,0));
		isFreeable = false;
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
}
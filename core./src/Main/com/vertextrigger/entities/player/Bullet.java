package com.vertextrigger.entities.player;
import static com.vertextrigger.util.GameObjectSize.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.factory.bodyfactory.BulletBodyFactory;
import com.vertextrigger.util.GameObjectSize;

/**
 * Bullets are shot from the player's position horizontally.
 * Bullets are freed 5 seconds after being shot.
 */
public class Bullet implements Poolable, Entity {
	static final float SHOT_POWER = 500;
	private final Body body;
	private final Sprite sprite;
	private boolean isVisible = false;
	
	Bullet(Body body, Sprite sprite) {
		this.body = body;
		this.sprite = sprite;
	}
	
	Vector2 getPosition() {
		return body.getPosition();
	}
	
	void shoot(boolean shootLeft) {
		isVisible = true;
		
		float left = -SHOT_POWER;
		float right = SHOT_POWER;
		if (shootLeft) shoot(left);
		else shoot(right);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setNotVisible() {
		isVisible = false;
	}
	
	public boolean isVisible() {
		return isVisible;
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
	@Override
	public Sprite update(float delta) {
		Vector2 currentPosition = getPosition();
		sprite.setPosition(currentPosition.x - getOffsetX(), currentPosition.y - getOffsetY());
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
		body.setTransform(BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW, 0);
	}

	boolean isInInitialPosition() {
		return isInitialPositionX() && isInitialPositionY();
	}
	
	private boolean isInitialPositionX() {
		return body.getPosition().x < BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW.x + 1 &&  // x to the left of -49 
			   body.getPosition().x > BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW.x - 1;  // x to right of -51
	}
	private boolean isInitialPositionY() {
		return body.getPosition().y < BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW.y + 1 &&
			   body.getPosition().y > BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW.y - 1;
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
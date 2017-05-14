package com.vertextrigger.inanimate;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.vertextrigger.collisiondetection.Collidable;
import com.vertextrigger.util.GameObjectSize;

import static com.badlogic.gdx.math.MathUtils.degreesToRadians;
import static com.vertextrigger.util.GameObjectSize.SMALL_PLATFORM_SIZE;

public class StaticPlatform implements Collidable {
	private final Sprite sprite;
	private final Body body;

	public StaticPlatform(final Sprite sprite, final Body body) {
		this.sprite = sprite;
		this.body = body;
		setUserData();
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSpritePosition() {
		final GameObjectSize platformSize = SMALL_PLATFORM_SIZE;
		sprite.setPosition(body.getPosition().x - (sprite.getWidth() / platformSize.getOffsetX()), body.getPosition().y
				- (sprite.getHeight() / platformSize.getOffsetY()));
	}

	public void setRotation(final float degrees) {
		sprite.setOriginCenter();
		sprite.setRotation(degrees);
		body.setTransform(body.getPosition(), degrees * degreesToRadians);
	}

	@Override
	public void setUserData() {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}

}
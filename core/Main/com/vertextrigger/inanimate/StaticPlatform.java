package com.vertextrigger.inanimate;
import static com.vertextrigger.util.GameObjectSize.*;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.util.GameObjectSize;

public class StaticPlatform {
	private final Sprite sprite;
	private final Body body;
	
	public StaticPlatform(Sprite sprite, Body body) {
		this.sprite = sprite;
		this.body = body;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void setPosition() {
		GameObjectSize platformSize = SMALL_PLATFORM_SIZE;
		sprite.setPosition(body.getPosition().x - sprite.getWidth() / platformSize.getOffsetX(), 
				body.getPosition().y - sprite.getHeight() / platformSize.getOffsetY());
	}
	
	public void setRotation(float degrees) {
		sprite.setOriginCenter();
		sprite.setRotation(degrees * MathUtils.radiansToDegrees);
		body.setTransform(body.getPosition(), degrees);
	}
}
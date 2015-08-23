package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

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
	
	public void setPosition(Vector2 position) {
		body.setTransform(position, 0);
		sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, 
				body.getPosition().y - sprite.getHeight() / 2);
	}
	
	public void setRotation(float degrees) {
		sprite.setOriginCenter();
		sprite.setRotation(degrees * MathUtils.radiansToDegrees);
		body.setTransform(body.getPosition(), degrees);
	}
}
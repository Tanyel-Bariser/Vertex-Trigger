package com.vertextrigger.util;

import com.badlogic.gdx.math.Vector2;

public enum GameObjectSize {	
	BULLET_SIZE(0.5f, 0.5f, 0.25f, 0.25f, 0.26f, 0.2f),
	PLAYER_SIZE(2.5f, 4.5f, 0.5f, 1.5f, 1.9f, 2.2f),
	POKER_BODY_SIZE(1.3f, 4.1f, 0.6f, 1.5f, 2f, 2f),
	PORTAL_SIZE(2f, 5f, 0.5f, 1.5f, 0, 0),//Sprite is bigger oval & body is smaller rectangle that fits inside the sprite
	SMALL_PLATFORM_SIZE(5, 1, 2.5f, .5f, 2f, 2f);
	
	public final static float OBJECT_SIZE = 0.15F;
	
	GameObjectSize(float spriteWidth, float spriteHeight, float physicalWidth, float physicalHeight,float xOffset, float yOffset) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.physicalWidth = physicalWidth;
		this.physicalHeight = physicalHeight;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
}
	
	private final float spriteWidth;
	private final float spriteHeight;
	private final float physicalWidth;
	private final float physicalHeight;
	private final float xOffset;
	private final float yOffset;
	
	public float getSpriteWidth() {
		return spriteWidth * OBJECT_SIZE;
	}
	
	public float getSpriteHeight() {
		return spriteHeight * OBJECT_SIZE;
	}

	public float getPhysicalWidth() {
		return physicalWidth * OBJECT_SIZE;
	}

	public float getPhysicalHeight() {
		return physicalHeight * OBJECT_SIZE;
	}
	
	public float getOffsetX() {
		return xOffset;
	}
	
	public float getOffsetY() {
		return yOffset;
	}
}
package com.vertextrigger.util;

import com.badlogic.gdx.Gdx;

public enum GameObjectSize {
	PLAYER_BULLET_SIZE(0.5f, 0.5f, 0.25f, 0.25f, 0.04f, 0.03f), //
	BEE_BULLET_SIZE(1f, 1f, 0.25f, 0.25f, 0.07f, 0.04f), //
	BEE_SIZE(3f, 2f, 1.5f, 1f, 2.05f, 2.2f), //
	MAGNET_SIZE(3f, 2f, 1.5f, 1f, 2.05f, 2.2f), //
	BACKGROUND_SIZE(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0, 0, 0), //
	PLAYER_SIZE(2.5f, 4.5f, 0.5f, 1.5f, 1.9f, 2.2f), //
	POKER_BODY_SIZE(1.3f, 4f, 0.6f, 1.7f, 2.05f, 2.2f), //
	PORTAL_SIZE(1.5f, 4f, 0.5f, 1.5f, 2f, 2f), // Sprite is bigger oval & body is smaller rectangle that fits inside the sprite
	SHIELD_SIZE(5, 5, PLAYER_SIZE.getPhysicalHeight() * 8.7f, PLAYER_SIZE.getPhysicalHeight() * 8.7f, 0.37f, 0.33f), //
	SIGN_SIZE(3.2f, 3.5f, 0, 0, 0, 0), //
	WINDOW_SIZE(4f, 4.14f, 0, 0, 0, 0), //

	TINY_PLATFORM_SIZE(2, 1, 1, .5f, 2f, 2f), //
	SMALL_PLATFORM_SIZE(5, 1, 2.5f, .5f, 2f, 2f), //
	MEDIUM_PLATFORM_SIZE(8, 1, 4, .5f, 2f, 2f), //
	GROUND_SIZE(500, .2f, 500, .2f, 0, 0);

	public final static float OBJECT_SIZE = 0.15F;

	GameObjectSize(final float spriteWidth, final float spriteHeight, final float physicalWidth, final float physicalHeight, final float xOffset,
			final float yOffset) {
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
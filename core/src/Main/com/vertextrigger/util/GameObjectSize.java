package com.vertextrigger.util;

public class GameObjectSize {
	private final float spriteWidth;
	private final float spriteHeight;
	private final float physicalWidth;
	private final float physicalHeight;
	private final float xOffset;
	private final float yOffset;
	
	private static GameObjectSize smallPlatformSize;
	private static GameObjectSize playerSize;
	private static GameObjectSize bulletSize;
	private static GameObjectSize pokerSize;
	
	private GameObjectSize(float spriteWidth, float spriteHeight, float physicalWidth, float physicalHeight,
								float xOffset, float yOffset) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.physicalWidth = physicalWidth;
		this.physicalHeight = physicalHeight;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public static GameObjectSize getPlayerSize() {
		if (playerSize == null) {
			playerSize = new GameObjectSize(2.5f, 4.5f, 0.5f, 1.5f, 1.9f, 2.2f);
		}
		return playerSize;
	}
	
	public static GameObjectSize getSmallPlatformSize() {
		if (smallPlatformSize == null) {
			smallPlatformSize = new GameObjectSize(5, 1, 2.5f, .5f, 2f, 2f);
		}
		return smallPlatformSize;
	}
	
	public static GameObjectSize getPokerSize() {
		if (pokerSize == null) {
			pokerSize = new GameObjectSize(1.3f, 4f, 0.6f, 2f, 2f, 2f);
		}
		return pokerSize;
	}
	
	public static GameObjectSize getBulletSize() {
		if (bulletSize == null) {
			bulletSize = new GameObjectSize(0.5f, 0.5f, 0.5f, 0.5f, 0.26f, 0.2f);
		}
		return bulletSize;
	}

	public float getSpriteWidth() {
		return spriteWidth;
	}
	
	public float getSpriteHeight() {
		return spriteHeight;
	}

	public float getPhysicalWidth() {
		return physicalWidth;
	}

	public float getPhysicalHeight() {
		return physicalHeight;
	}
	
	public float getOffsetX() {
		return xOffset;
	}
	
	public float getOffsetY() {
		return yOffset;
	}
}
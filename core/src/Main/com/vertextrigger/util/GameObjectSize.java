package com.vertextrigger.util;

public class GameObjectSize {
	private final float spriteWidth;
	private final float spriteHeight;
	private final float physicalWidth;
	private final float physicalHeight;
	
	public static GameObjectSize createPlayerSize() {
		return new GameObjectSize(2.5f, 4.5f, 0.5f, 1.5f);
	}
	
	public static GameObjectSize createSmallPlatformSize() {
		return new GameObjectSize(5, 1, 2.5f, .5f);
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
	public GameObjectSize(float spriteWidth, float spriteHeight, float physicalWidth, float physicalHeight) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.physicalWidth = physicalWidth;
		this.physicalHeight = physicalHeight;
	}
}
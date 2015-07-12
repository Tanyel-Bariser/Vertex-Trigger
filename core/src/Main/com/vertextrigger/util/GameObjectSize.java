package com.vertextrigger.util;

public class GameObjectSize {
	private final float spriteHeight;
	private final float spriteWidth;
	private final float physicalHeight;
	private final float physicalWidth;
	
	public static GameObjectSize createPlayerSize() {
		return new GameObjectSize(30, 40, 10, 13);
	}
	
	public static GameObjectSize createSmallPlatformSize() {
		return new GameObjectSize(20,20,10,10);
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
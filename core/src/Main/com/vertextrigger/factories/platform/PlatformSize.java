package com.vertextrigger.factories.platform;

public class PlatformSize {
	private final float spriteHeight;
	private final float spriteWidth;
	private final float physicalHeight;
	private final float physicalWidth;
	
	
	public float getSpriteHeight() {
		return spriteHeight;
	}

	public float getSpriteWidth() {
		return spriteWidth;
	}

	public float getPhysicalHeight() {
		return physicalHeight;
	}

	public float getPhysicalWidth() {
		return physicalWidth;
	}

	public PlatformSize(float spriteHeight, float spriteWidth, float physicalHeight, float physicalWidth) {
		this.spriteHeight = spriteHeight;
		this.spriteWidth = spriteWidth;
		this.physicalHeight = physicalHeight;
		this.physicalWidth = physicalWidth;
	}
}
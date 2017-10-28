package com.vertextrigger.level;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.levelbuilder.*;
import com.vertextrigger.main.VertexTrigger;

public class HughLevel implements Level {
	Screen screen;// renders what camera can see
	final private HughLevelBuilder levelBuilder;

	public HughLevel(final Screen screen, final HughLevelBuilder levelBuilder) {
		this.levelBuilder = levelBuilder;
		addEntities(levelBuilder.buildEntities());
		addInanimates(levelBuilder.buildInanimate());
		levelBuilder.addCallbacks(this);
	}

	public void addEntities(final Array<Entity> entities) {
		this.entities.addAll(entities);
	}

	@Override
	public void onLoad() {
		VertexTrigger.ASSETS.unloadPrototypeLevel();
	}

	@Override
	public void onUnload() {
		VertexTrigger.ASSETS.unloadPrototypeLevel();
	}

	@Override
	public Array<Sprite> getBackgroundSprites() {
		return null;
	}

	@Override
	public Array<Entity> getEntities() {
		return null;
	}
}

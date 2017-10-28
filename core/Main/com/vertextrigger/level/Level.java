package com.vertextrigger.level;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.Entity;

public abstract class Level {

	protected final Array<Entity> entities = new Array<>();

	protected abstract void onLoad();

	protected abstract void onUnload();

	protected abstract Array<Sprite> getBackgroundSprites();

	protected abstract Array<Entity> getEntities();
}
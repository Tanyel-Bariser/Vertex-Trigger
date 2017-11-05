package com.vertextrigger.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.collisiondetection.CollisionDetection;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.inanimate.Inanimate;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.screen.AbstractGameScreen;

import lombok.Getter;

@Getter
public abstract class Level {

	protected World world;
	protected Player player;
	protected Array<Entity> entities;
	protected Array<Inanimate> inanimate;
	protected LevelSize levelSize;

	public abstract void onLoad();
	public abstract void onUnload();
	public abstract void onStart();
	public abstract AbstractGameScreen getScreen(final VertexTrigger vertexTrigger);
	public abstract void reset();
	public abstract World getWorld();

	// to add entities mid-level e.g. bullets or generated enemies/powerups
	public void addEntity(final Entity entity) {
		entities.add(entity);
	}

	public float getGroundLevel() {
		return -levelSize.getContainerHeight();
	}

	public float getCeilingLevel() {
		return levelSize.getContainerHeight();
	}

	public float getLeftBorderOfLevel() {
		return -levelSize.getContainerWidth();
	}

	public float getRightBorderOfLevel() {
		return levelSize.getContainerWidth();
	}

	static World createWorld(final Vector2 GRAVITY) {
		final World world = new World(GRAVITY, true);
		world.setContactListener(new CollisionDetection());
		return world;
	}
}
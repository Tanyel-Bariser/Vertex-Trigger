package com.vertextrigger.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.levelbuilder.HughLevelBuilder;
import com.vertextrigger.main.GameLoop;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.screen.AbstractGameScreen;

import lombok.val;

public class HughLevel extends Level {

	private final Vector2 LEVEL_GRAVITY = new Vector2(0, -9.81f);

	@Override
	public void onLoad() {
		VertexTrigger.ASSETS.loadHughLevel();
	}

	@Override
	public void onUnload() {
		VertexTrigger.ASSETS.unloadHughLevel();
	}

	@Override
	public void onStart() {
		this.world = createWorld(LEVEL_GRAVITY);
		val levelBuilder = new HughLevelBuilder(this);
		this.player = levelBuilder.getPlayer();
		this.entities = levelBuilder.buildEntities();
		this.inanimate = levelBuilder.buildInanimate();
		this.levelSize = levelBuilder.getLevelSize();

		// todo remove this line
		AudioManager.onPause();
	}

	@Override
	public Array<Entity> getEntitiesForUpdate(final GameLoop gameLoop) {
		final Array<Entity> updatable = new Array<>();
		for (final Entity entity : this.entities) {
			if (gameLoop.isUpdatable(entity)) {
				updatable.add(entity);
			}
		}
		return updatable;
	}

	@Override
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	@Override
	public AbstractGameScreen getScreen(final VertexTrigger vertexTrigger) {
		return new AbstractGameScreen(vertexTrigger, this);
	}

	@Override
	public void reset() {

	}
}

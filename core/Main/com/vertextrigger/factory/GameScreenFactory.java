package com.vertextrigger.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.collisiondetection.CollisionDetection;
import com.vertextrigger.levelbuilder.*;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.screen.AbstractGameScreen;

public class GameScreenFactory {
	public static AbstractGameScreen createPrototypeLevel(final VertexTrigger vertex) {
		return new AbstractGameScreen(vertex) {
			@Override
			protected void initialiseAssets() {
				VertexTrigger.ASSETS.loadPrototypeLevel();
			}

			@Override
			protected void disposeOfAssets() {
				VertexTrigger.ASSETS.unloadPrototypeLevel();
			}

			@Override
			protected AbstractLevelBuilder createLevelBuilder() {
				world = createWorld(GRAVITY);
				return new PrototypeLevelBuilder(world, this);
			}
		};
	}

	public static AbstractGameScreen createHughLevel(final VertexTrigger vertex) {
		return new AbstractGameScreen(vertex) {
			@Override
			protected void initialiseAssets() {
				VertexTrigger.ASSETS.loadHughLevel();
			}

			@Override
			protected void disposeOfAssets() {
				VertexTrigger.ASSETS.unloadHughLevel();
			}

			@Override
			protected AbstractLevelBuilder createLevelBuilder() {
				world = createWorld(GRAVITY);
				return new HughLevelBuilder(world, this);
			}
		};
	}

	public static AbstractGameScreen createTanyelLevel(final VertexTrigger vertex) {
		return new AbstractGameScreen(vertex) {
			@Override
			protected void initialiseAssets() {
				VertexTrigger.ASSETS.loadPrototypeLevel();
			}

			@Override
			protected void disposeOfAssets() {
				VertexTrigger.ASSETS.unloadPrototypeLevel();
			}

			@Override
			protected AbstractLevelBuilder createLevelBuilder() {
				world = createWorld(GRAVITY);
				return new TanyelLevelBuilder(world, this);
			}
		};
	}

	private static World createWorld(final Vector2 GRAVITY) {
		final World world = new World(GRAVITY, true);
		world.setContactListener(new CollisionDetection());
		return world;
	}
}
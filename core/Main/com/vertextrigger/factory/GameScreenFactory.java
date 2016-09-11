package com.vertextrigger.factory;

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
			protected AbstractLevelBuilder createLevelBuilder() {
				world = new World(GRAVITY, true);
				final AbstractLevelBuilder prototypeLevelBuilder = new PrototypeLevelBuilder(world, this, 4, 3);
				world.setContactListener(new CollisionDetection());
				return prototypeLevelBuilder;
			}
		};
	}
}
